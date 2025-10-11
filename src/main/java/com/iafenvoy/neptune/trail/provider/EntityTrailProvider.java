package com.iafenvoy.neptune.trail.provider;

import com.iafenvoy.neptune.trail.TrailManager;
import com.iafenvoy.neptune.trail.render.TrailHolder;
import com.iafenvoy.neptune.util.Color4i;
import com.iafenvoy.neptune.util.MathUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class EntityTrailProvider<T extends Entity> implements TrailProvider {
    private final T entity;
    private final Color4i color;
    private final float width;
    private final int length;
    private final Vec3 offset;
    private final boolean followRotation;

    protected EntityTrailProvider(T entity, Color4i color, float width, int length, Vec3 offset, boolean followRotation) {
        this.entity = entity;
        this.color = color;
        this.width = width;
        this.length = length;
        this.offset = offset;
        this.followRotation = followRotation;
    }

    @Override
    public TrailHolder createTail() {
        return new TrailHolder(this, this.width, this.length);
    }

    @Override
    public void updateTrail(TrailHolder effect) {
        Vec3 oldPos = new Vec3(this.entity.xo, this.entity.yo, this.entity.zo);
        effect.update(oldPos.add(0, this.entity.getBbHeight() / 2, 0), this.entity.position().subtract(oldPos));
        if (this.entity.isRemoved()) effect.setLength(Math.max(effect.getLength() - 0.9f, 0));
    }

    @Override
    public Vec3 getCurrentPos() {
        return this.entity.position();
    }

    @Override
    public Color4i getTrailColor() {
        return this.color;
    }

    @Override
    public TrailHolder.TrailPoint adjustPoint(TrailHolder.TrailPoint point, boolean vertical, float partialTicks) {
        return point.offset(this.followRotation ? MathUtil.rotateCounterClockwise(this.offset, this.entity.getYRot()) : this.offset);
    }

    @Override
    public int getTrailLight(float tickDelta) {
        return TrailManager.PROXY.getEntityLight(this.entity, tickDelta);
    }

    public static <T extends Entity> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends Entity> {
        protected Color4i color = new Color4i(160, 164, 195, 255);
        protected float width = 0.3f;
        protected int length = 15;
        protected Vec3 offset = Vec3.ZERO;
        protected boolean followRotation;

        public Builder<T> color(Color4i color) {
            this.color = color;
            return this;
        }

        public Builder<T> width(float width) {
            this.width = width;
            return this;
        }

        public Builder<T> length(int length) {
            this.length = length;
            return this;
        }

        public Builder<T> offset(Vec3 offset, boolean followRotation) {
            this.offset = offset;
            this.followRotation = followRotation;
            return this;
        }

        public EntityTrailProvider<T> build(T entity) {
            return new EntityTrailProvider<>(entity, this.color, this.width, this.length, this.offset, this.followRotation);
        }
    }
}
