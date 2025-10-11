package com.iafenvoy.neptune.render;

import java.util.Optional;
import net.minecraft.resources.ResourceLocation;

public interface EntityWithMarkerTextureProvider extends EntityTextureProvider {
    Optional<ResourceLocation> getMarkerTextureId();
}
