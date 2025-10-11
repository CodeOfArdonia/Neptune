package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.compat.CuriosHelper;
import com.iafenvoy.neptune.render.armor.IArmorRendererBase;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> context) {
        super(context);
    }

    @Inject(method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    private void onRenderArmor(PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, EquipmentSlot slot, int packedLight, A p_model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = livingEntity.getItemBySlot(slot);
        @SuppressWarnings("unchecked")
        IArmorRendererBase<T> renderer = (IArmorRendererBase<T>) IArmorRendererBase.RENDERERS.get(stack.getItem());
        if (renderer != null) {
            renderer.render(poseStack, bufferSource, livingEntity, slot, packedLight, stack, this.getParentModel());
            ci.cancel();
        }
    }

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("RETURN"))
    private void renderAccessories(PoseStack matrices, MultiBufferSource vertexConsumers, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        Map<CuriosHelper.Place, ItemStack> equipped = CuriosHelper.getEquipped(livingEntity);
        for (CuriosHelper.Place place : List.of(CuriosHelper.Place.HAT, CuriosHelper.Place.NECKLACE, CuriosHelper.Place.FEET)) {
            ItemStack stack = equipped.get(place);
            if (stack == null || stack.isEmpty()) continue;
            @SuppressWarnings("unchecked")
            IArmorRendererBase<T> renderer = (IArmorRendererBase<T>) IArmorRendererBase.RENDERERS.get(stack.getItem());
            if (renderer != null)
                renderer.render(matrices, vertexConsumers, livingEntity, place.getSlot(), i, stack, this.getParentModel());
        }
    }
}
