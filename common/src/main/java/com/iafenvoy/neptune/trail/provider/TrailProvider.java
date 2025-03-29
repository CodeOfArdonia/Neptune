package com.iafenvoy.neptune.trail.provider;

import com.iafenvoy.neptune.trail.render.TrailHolder;
import com.iafenvoy.neptune.trail.storage.TrailStorage;
import com.iafenvoy.neptune.util.Color4i;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;

public interface TrailProvider extends Position {
    TrailHolder createTail();

    void updateTrail(TrailHolder effect);

    Vec3d getCurrentPos();

    Color4i getTrailColor();

    default double getX() {
        return this.getCurrentPos().x;
    }

    default double getY() {
        return this.getCurrentPos().y;
    }

    default double getZ() {
        return this.getCurrentPos().z;
    }

    default TrailHolder.TrailPoint adjustPoint(TrailHolder.TrailPoint point, boolean vertical, float partialTicks) {
        return point;
    }

    default int getTrailLight(float tickDelta) {
        return TrailStorage.MAX_LIGHT;
    }

    default boolean shouldRenderHorizontal() {
        return true;
    }

    default int getMaxTrailLength() {
        return 32;
    }

    default double maxDistance() {
        return 12 * 16;
    }
}
