package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.network.PacketBufferUtils;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
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

public sealed abstract class AbstractAbility<T extends AbstractAbility<T>> permits DelayAbility, DummyAbility, InstantAbility, IntervalAbility, PersistAbility {
    public static final List<AbstractAbility<?>> ABILITIES = new ArrayList<>();
    private static final Map<Identifier, AbstractAbility<?>> BY_ID = new HashMap<>();
    private final Identifier id;
    private final AbilityCategory category;
    private Consumer<AbstractAbility<?>> init = self -> {
    };
    private ToIntFunction<AbilityDataHolder> primaryCooldownSupplier = data -> 0, secondaryCooldownSupplier = data -> 0;
    private ToDoubleFunction<AbilityDataHolder> exhaustion = data -> 0;
    protected Consumer<AbilityDataHolder> apply = holder -> {
    };
    @Nullable
    protected Supplier<SoundEvent> applySound;
    private boolean experimental = false;

    public AbstractAbility(Identifier id, AbilityCategory category) {
        this.id = id;
        this.category = category;
        if (category != null) {
            ABILITIES.add(this);
            BY_ID.put(id, this);
            category.registerAbility(this);
        }
    }

    public Identifier getId() {
        return this.id;
    }

    public AbilityCategory getCategory() {
        return this.category;
    }

    public Identifier getIconTexture() {
        if (this.isEmpty()) return Identifier.of(Identifier.DEFAULT_NAMESPACE, "textures/item/barrier.png");
        return Identifier.of(this.id.getNamespace(), "textures/ability/" + this.id.getPath() + ".png");
    }

    public T onInit(Consumer<AbstractAbility<?>> init) {
        this.init = init;
        return this.get();
    }

    public void init() {
        this.init.accept(this);
    }

    public T onApply(Consumer<AbilityDataHolder> apply) {
        this.apply = apply;
        return this.get();
    }

    public T setApplySound(Supplier<SoundEvent> applySound) {
        this.applySound = applySound;
        return this.get();
    }

    public int getPrimaryCooldown(AbilityData.SingleAbilityData data) {
        return this.getPrimaryCooldown(new AbilityDataHolder(data));
    }

    public int getPrimaryCooldown(AbilityDataHolder data) {
        return this.primaryCooldownSupplier.applyAsInt(data);
    }

    public T setPrimaryCooldown(int ticks) {
        return this.setPrimaryCooldown(data -> ticks);
    }

    public T setPrimaryCooldown(ToIntFunction<AbilityDataHolder> supplier) {
        this.primaryCooldownSupplier = supplier;
        return this.get();
    }

    public int getSecondaryCooldown(AbilityData.SingleAbilityData data) {
        return this.getSecondaryCooldown(new AbilityDataHolder(data));
    }

    public int getSecondaryCooldown(AbilityDataHolder data) {
        return this.secondaryCooldownSupplier.applyAsInt(data);
    }

    public T setSecondaryCooldown(int ticks) {
        return this.setSecondaryCooldown(data -> ticks);
    }

    public T setSecondaryCooldown(ToIntFunction<AbilityDataHolder> supplier) {
        this.secondaryCooldownSupplier = supplier;
        return this.get();
    }

    public double getExhaustion(AbilityData.SingleAbilityData data) {
        return this.getExhaustion(new AbilityDataHolder(data));
    }

    public double getExhaustion(AbilityDataHolder data) {
        return this.exhaustion.applyAsDouble(data);
    }

    public T setExhaustion(float exhaustion) {
        return this.setExhaustion(data -> exhaustion);
    }

    public T setExhaustion(ToDoubleFunction<AbilityDataHolder> exhaustion) {
        this.exhaustion = exhaustion;
        return this.get();
    }

    public boolean isPersist() {
        return this.getType() == AbilityType.PERSIST;
    }

    public boolean apply(AbilityData.SingleAbilityData data) {
        if (!data.allowEnable()) return false;
        boolean success = this.applyInternal(new AbilityDataHolder(data));
        if (success) this.sendApplyMessage(data.getPlayer(), true);
        return success;
    }

    public void unapply(AbilityData.SingleAbilityData data) {
        this.sendApplyMessage(data.getPlayer(), false);
    }

    public void sendApplyMessage(PlayerEntity player, boolean enable) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            PacketByteBuf buf = PacketBufferUtils.create();
            buf.writeUuid(player.getUuid()).writeIdentifier(this.id).writeBoolean(enable);
            NetworkManager.sendToPlayer(serverPlayer, NeptuneConstants.ABILITY_STATE_CHANGE, buf);
        }
    }

    public boolean isEmpty() {
        return this == DummyAbility.EMPTY || this.id == null || this.id.getPath().isEmpty();
    }

    public String getTranslateKey() {
        return this.id.toTranslationKey("ability." + Neptune.MOD_ID);
    }

    protected abstract boolean applyInternal(AbilityDataHolder holder);

    protected abstract AbilityType getType();

    protected abstract T get();

    protected static void playSound(AbilityDataHolder holder, @Nullable Supplier<SoundEvent> sound) {
        if (sound != null)
            SoundUtil.playSound(holder.getWorld(), holder.getPlayer().getX(), holder.getPlayer().getY(), holder.getPlayer().getZ(), sound.get().getId(), 0.5f, 1);
    }

    public static AbstractAbility<?> byId(Identifier id) {
        return BY_ID.getOrDefault(id, DummyAbility.EMPTY);
    }

    public T experimental() {
        this.experimental = true;
        return this.get();
    }

    public boolean isExperimental() {
        return this.experimental;
    }

    public static void initAll() {
        ABILITIES.forEach(AbstractAbility::init);
    }

    protected enum AbilityType {
        INSTANT, INTERVAL, PERSIST, DELAY, DUMMY
    }
}
