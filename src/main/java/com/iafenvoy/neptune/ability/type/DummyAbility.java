package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import com.iafenvoy.neptune.registry.NeptuneRegistries;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber
public non-sealed abstract class DummyAbility extends Ability<DummyAbility> {
    public static final DummyAbility EMPTY = new DummyAbility(null) {
        @Override
        protected boolean applyInternal(AbilityDataHolder holder) {
            return true;
        }
    };

    @SubscribeEvent
    public static void registerDefaulted(FMLCommonSetupEvent event) {
        Registry.register(NeptuneRegistries.ABILITY, "empty", EMPTY);
    }

    public DummyAbility(AbilityCategory category) {
        super(category);
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
