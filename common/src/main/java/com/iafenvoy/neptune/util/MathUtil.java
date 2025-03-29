package com.iafenvoy.neptune.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathUtil {
    public static float positionToPitch(Vec3d vec3) {
        return positionToPitch(vec3.x, vec3.y, vec3.z);
    }

    public static float positionToYaw(Vec3d vec3) {
        return positionToYaw(vec3.x, vec3.z);
    }

    public static float positionToPitch(double diffX, double diffY, double diffZ) {
        double horizontalDist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        return !(Math.abs(diffY) > (double) 1.0E-5F) && !(Math.abs(horizontalDist) > (double) 1.0E-5F) ? 0 : (float) ((MathHelper.atan2(diffY, horizontalDist) * MathHelper.RADIANS_PER_DEGREE));
    }

    public static float positionToYaw(double diffX, double diffZ) {
        return !(Math.abs(diffZ) > (double) 1.0E-5F) && !(Math.abs(diffX) > (double) 1.0E-5F) ? 0 : (float) (MathHelper.atan2(diffZ, diffX) * MathHelper.RADIANS_PER_DEGREE);
    }

    public static Vec3d rotationToPosition(float radius, float pitch, float yaw) {
        double endPosX = radius * Math.cos(yaw * MathHelper.DEGREES_PER_RADIAN) * Math.cos(pitch * MathHelper.DEGREES_PER_RADIAN);
        double endPosY = radius * Math.sin(pitch * MathHelper.DEGREES_PER_RADIAN);
        double endPosZ = radius * Math.sin(yaw * MathHelper.DEGREES_PER_RADIAN) * Math.cos(pitch * MathHelper.DEGREES_PER_RADIAN);
        return new Vec3d(endPosX, endPosY, endPosZ);
    }

    public static Vec3d rotationToPosition(Vec3d startPos, float radius, float pitch, float yaw) {
        return startPos.add(rotationToPosition(radius, pitch, yaw));
    }

    public static Vec3d lerpVec(float progress, Vec3d from, Vec3d to) {
        return new Vec3d(MathHelper.lerp(progress, from.x, to.x), MathHelper.lerp(progress, from.y, to.y), MathHelper.lerp(progress, from.z, to.z));
    }

    public static Vec3d rotateClockwise(Vec3d vec, double degrees) {
        double x = vec.x, z = vec.z, radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double newX = x * cos + z * sin;
        double newZ = -x * sin + z * cos;
        return new Vec3d(newX, vec.y, newZ);
    }

    public static Vec3d rotateCounterClockwise(Vec3d vec, double degrees) {
        double x = vec.x, z = vec.z, radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double newX = x * cos - z * sin;
        double newZ = x * sin + z * cos;
        return new Vec3d(newX, vec.y, newZ);
    }
}
