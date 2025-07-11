package com.iafenvoy.neptune.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CommonPlayerLikeEntityRenderer<T extends MobEntity & EntityTextureProvider> extends BipedEntityRenderer<T, PlayerEntityModel<T>> {
    public CommonPlayerLikeEntityRenderer(EntityRendererFactory.Context ctx) {
        this(ctx, false);
    }

    public CommonPlayerLikeEntityRenderer(EntityRendererFactory.Context ctx, boolean slim) {
        super(ctx, new PlayerEntityModel<>(ctx.getPart(EntityModelLayers.PLAYER), slim), 0.5F);
    }

    @Override
    protected void scale(T entity, MatrixStack matrices, float amount) {
        float f = entity.getScale();
        if (f != 1)
            matrices.scale(f, f, f);
    }

    @Override
    public Identifier getTexture(T entity) {
        return entity.getTextureId();
    }
}
