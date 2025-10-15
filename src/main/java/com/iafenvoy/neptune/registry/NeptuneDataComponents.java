package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.type.Ability;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeptuneDataComponents {
    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Neptune.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Ability<?>>> ABILITY = REGISTRY.register("ability", () -> DataComponentType.<Ability<?>>builder().persistent(NeptuneRegistries.ABILITY.byNameCodec()).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AbilityCategory>> ABILITY_CATEGORY = REGISTRY.register("ability_category", () -> DataComponentType.<AbilityCategory>builder().persistent(NeptuneRegistries.ABILITY_CATEGORY.byNameCodec()).build());
}
