package com.iafenvoy.neptune.power.type;

import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerDataHolder;
import net.minecraft.util.Identifier;

public final class InstantPower extends AbstractPower<InstantPower> {
    public InstantPower(Identifier id, PowerCategory category) {
        super(id, category);
    }

    @Override
    protected boolean applyInternal(PowerDataHolder holder) {
        this.apply.accept(holder);
        if (holder.isCancelled()) return false;
        holder.cooldown();
        playSound(holder, this.applySound);
        return true;
    }

    @Override
    protected PowerType getType() {
        return PowerType.INSTANT;
    }

    @Override
    protected InstantPower get() {
        return this;
    }
}
