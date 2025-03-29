package com.iafenvoy.neptune.trail;

import com.iafenvoy.neptune.trail.storage.ServerTrailStorage;
import com.iafenvoy.neptune.trail.storage.TrailStorage;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public final class TrailManager {
    public static TrailStorage PROXY = ServerTrailStorage.INSTANCE;

    public static void addTrail(Entity entity, Identifier id) {
        if (!entity.getWorld().isClient) ServerTrailStorage.INSTANCE.addTrail(entity, id);
    }

    public static void removeTrail(Entity entity, Identifier id) {
        if (!entity.getWorld().isClient) ServerTrailStorage.INSTANCE.removeTrail(entity, id);
    }
}
