package com.iafenvoy.neptune.render;

import net.minecraft.resources.ResourceLocation;

public interface EntityTextureProvider {
    ResourceLocation getTextureId();

    default float getScale() {
        return 1;
    }
}
