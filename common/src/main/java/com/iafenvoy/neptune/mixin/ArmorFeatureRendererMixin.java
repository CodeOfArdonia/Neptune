package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.accessory.AccessoryManager;
import com.iafenvoy.neptune.render.armor.IArmorRendererBase;
import com.iafenvoy.neptune.render.armor.IArmorTextureProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    public ArmorFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Inject(method = "getArmorTexture", at = @At("TAIL"), cancellable = true)
    private void pushTexture(ArmorItem item, boolean secondLayer, String overlay, CallbackInfoReturnable<Identifier> cir) {
        if (item instanceof IArmorTextureProvider provider)
            cir.setReturnValue(provider.getArmorTexture(item.getDefaultStack(), null, item.getSlotType(), overlay));
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void onRenderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
        ItemStack stack = entity.getEquippedStack(armorSlot);
        @SuppressWarnings("unchecked")
        IArmorRendererBase<T> renderer = (IArmorRendererBase<T>) IArmorRendererBase.RENDERERS.get(stack.getItem());
        if (renderer != null) {
            renderer.render(matrices, vertexConsumers, entity, armorSlot, light, stack, this.getContextModel());
            ci.cancel();
        }
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At("RETURN"))
    private void renderAccessories(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        for (AccessoryManager.Place place : List.of(AccessoryManager.Place.HAT, AccessoryManager.Place.NECKLACE, AccessoryManager.Place.FEET)) {
            ItemStack stack = AccessoryManager.getEquipped(livingEntity, place);
            if (stack.isEmpty()) continue;
            @SuppressWarnings("unchecked")
            IArmorRendererBase<T> renderer = (IArmorRendererBase<T>) IArmorRendererBase.RENDERERS.get(stack.getItem());
            if (renderer != null)
                renderer.render(matrices, vertexConsumers, livingEntity, place.getSlot(), i, stack, this.getContextModel());
        }
    }
}
