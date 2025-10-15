package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import com.iafenvoy.neptune.ability.AbilityRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public non-sealed abstract class DummyAbility extends Ability<DummyAbility> {
    public static final DummyAbility EMPTY = new DummyAbility(ResourceLocation.tryParse(""), null) {
        @Override
        protected boolean applyInternal(AbilityDataHolder holder) {
            return true;
        }
    };

    static {
        Registry.register(AbilityRegistry.REGISTRY, "empty", EMPTY);
    }

    public DummyAbility(ResourceLocation id, AbilityCategory category) {
        super(id, category);
    }

    @Override
    protected AbilityType getType() {
        return AbilityType.DUMMY;
    }

    @Override
    protected DummyAbility get() {
        return this;
    }
}
