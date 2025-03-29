package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.event.PlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "onSpawn", at = @At("RETURN"))
    private void onPlayerSpawn(CallbackInfo ci) {
        PlayerEvents.SPAWN.invoker().onSpawn((ServerPlayerEntity) (Object) this);
    }
}
