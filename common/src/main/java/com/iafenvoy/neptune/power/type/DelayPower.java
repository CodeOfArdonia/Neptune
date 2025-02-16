package com.iafenvoy.neptune.power.type;

import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerDataHolder;
import com.iafenvoy.neptune.util.Timeout;
import net.minecraft.util.Identifier;

public final class DelayPower extends AbstractPower<DelayPower> {
    private int delay = 0;

    public DelayPower(Identifier id, PowerCategory category) {
        super(id, category);
    }

    public DelayPower setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public int getPrimaryCooldown(PowerDataHolder data) {
        return super.getPrimaryCooldown(data) + this.delay;
    }

    @Override
    protected boolean applyInternal(PowerDataHolder holder) {
        playSound(holder, this.applySound);
        holder.cooldown();
        Timeout.create(this.delay, () -> this.apply.accept(holder));
        return true;
    }

    @Override
    protected PowerType getType() {
        return PowerType.DELAY;
    }

    @Override
    protected DelayPower get() {
        return this;
    }
}
