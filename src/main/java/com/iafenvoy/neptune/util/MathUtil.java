package com.iafenvoy.neptune.util;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class MathUtil {
    public static float positionToPitch(Vec3 vec3) {
        return positionToPitch(vec3.x, vec3.y, vec3.z);
    }

    public static float positionToYaw(Vec3 vec3) {
        return positionToYaw(vec3.x, vec3.z);
    }

    public static float positionToPitch(double diffX, double diffY, double diffZ) {
        double horizontalDist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        return !(Math.abs(diffY) > (double) 1.0E-5F) && !(Math.abs(horizontalDist) > (double) 1.0E-5F) ? 0 : (float) ((Mth.atan2(diffY, horizontalDist) * Mth.DEG_TO_RAD));
    }

    public static float positionToYaw(double diffX, double diffZ) {
        return !(Math.abs(diffZ) > (double) 1.0E-5F) && !(Math.abs(diffX) > (double) 1.0E-5F) ? 0 : (float) (Mth.atan2(diffZ, diffX) * Mth.DEG_TO_RAD);
    }

    public static Vec3 rotationToPosition(float radius, float pitch, float yaw) {
        double endPosX = radius * Math.cos(yaw * Mth.RAD_TO_DEG) * Math.cos(pitch * Mth.RAD_TO_DEG);
        double endPosY = radius * Math.sin(pitch * Mth.RAD_TO_DEG);
        double endPosZ = radius * Math.sin(yaw * Mth.RAD_TO_DEG) * Math.cos(pitch * Mth.RAD_TO_DEG);
        return new Vec3(endPosX, endPosY, endPosZ);
    }

    public static Vec3 rotationToPosition(Vec3 startPos, float radius, float pitch, float yaw) {
        return startPos.add(rotationToPosition(radius, pitch, yaw));
    }

    public static Vec3 lerpVec(float progress, Vec3 from, Vec3 to) {
        return new Vec3(Mth.lerp(progress, from.x, to.x), Mth.lerp(progress, from.y, to.y), Mth.lerp(progress, from.z, to.z));
    }

    public static Vec3 rotateClockwise(Vec3 vec, double degrees) {
        double x = vec.x, z = vec.z, radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double newX = x * cos + z * sin;
        double newZ = -x * sin + z * cos;
        return new Vec3(newX, vec.y, newZ);
    }

    public static Vec3 rotateCounterClockwise(Vec3 vec, double degrees) {
        double x = vec.x, z = vec.z, radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double newX = x * cos - z * sin;
        double newZ = x * sin + z * cos;
        return new Vec3(newX, vec.y, newZ);
    }
}
