package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public final class InstantAbility extends Ability<InstantAbility> {
    public InstantAbility(@NotNull Supplier<AbilityCategory> category) {
        super(category);
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
