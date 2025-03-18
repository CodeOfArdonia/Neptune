package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class PersistAbility extends AbstractAbility<PersistAbility> {
    private Consumer<AbilityDataHolder> unapply = data -> {
    }, tick = data -> {
    };
    @Nullable
    private Supplier<SoundEvent> unapplySound;

    public PersistAbility(Identifier id, AbilityCategory category) {
        super(id, category);
    }

    public PersistAbility setUnapplySound(Supplier<SoundEvent> unapplySound) {
        this.unapplySound = unapplySound;
        return this;
    }

    public PersistAbility onUnapply(Consumer<AbilityDataHolder> unapply) {
        this.unapply = unapply;
        return this;
    }

    public PersistAbility onTick(Consumer<AbilityDataHolder> tick) {
        this.tick = tick;
        return this;
    }

    @Override
    public void unapply(AbilityData.SingleAbilityData data) {
        AbilityDataHolder holder = new AbilityDataHolder(data);
        playSound(holder, this.unapplySound);
        this.unapply.accept(holder);
        holder.cooldown();
        super.unapply(data);
    }

    public boolean tick(AbilityData.SingleAbilityData data) {
        AbilityDataHolder holder = new AbilityDataHolder(data);
        this.tick.accept(holder);
        if (!holder.isCancelled()) data.getPlayer().addExhaustion((float) (this.getExhaustion(data) / 20));
        return holder.isCancelled();
    }

    @Override
    protected boolean applyInternal(AbilityDataHolder holder) {
        playSound(holder, this.applySound);
        this.apply.accept(holder);
        return true;
    }

    @Override
    protected AbilityType getType() {
        return AbilityType.PERSIST;
    }

    @Override
    protected PersistAbility get() {
        return this;
    }
}
