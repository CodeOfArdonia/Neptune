package com.iafenvoy.neptune.render;

import com.iafenvoy.neptune.render.feature.MarkerFeatureRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CommonPlayerLikeWithMarkerEntityRenderer<T extends Mob & EntityWithMarkerTextureProvider> extends CommonPlayerLikeEntityRenderer<T> {
    public CommonPlayerLikeWithMarkerEntityRenderer(EntityRendererProvider.Context ctx) {
        this(ctx, true);
    }

    public CommonPlayerLikeWithMarkerEntityRenderer(EntityRendererProvider.Context ctx, boolean glint) {
        this(ctx, glint, false);
    }

    public CommonPlayerLikeWithMarkerEntityRenderer(EntityRendererProvider.Context ctx, boolean glint, boolean slim) {
        super(ctx, slim);
        this.addLayer(new MarkerFeatureRenderer<>(this, glint));
    }
}
