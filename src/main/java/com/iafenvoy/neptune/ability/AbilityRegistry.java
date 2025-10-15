package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.type.Ability;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class AbilityRegistry {
    public static final ResourceKey<Registry<Ability<?>>> ABILITY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Neptune.MOD_ID, "ability"));
    public static final DefaultedRegistry<Ability<?>> ABILITY = new DefaultedMappedRegistry<>("empty", ABILITY_KEY, Lifecycle.stable(), false);

    public static final ResourceKey<Registry<AbilityCategory>> ABILITY_CATEGORY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Neptune.MOD_ID, "ability_category"));
    public static final Registry<AbilityCategory> ABILITY_CATEGORY = new MappedRegistry<>(ABILITY_CATEGORY_KEY, Lifecycle.stable(), false);
}
