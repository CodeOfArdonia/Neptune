package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import com.iafenvoy.neptune.util.Timeout;
import java.util.function.ToIntFunction;
import net.minecraft.resources.ResourceLocation;

public final class IntervalAbility extends AbstractAbility<IntervalAbility> {
    private ToIntFunction<AbilityDataHolder> times = data -> 0;
    private ToIntFunction<AbilityDataHolder> interval = data -> 0;

    public IntervalAbility(ResourceLocation id, AbilityCategory category) {
        super(id, category);
    }

    public IntervalAbility setInterval(int interval) {
        return this.setInterval(data -> interval);
    }

    public IntervalAbility setInterval(ToIntFunction<AbilityDataHolder> interval) {
        this.interval = interval;
        return this;
    }

    public IntervalAbility setTimes(int times) {
        return this.setTimes(data -> times);
    }

    public IntervalAbility setTimes(ToIntFunction<AbilityDataHolder> times) {
        this.times = times;
        return this;
    }

    @Override
    protected boolean applyInternal(AbilityDataHolder holder) {
        playSound(holder, this.applySound);
        this.apply.accept(holder);
        if (holder.isCancelled()) return false;
        holder.cooldown();
        Timeout.create(this.interval.applyAsInt(holder), this.times.applyAsInt(holder), () -> this.apply.accept(holder));
        return true;
    }

    @Override
    protected AbilityType getType() {
        return AbilityType.INTERVAL;
    }

    @Override
    public int getPrimaryCooldown(AbilityDataHolder data) {
        return super.getPrimaryCooldown(data) + this.interval.applyAsInt(data) * this.times.applyAsInt(data);
    }

    @Override
    protected IntervalAbility get() {
        return this;
    }
}
