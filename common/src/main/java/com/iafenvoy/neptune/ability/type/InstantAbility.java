package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import net.minecraft.util.Identifier;

public final class InstantAbility extends AbstractAbility<InstantAbility> {
    public InstantAbility(Identifier id, AbilityCategory category) {
        super(id, category);
    }

    @Override
    protected boolean applyInternal(AbilityDataHolder holder) {
        this.apply.accept(holder);
        if (holder.isCancelled()) return false;
        holder.cooldown();
        playSound(holder, this.applySound);
        return true;
    }

    @Override
    protected AbilityType getType() {
        return AbilityType.INSTANT;
    }

    @Override
    protected InstantAbility get() {
        return this;
    }
}
