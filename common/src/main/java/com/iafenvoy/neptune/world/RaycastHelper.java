package com.iafenvoy.neptune.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class RaycastHelper {
    @Nullable
    public static EntityHitResult raycastNearest(LivingEntity entity, double maxDistance) {
        Vec3d p1 = entity.getEyePos(), p2 = p1.add(entity.getRotationVec(1).multiply(maxDistance)), r = new Vec3d(maxDistance, maxDistance, maxDistance);
        return ProjectileUtil.raycast(entity, p1, p2, new Box(entity.getPos().add(r), entity.getPos().subtract(r)), e -> e instanceof LivingEntity, maxDistance * maxDistance);
    }

    public static List<EntityHitResult> raycastAll(Entity entity, Vec3d min, Vec3d max, Box box, Predicate<Entity> predicate, double d) {
        World world = entity.getWorld();
        List<EntityHitResult> results = new ArrayList<>();
        for (Entity entity3 : world.getOtherEntities(entity, box, predicate)) {
            Box box2 = entity3.getBoundingBox().expand(entity3.getTargetingMargin());
            Optional<Vec3d> optional = box2.raycast(min, max);
            if (box2.contains(min)) results.add(new EntityHitResult(entity3, optional.orElse(min)));
            else if (optional.isPresent()) {
                Vec3d vec3d2 = optional.get();
                double f = min.squaredDistanceTo(vec3d2);
                if (f < d || d == 0.0) results.add(new EntityHitResult(entity3, vec3d2));
            }
        }
        return results;
    }
}
