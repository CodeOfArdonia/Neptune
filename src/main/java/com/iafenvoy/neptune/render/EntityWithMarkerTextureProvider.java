package com.iafenvoy.neptune.render;

import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public interface EntityWithMarkerTextureProvider extends EntityTextureProvider {
    Optional<ResourceLocation> getMarkerTextureId();
}
