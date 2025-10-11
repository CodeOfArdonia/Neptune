package com.iafenvoy.neptune.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CommonPlayerLikeEntityRenderer<T extends Mob & EntityTextureProvider> extends HumanoidMobRenderer<T, PlayerModel<T>> {
    public CommonPlayerLikeEntityRenderer(EntityRendererProvider.Context ctx) {
        this(ctx, false);
    }

    public CommonPlayerLikeEntityRenderer(EntityRendererProvider.Context ctx, boolean slim) {
        super(ctx, new PlayerModel<>(ctx.bakeLayer(ModelLayers.PLAYER), slim), 0.5F);
    }

    @Override
    protected void scale(T entity, @NotNull PoseStack matrices, float amount) {
        float f = entity.getScale();
        if (f != 1)
            matrices.scale(f, f, f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(T entity) {
        return entity.getTextureId();
    }
}
