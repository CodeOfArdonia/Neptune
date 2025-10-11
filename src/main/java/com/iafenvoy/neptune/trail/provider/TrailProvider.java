package com.iafenvoy.neptune.trail.provider;

import com.iafenvoy.neptune.trail.render.TrailHolder;
import com.iafenvoy.neptune.util.Color4i;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.Position;
import net.minecraft.world.phys.Vec3;

public interface TrailProvider extends Position {
    TrailHolder createTail();

    void updateTrail(TrailHolder effect);

    Vec3 getCurrentPos();

    Color4i getTrailColor();

    @Override
    default double x() {
        return this.getCurrentPos().x;
    }

    @Override
    default double y() {
        return this.getCurrentPos().y;
    }

    @Override
    default double z() {
        return this.getCurrentPos().z;
    }

    default TrailHolder.TrailPoint adjustPoint(TrailHolder.TrailPoint point, boolean vertical, float partialTicks) {
        return point;
    }

    default int getTrailLight(float tickDelta) {
        return LightTexture.FULL_BRIGHT;
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
