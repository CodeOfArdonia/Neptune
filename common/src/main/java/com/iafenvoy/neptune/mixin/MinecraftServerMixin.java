package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.power.PowerData;
import com.iafenvoy.neptune.util.Timeout;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onServerCreated(CallbackInfo ci) {
        NeptuneConstants.server = (MinecraftServer) (Object) this;
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void endTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        Timeout.runTimeout((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("HEAD"), method = "shutdown")
    private void beforeShutdownServer(CallbackInfo info) {
        PowerData.stop((MinecraftServer) (Object) this);
    }
}
