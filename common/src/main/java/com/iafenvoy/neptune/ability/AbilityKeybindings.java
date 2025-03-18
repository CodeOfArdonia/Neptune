package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.network.PacketBufferUtils;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.networking.NetworkManager;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.PacketByteBuf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class AbilityKeybindings {
    private static final Map<AbilityCategory, KeyBindingHolder> KEY_BINDINGS = new HashMap<>();

    public static void registerKeyBinding(AbilityCategory category, KeyBinding keyBinding) {
        KeyBindingHolder holder = new KeyBindingHolder(keyBinding);
        holder.registerPressCallback(press -> {
            if (press) {
                PacketByteBuf buf = PacketBufferUtils.create();
                buf.writeIdentifier(category.getId());
                NetworkManager.sendToServer(NeptuneConstants.ABILITY_KEYBINDING_SYNC, buf);
            }
        });
        KEY_BINDINGS.put(category, holder);
    }

    public static KeyBindingHolder get(AbilityCategory category) {
        return KEY_BINDINGS.get(category);
    }

    static {
        ClientTickEvent.CLIENT_POST.register(client -> KEY_BINDINGS.values().forEach(KeyBindingHolder::tick));
    }

    public static class KeyBindingHolder {
        public final Supplier<KeyBinding> keyBinding;
        private final List<BooleanConsumer> callback = new ArrayList<>();
        private boolean pressed;

        public KeyBindingHolder(KeyBinding keyBinding) {
            this.keyBinding = () -> keyBinding;
        }

        public KeyBindingHolder(Supplier<KeyBinding> keyBinding) {
            this.keyBinding = keyBinding;
        }

        public void registerPressCallback(BooleanConsumer consumer) {
            this.callback.add(consumer);
        }

        public void tick() {
            KeyBinding k = this.keyBinding.get();
            if (k == null) return;
            boolean curr = k.isPressed();
            if (!this.pressed && curr) this.callback.forEach(x -> x.accept(true));
            if (this.pressed && !curr) this.callback.forEach(x -> x.accept(false));
            this.pressed = curr;
        }

        public boolean isPressed() {
            return this.pressed;
        }
    }
}
