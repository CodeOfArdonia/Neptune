package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.type.Ability;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class AbilityRegistry {
    public static final ResourceKey<Registry<Ability<?>>> KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Neptune.MOD_ID, "ability"));
    public static final DefaultedRegistry<Ability<?>> REGISTRY = new DefaultedMappedRegistry<>("empty", KEY, Lifecycle.stable(), false);
}
