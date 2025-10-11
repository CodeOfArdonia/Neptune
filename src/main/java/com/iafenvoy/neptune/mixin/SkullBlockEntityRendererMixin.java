package com.iafenvoy.neptune.mixin;

import com.google.common.collect.ImmutableMap;
import com.iafenvoy.neptune.render.SkullRenderRegistry;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mixin(SkullBlockRenderer.class)
public class SkullBlockEntityRendererMixin {
    @Shadow
    @Final
    public static Map<SkullBlock.Type, ResourceLocation> SKIN_BY_TYPE;

    //FIXME::Event
    @Inject(method = "createSkullRenderers", at = @At("TAIL"), cancellable = true)
    private static void registerSkull(EntityModelSet modelLoader, CallbackInfoReturnable<Map<SkullBlock.Type, SkullModelBase>> cir) {
        ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();
        builder.putAll(cir.getReturnValue());
        builder.putAll(SkullRenderRegistry.getSkulls(SKIN_BY_TYPE, modelLoader));
        cir.setReturnValue(builder.build());
    }
}
