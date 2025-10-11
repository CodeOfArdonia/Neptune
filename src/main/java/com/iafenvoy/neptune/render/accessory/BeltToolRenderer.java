package com.iafenvoy.neptune.render.accessory;

import com.iafenvoy.neptune.compat.CuriosHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class BeltToolRenderer extends ItemInHandLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final ItemInHandRenderer heldItemRenderer;

    public BeltToolRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> context, ItemInHandRenderer heldItemRenderer) {
        super(context, heldItemRenderer);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(@NotNull PoseStack matrices, @NotNull MultiBufferSource provider, int i, @NotNull AbstractClientPlayer entity, float f, float g, float h, float j, float k, float l) {
        Map<CuriosHelper.Place, ItemStack> stacks = CuriosHelper.getEquipped(entity);
        if (stacks.containsKey(CuriosHelper.Place.BELT_LEFT))
            this.renderItem(stacks.get(CuriosHelper.Place.BELT_LEFT), matrices, provider, i, entity, true);
        if (stacks.containsKey(CuriosHelper.Place.BELT_RIGHT))
            this.renderItem(stacks.get(CuriosHelper.Place.BELT_RIGHT), matrices, provider, i, entity, false);
    }

    private void renderItem(ItemStack stack, PoseStack matrices, MultiBufferSource provider, int i, AbstractClientPlayer entity, boolean left) {
        matrices.pushPose();
        ModelPart modelPart = this.getParentModel().body;
        modelPart.translateAndRotate(matrices);
        double switchBeltSide = 0.29D;
        matrices.translate(switchBeltSide * (left ? 1 : -1), 0.5D, 0.05D);
        matrices.mulPose(Axis.YP.rotationDegrees(-90.0F));
        matrices.scale(1.5f, 1.5f, 1.5f);
        CuriosHelper.BeltHolder holder = CuriosHelper.getBelt(stack.getItem());
        if (holder != null) holder.transformer().accept(matrices, left);
        this.heldItemRenderer.renderItem(entity, stack, ItemDisplayContext.GROUND, left, matrices, provider, i);
        matrices.popPose();
    }
}