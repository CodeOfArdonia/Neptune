package com.iafenvoy.neptune.trail.storage;

import com.iafenvoy.neptune.Neptune;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface TrailStorage {
    int MAX_LIGHT = 15728880;
    Identifier SYNC_TRAIL = Identifier.of(Neptune.MOD_ID, "sync_trail");
    Identifier ADD_TRAIL = Identifier.of(Neptune.MOD_ID, "add_trail");
    Identifier REMOVE_TRAIL = Identifier.of(Neptune.MOD_ID, "remove_trail");

    void addTrail(Entity entity, Identifier id);

    void removeTrail(Entity entity, Identifier id);

    int getEntityLight(Entity entity, float tickDelta);
}
