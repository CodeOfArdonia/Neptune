package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.render.SkullRenderRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(CustomHeadLayer.class)
public class HeadFeatureRendererMixin<T extends LivingEntity> {
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    private void cancelRender(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        ItemStack head = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (head.getItem() instanceof PlayerHeadItem skullItem && skullItem.getBlock() instanceof AbstractSkullBlock skullBlock)
            if (SkullRenderRegistry.getTextureFromType(skullBlock.getType()) != null || skullBlock.getType() == SkullBlock.Types.PLAYER)
                ci.cancel();
    }
}
