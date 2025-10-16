package com.iafenvoy.neptune.ability.type;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityDataHolder;
import com.iafenvoy.neptune.registry.NeptuneRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@EventBusSubscriber
public non-sealed abstract class DummyAbility extends Ability<DummyAbility> {
    public static final DummyAbility EMPTY = new DummyAbility(AbilityCategory.EMPTY) {
        @Override
        protected boolean applyInternal(AbilityDataHolder holder) {
            return true;
        }
    };

    @SubscribeEvent
    public static void registerDefaulted(RegisterEvent event) {
        if (event.getRegistryKey() == NeptuneRegistries.ABILITY_KEY)
            event.register(NeptuneRegistries.ABILITY_KEY, ResourceLocation.withDefaultNamespace("empty"), () -> EMPTY);
    }

    public DummyAbility(@NotNull Supplier<AbilityCategory> category) {
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
