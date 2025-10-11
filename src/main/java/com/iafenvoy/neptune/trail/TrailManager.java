package com.iafenvoy.neptune.trail;

import com.iafenvoy.neptune.trail.storage.ServerTrailStorage;
import com.iafenvoy.neptune.trail.storage.TrailStorage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public final class TrailManager {
    public static TrailStorage PROXY = ServerTrailStorage.INSTANCE;

    public static void addTrail(Entity entity, ResourceLocation id) {
        if (!entity.level().isClientSide) ServerTrailStorage.INSTANCE.addTrail(entity, id);
    }

    public static void removeTrail(Entity entity, ResourceLocation id) {
        if (!entity.level().isClientSide) ServerTrailStorage.INSTANCE.removeTrail(entity, id);
    }
}
