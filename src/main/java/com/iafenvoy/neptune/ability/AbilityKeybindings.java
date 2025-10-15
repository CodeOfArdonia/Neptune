package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.ability.type.Ability;
import com.iafenvoy.neptune.event.AbilityStateChangeEvent;
import com.iafenvoy.neptune.network.payload.AbilityKeybindingSyncPayload;
import com.iafenvoy.neptune.network.payload.AbilityStateChangePayload;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public class AbilityKeybindings {
    private static final Map<AbilityCategory, KeyBindingHolder> KEY_BINDINGS = new HashMap<>();

    public static void registerKeyBinding(AbilityCategory category, KeyMapping keyBinding) {
        KeyBindingHolder holder = new KeyBindingHolder(keyBinding);
        holder.registerPressCallback(press -> {
            if (press) PacketDistributor.sendToServer(new AbilityKeybindingSyncPayload(category.getId()));
        });
        KEY_BINDINGS.put(category, holder);
    }

    public static KeyBindingHolder get(AbilityCategory category) {
        return KEY_BINDINGS.get(category);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        KEY_BINDINGS.values().forEach(KeyBindingHolder::tick);
    }

    public static class KeyBindingHolder {
        public final Supplier<KeyMapping> keyBinding;
        private final List<BooleanConsumer> callback = new ArrayList<>();
        private boolean pressed;

        public KeyBindingHolder(KeyMapping keyBinding) {
            this.keyBinding = () -> keyBinding;
        }

        public KeyBindingHolder(Supplier<KeyMapping> keyBinding) {
            this.keyBinding = keyBinding;
        }

        public void registerPressCallback(BooleanConsumer consumer) {
            this.callback.add(consumer);
        }

        public void tick() {
            KeyMapping k = this.keyBinding.get();
            if (k == null) return;
            boolean curr = k.isDown();
            if (!this.pressed && curr) this.callback.forEach(x -> x.accept(true));
            if (this.pressed && !curr) this.callback.forEach(x -> x.accept(false));
            this.pressed = curr;
        }

        public boolean isPressed() {
            return this.pressed;
        }
    }

    public static void onAbilityStateChange(AbilityStateChangePayload payload) {
        Level world = Minecraft.getInstance().level;
        assert world != null;
        Player player = world.getPlayerByUUID(payload.player());
        Ability<?> ability = AbilityRegistry.ABILITY.get(payload.ability());
        boolean enable = payload.enable();
        if (!ability.isEmpty()) NeoForge.EVENT_BUS.post(new AbilityStateChangeEvent(player, ability, enable));
    }
}
