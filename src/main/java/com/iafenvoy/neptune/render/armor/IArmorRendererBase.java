package com.iafenvoy.neptune.render.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public interface IArmorRendererBase<T extends LivingEntity> {
    HashMap<ItemLike, IArmorRendererBase<? extends LivingEntity>> RENDERERS = new HashMap<>();

    HumanoidModel<T> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<T> defaultModel);

    ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot);

    default void render(PoseStack matrices, MultiBufferSource vertexConsumers, LivingEntity entity, EquipmentSlot slot, int light, ItemStack stack, HumanoidModel<T> defaultModel) {
        HumanoidModel<T> armorModel = this.getHumanoidArmorModel(entity, stack, slot, defaultModel);
        defaultModel.copyPropertiesTo(armorModel);
        armorModel.head.visible = slot == EquipmentSlot.HEAD;
        armorModel.hat.visible = slot == EquipmentSlot.HEAD;
        armorModel.body.visible = slot == EquipmentSlot.CHEST;
        armorModel.leftArm.visible = slot == EquipmentSlot.CHEST;
        armorModel.rightArm.visible = slot == EquipmentSlot.CHEST;
        armorModel.leftLeg.visible = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
        armorModel.rightLeg.visible = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderType.armorCutoutNoCull(this.getArmorTexture(stack, entity, slot)));
        armorModel.renderToBuffer(matrices, consumer, light, OverlayTexture.NO_OVERLAY, -1);
    }

    static <T extends LivingEntity> void register(IArmorRendererBase<T> renderer, ItemLike... items) {
        Arrays.stream(items).forEach(x -> RENDERERS.put(x, renderer));
    }
}
