package com.iafenvoy.neptune.render.feature;

import com.iafenvoy.neptune.render.EntityWithMarkerTextureProvider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class MarkerFeatureRenderer<T extends Mob & EntityWithMarkerTextureProvider> extends RenderLayer<T, PlayerModel<T>> {
    private final boolean glint;

    public MarkerFeatureRenderer(RenderLayerParent<T, PlayerModel<T>> context, boolean glint) {
        super(context);
        this.glint = glint;
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        Optional<ResourceLocation> marker = entity.getMarkerTextureId();
        HumanoidModel<T> model = this.getParentModel();
        marker.ifPresent(id -> model.renderToBuffer(matrices, vertexConsumers.getBuffer(this.glint ? RenderType.entityTranslucentEmissive(id) : RenderType.entityCutout(id)), light, OverlayTexture.NO_OVERLAY, -1));
    }
}
