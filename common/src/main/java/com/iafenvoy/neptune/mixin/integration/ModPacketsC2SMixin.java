package com.iafenvoy.neptune.mixin.integration;

import com.iafenvoy.neptune.event.OriginsEvents;
import io.github.apace100.origins.networking.ModPacketsC2S;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(ModPacketsC2S.class)
public class ModPacketsC2SMixin {
    @Inject(method = "confirmOrigin", at = @At("HEAD"))
    private static void onOriginChange(ServerPlayerEntity player, OriginLayer layer, Origin origin, CallbackInfo ci) {
        OriginsEvents.ON_CONFIRM.invoker().accept(player, layer.getIdentifier(), origin.getIdentifier());
    }
}
