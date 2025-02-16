package com.iafenvoy.neptune.power.type;

import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerDataHolder;
import com.iafenvoy.neptune.util.Timeout;

import java.util.function.ToIntFunction;

public final class IntervalPower extends AbstractPower<IntervalPower> {
    private ToIntFunction<PowerDataHolder> times = data -> 0;
    private ToIntFunction<PowerDataHolder> interval = data -> 0;

    public IntervalPower(String id, PowerCategory category) {
        super(id, category);
    }

    public IntervalPower setInterval(int interval) {
        return this.setInterval(data -> interval);
    }

    public IntervalPower setInterval(ToIntFunction<PowerDataHolder> interval) {
        this.interval = interval;
        return this;
    }

    public IntervalPower setTimes(int times) {
        return this.setTimes(data -> times);
    }

    public IntervalPower setTimes(ToIntFunction<PowerDataHolder> times) {
        this.times = times;
        return this;
    }

    @Override
    protected boolean applyInternal(PowerDataHolder holder) {
        playSound(holder, this.applySound);
        this.apply.accept(holder);
        if (holder.isCancelled()) return false;
        holder.cooldown();
        Timeout.create(this.interval.applyAsInt(holder), this.times.applyAsInt(holder), () -> this.apply.accept(holder));
        return true;
    }

    @Override
    protected PowerType getType() {
        return PowerType.INTERVAL;
    }

    @Override
    public int getPrimaryCooldown(PowerDataHolder data) {
        return super.getPrimaryCooldown(data) + this.interval.applyAsInt(data) * this.times.applyAsInt(data);
    }

    @Override
    protected IntervalPower get() {
        return this;
    }
}
