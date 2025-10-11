package com.iafenvoy.neptune.trail.render;

import com.iafenvoy.neptune.trail.provider.TrailProvider;
import com.iafenvoy.neptune.util.MathUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class TrailHolder {
    private final List<TrailPoint> verticalPoints = new LinkedList<>();
    private final List<TrailPoint> horizontalPoints = new LinkedList<>();
    private final List<TrailPoint> verticalRenderPoints = new LinkedList<>();
    private final List<TrailPoint> horizontalRenderPoints = new LinkedList<>();
    private final TrailProvider provider;
    private final float width;
    private float oldLength;
    private float length;
    private final int ordinal;

    public TrailHolder(TrailProvider provider, float width, int length) {
        this(provider, width, length, 0);
    }

    public TrailHolder(TrailProvider provider, float width, int length, int ordinal) {
        this.provider = provider;
        this.width = width;
        this.length = length;
        this.ordinal = ordinal;
    }

    public List<TrailPoint> getVerticalRenderPoints() {
        return this.verticalRenderPoints;
    }

    public List<TrailPoint> getHorizontalRenderPoints() {
        return this.horizontalRenderPoints;
    }

    public float getWidth() {
        return this.width;
    }

    public float getLength() {
        return this.length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    private List<TrailPoint> getPoints(boolean vertical) {
        return vertical ? this.verticalPoints : this.horizontalPoints;
    }

    public void update(TrailPoint point, boolean vertical) {
        List<TrailPoint> points = this.getPoints(vertical);
        points.add(0, point);
        if (points.size() > this.provider.getMaxTrailLength())
            points.remove(points.size() - 1);
    }

    public void update(Vec3 pos, Vec3 delta) {
        this.oldLength = this.length;
        float yaw = MathUtil.positionToYaw(delta);
        float pitch = MathUtil.positionToPitch(delta);
        Vec3 upper = MathUtil.rotationToPosition(pos, this.width / 2f, pitch - 90, yaw);
        Vec3 lower = MathUtil.rotationToPosition(pos, this.width / 2f, pitch + 90, yaw);
        this.update(new TrailPoint(upper, lower), true);
        Vec3 offset = upper.subtract(lower).cross(delta).normalize().scale(this.width / 2);
        Vec3 upper1 = pos.add(offset);
        Vec3 lower1 = pos.add(offset.scale(-1));
        this.update(new TrailPoint(upper1, lower1), false);
    }

    public void prepareRender(Vec3 pos, Vec3 delta, float partialTicks) {
        float yaw = MathUtil.positionToYaw(delta);
        float pitch = MathUtil.positionToPitch(delta);
        Vec3 upper = MathUtil.rotationToPosition(pos, this.width / 2f, pitch - 90, yaw);
        Vec3 lower = MathUtil.rotationToPosition(pos, this.width / 2f, pitch + 90, yaw);
        this.verticalRenderPoints.clear();
        this.verticalRenderPoints.addAll(this.verticalPoints);
        this.prepare(new TrailPoint(upper, lower), true, partialTicks);
        Vec3 offset = upper.subtract(lower).cross(delta).normalize().scale(this.width / 2);
        Vec3 upper1 = pos.add(offset);
        Vec3 lower1 = pos.add(offset.scale(-1));
        this.horizontalRenderPoints.clear();
        this.horizontalRenderPoints.addAll(this.horizontalPoints);
        this.prepare(new TrailPoint(upper1, lower1), false, partialTicks);
    }

    private void prepare(TrailPoint point, boolean vertical, float partialTicks) {
        List<TrailPoint> points = vertical ? this.verticalRenderPoints : this.horizontalRenderPoints;
        List<TrailPoint> modified = new ArrayList<>();
        points.add(0, point);
        float totalLength = 0;
        float renderLength = Mth.lerp(partialTicks, this.oldLength, this.length);
        for (int i = 0; i < points.size() - 1; i++) {
            TrailPoint from = points.get(i);
            TrailPoint to = points.get(i + 1);
            float distance = (float) from.center().distanceTo(to.center());
            totalLength += distance;
            if (totalLength > renderLength) {
                points.set(i + 1, this.interpolateTrailPoint((totalLength - renderLength) / distance, to, from));
                modified.addAll(points.subList(0, i + 2));
                totalLength = renderLength;
                break;
            }
        }
        if (!modified.isEmpty()) {
            points.clear();
            points.addAll(modified);
        }
        float currentLength = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            TrailPoint from = points.get(i);
            TrailPoint to = points.get(i + 1);
            float distance = (float) from.center().distanceTo(to.center());
            points.set(i, points.get(i).withWidth((totalLength - currentLength) * (this.width / totalLength)));
            currentLength += distance;
        }
        if (points.size() > 1) points.set(points.size() - 1, points.get(points.size() - 1).withWidth(0.01f));
    }

    private TrailPoint interpolateTrailPoint(float progress, TrailPoint first, TrailPoint second) {
        return new TrailPoint(MathUtil.lerpVec(progress, first.upper(), second.upper()), MathUtil.lerpVec(progress, first.lower(), second.lower()));
    }

    public record TrailPoint(Vec3 upper, Vec3 lower) {
        public Vec3 center() {
            return this.lower().add(this.upper().subtract(this.lower()).scale(0.5));
        }

        public float width() {
            return (float) this.upper().distanceTo(this.lower());
        }

        public TrailPoint withWidth(float width) {
            Vec3 center = this.center();
            Vec3 upperVec = this.upper().subtract(center);
            Vec3 lowerVec = this.lower().subtract(center);
            return new TrailPoint(center.add(upperVec.normalize().scale(width / 2)), center.add(lowerVec.normalize().scale(width / 2)));
        }

        public TrailPoint offset(Vec3 vec3d) {
            return new TrailPoint(this.upper.add(vec3d), this.lower.add(vec3d));
        }
    }
}
