package com.iafenvoy.neptune.power.type;

import com.iafenvoy.neptune.Constants;
import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.network.PacketBufferUtils;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerData;
import com.iafenvoy.neptune.power.PowerDataHolder;
import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public sealed abstract class AbstractPower<T extends AbstractPower<T>> permits DelayPower, DummyPower, InstantPower, IntervalPower, PersistPower {
    public static final List<AbstractPower<?>> POWERS = new ArrayList<>();
    private static final Map<Identifier, AbstractPower<?>> BY_ID = new HashMap<>();
    private final Identifier id;
    private final PowerCategory category;
    private Consumer<AbstractPower<?>> init = self -> {
    };
    private ToIntFunction<PowerDataHolder> primaryCooldownSupplier = data -> 0, secondaryCooldownSupplier = data -> 0;
    private ToDoubleFunction<PowerDataHolder> exhaustion = data -> 0;
    protected Consumer<PowerDataHolder> apply = holder -> {
    };
    @Nullable
    protected Supplier<SoundEvent> applySound;
    private boolean experimental = false;

    public AbstractPower(String id, PowerCategory category) {
        this(Identifier.tryParse(id), category);
    }

    public AbstractPower(Identifier id, PowerCategory category) {
        this.id = id;
        this.category = category;
        if (category != null) {
            POWERS.add(this);
            BY_ID.put(id, this);
            category.registerPower(this);
        }
    }

    public Identifier getId() {
        return this.id;
    }

    public PowerCategory getCategory() {
        return this.category;
    }

    public Identifier getIconTexture() {
        if (this.isEmpty()) return Identifier.of(Identifier.DEFAULT_NAMESPACE, "textures/item/barrier.png");
        return Identifier.of(Neptune.MOD_ID, "textures/power/" + this.id + ".png");
    }

    public T onInit(Consumer<AbstractPower<?>> init) {
        this.init = init;
        return this.get();
    }

    public void init() {
        this.init.accept(this);
    }

    public T onApply(Consumer<PowerDataHolder> apply) {
        this.apply = apply;
        return this.get();
    }

    public T setApplySound(Supplier<SoundEvent> applySound) {
        this.applySound = applySound;
        return this.get();
    }

    public int getPrimaryCooldown(PowerData.SinglePowerData data) {
        return this.getPrimaryCooldown(new PowerDataHolder(data));
    }

    public int getPrimaryCooldown(PowerDataHolder data) {
        return this.primaryCooldownSupplier.applyAsInt(data);
    }

    public T setPrimaryCooldown(int ticks) {
        return this.setPrimaryCooldown(data -> ticks);
    }

    public T setPrimaryCooldown(ToIntFunction<PowerDataHolder> supplier) {
        this.primaryCooldownSupplier = supplier;
        return this.get();
    }

    public int getSecondaryCooldown(PowerData.SinglePowerData data) {
        return this.getSecondaryCooldown(new PowerDataHolder(data));
    }

    public int getSecondaryCooldown(PowerDataHolder data) {
        return this.secondaryCooldownSupplier.applyAsInt(data);
    }

    public T setSecondaryCooldown(int ticks) {
        return this.setSecondaryCooldown(data -> ticks);
    }

    public T setSecondaryCooldown(ToIntFunction<PowerDataHolder> supplier) {
        this.secondaryCooldownSupplier = supplier;
        return this.get();
    }

    public double getExhaustion(PowerData.SinglePowerData data) {
        return this.getExhaustion(new PowerDataHolder(data));
    }

    public double getExhaustion(PowerDataHolder data) {
        return this.exhaustion.applyAsDouble(data);
    }

    public T setExhaustion(float exhaustion) {
        return this.setExhaustion(data -> exhaustion);
    }

    public T setExhaustion(ToDoubleFunction<PowerDataHolder> exhaustion) {
        this.exhaustion = exhaustion;
        return this.get();
    }

    public boolean isPersist() {
        return this.getType() == PowerType.PERSIST;
    }

    public boolean apply(PowerData.SinglePowerData data) {
        boolean success = this.applyInternal(new PowerDataHolder(data));
        if (success) this.sendApplyMessage(data.getPlayer(), true);
        return success;
    }

    public void unapply(PowerData.SinglePowerData data) {
        this.sendApplyMessage(data.getPlayer(), false);
    }

    public void sendApplyMessage(PlayerEntity player, boolean enable) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            PacketByteBuf buf = PacketBufferUtils.create();
            buf.writeUuid(player.getUuid()).writeIdentifier(this.id).writeBoolean(enable);
            NetworkManager.sendToPlayer(serverPlayer, Constants.POWER_STATE_CHANGE, buf);
        }
    }

    public boolean isEmpty() {
        return this == DummyPower.EMPTY || this.id == null || this.id.getPath().isEmpty();
    }

    public String getTranslateKey() {
        return this.id.toTranslationKey("power." + Neptune.MOD_ID);
    }

    protected abstract boolean applyInternal(PowerDataHolder holder);

    protected abstract PowerType getType();

    protected abstract T get();

    protected static void playSound(PowerDataHolder holder, @Nullable Supplier<SoundEvent> sound) {
        if (sound != null)
            SoundUtil.playSound(holder.getWorld(), holder.getPlayer().getX(), holder.getPlayer().getY(), holder.getPlayer().getZ(), sound.get().getId(), 0.5f, 1);
    }

    public static AbstractPower<?> byId(Identifier id) {
        return BY_ID.getOrDefault(id, DummyPower.EMPTY);
    }

    public T experimental() {
        this.experimental = true;
        return this.get();
    }

    public boolean isExperimental() {
        return this.experimental;
    }

    protected enum PowerType {
        INSTANT, INTERVAL, PERSIST, DELAY, DUMMY
    }
}
