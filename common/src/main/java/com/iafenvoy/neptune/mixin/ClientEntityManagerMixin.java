package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.trail.storage.ClientTrailStorage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientEntityManager;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientEntityManager.class)
public class ClientEntityManagerMixin<T extends EntityLike> {
    @Inject(method = "addEntity", at = @At("RETURN"))
    private void onNewEntityOnClient(T entity, CallbackInfo ci) {
        ClientTrailStorage.onNewEntityOnClient(entity.getId());
    }
}
