package com.iafenvoy.neptune.render.accessory;

import com.iafenvoy.neptune.accessory.AccessoryManager;
import com.iafenvoy.neptune.registry.NeptuneTags;
import com.iafenvoy.neptune.render.armor.IArmorRenderHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RotationAxis;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class BackToolRenderer extends HeldItemFeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private final HeldItemRenderer heldItemRenderer;

    public BackToolRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, HeldItemRenderer heldItemRenderer) {
        super(context, heldItemRenderer);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider provider, int i, AbstractClientPlayerEntity entity, float f, float g, float h, float j, float k, float l) {
        Map<AccessoryManager.Place, ItemStack> stacks = AccessoryManager.getAllEquipped(entity);
        if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA)
            return;
        if (stacks.containsKey(AccessoryManager.Place.BACK_LEFT))
            this.renderItem(stacks.get(AccessoryManager.Place.BACK_LEFT), matrices, provider, i, entity, true);
        if (stacks.containsKey(AccessoryManager.Place.BACK_RIGHT))
            this.renderItem(stacks.get(AccessoryManager.Place.BACK_RIGHT), matrices, provider, i, entity, false);
    }

    private void renderItem(ItemStack stack, MatrixStack matrices, VertexConsumerProvider provider, int i, AbstractClientPlayerEntity entity, boolean left) {
        matrices.push();
        IArmorRenderHelper.translateToChest(matrices, this.getContextModel(), entity);
        matrices.translate(0, 0, 0.3);
        if (stack.isIn(NeptuneTags.RENDER_BIG_WEAPON)) matrices.translate(0, 0.3, 0);
        if (!entity.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) matrices.translate(0, 0, 0.05);
        if (left) matrices.translate(0, 0, 0.05);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
        if (left) matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(145));
        if (stack.isIn(NeptuneTags.RENDER_REVERSE_WEAPON)) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
            matrices.translate(0, -0.4, -0.4);
        }
        matrices.translate(0, -0.2, 0.1);
        AccessoryManager.BackHolder holder = AccessoryManager.getBack(stack.getItem());
        if (holder != null) holder.transformer().accept(matrices, left);
        this.heldItemRenderer.renderItem(entity, stack, ModelTransformationMode.THIRD_PERSON_LEFT_HAND, false, matrices, provider, i);
        matrices.pop();
    }
}