package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import com.iafenvoy.neptune.util.Timeout;
import net.minecraft.util.Identifier;

public final class DelayAbility extends AbstractAbility<DelayAbility> {
    private int delay = 0;

    public DelayAbility(Identifier id, AbilityCategory category) {
        super(id, category);
    }

    public DelayAbility setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public int getPrimaryCooldown(AbilityDataHolder data) {
        return super.getPrimaryCooldown(data) + this.delay;
    }

    @Override
    protected boolean applyInternal(AbilityDataHolder holder) {
        playSound(holder, this.applySound);
        holder.cooldown();
        Timeout.create(this.delay, () -> this.apply.accept(holder));
        return true;
    }

    @Override
    protected AbilityType getType() {
        return AbilityType.DELAY;
    }

    @Override
    protected DelayAbility get() {
        return this;
    }
}
