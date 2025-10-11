package com.iafenvoy.neptune.render.accessory;

import com.iafenvoy.neptune.compat.CuriosHelper;
import com.iafenvoy.neptune.registry.NeptuneTags;
import com.iafenvoy.neptune.render.armor.IArmorRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class BackToolRenderer extends ItemInHandLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final ItemInHandRenderer heldItemRenderer;

    public BackToolRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> context, ItemInHandRenderer heldItemRenderer) {
        super(context, heldItemRenderer);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(@NotNull PoseStack matrices, @NotNull MultiBufferSource provider, int i, @NotNull AbstractClientPlayer entity, float f, float g, float h, float j, float k, float l) {
        Map<CuriosHelper.Place, ItemStack> stacks = CuriosHelper.getEquipped(entity);
        if (entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.ELYTRA)
            return;
        if (stacks.containsKey(CuriosHelper.Place.BACK_LEFT))
            this.renderItem(stacks.get(CuriosHelper.Place.BACK_LEFT), matrices, provider, i, entity, true);
        if (stacks.containsKey(CuriosHelper.Place.BACK_RIGHT))
            this.renderItem(stacks.get(CuriosHelper.Place.BACK_RIGHT), matrices, provider, i, entity, false);
    }

    private void renderItem(ItemStack stack, PoseStack matrices, MultiBufferSource provider, int i, AbstractClientPlayer entity, boolean left) {
        matrices.pushPose();
        IArmorRenderHelper.translateToChest(matrices, this.getParentModel(), entity);
        matrices.translate(0, 0, 0.3);
        if (stack.is(NeptuneTags.RENDER_BIG_WEAPON)) matrices.translate(0, 0.3, 0);
        if (!entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) matrices.translate(0, 0, 0.05);
        if (left) matrices.translate(0, 0, 0.05);
        matrices.mulPose(Axis.YP.rotationDegrees(90));
        if (left) matrices.mulPose(Axis.YP.rotationDegrees(180));
        matrices.mulPose(Axis.XP.rotationDegrees(145));
        if (stack.is(NeptuneTags.RENDER_REVERSE_WEAPON)) {
            matrices.mulPose(Axis.XP.rotationDegrees(180));
            matrices.translate(0, -0.4, -0.4);
        }
        matrices.translate(0, -0.2, 0.1);
        CuriosHelper.BackHolder holder = CuriosHelper.getBack(stack.getItem());
        if (holder != null) holder.transformer().accept(matrices, left);
        this.heldItemRenderer.renderItem(entity, stack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, false, matrices, provider, i);
        matrices.popPose();
    }
}