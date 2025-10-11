package com.iafenvoy.neptune.world;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class RaycastHelper {
    @Nullable
    public static EntityHitResult raycastNearest(LivingEntity entity, double maxDistance) {
        Vec3 p1 = entity.getEyePosition(), p2 = p1.add(entity.getViewVector(1).scale(maxDistance)), r = new Vec3(maxDistance, maxDistance, maxDistance);
        return ProjectileUtil.getEntityHitResult(entity, p1, p2, new AABB(entity.position().add(r), entity.position().subtract(r)), e -> e instanceof LivingEntity, maxDistance * maxDistance);
    }

    public static List<EntityHitResult> raycastAll(Entity entity, Vec3 min, Vec3 max, AABB box, Predicate<Entity> predicate, double d) {
        Level world = entity.level();
        List<EntityHitResult> results = new ArrayList<>();
        for (Entity entity3 : world.getEntities(entity, box, predicate)) {
            AABB box2 = entity3.getBoundingBox().inflate(entity3.getPickRadius());
            Optional<Vec3> optional = box2.clip(min, max);
            if (box2.contains(min)) results.add(new EntityHitResult(entity3, optional.orElse(min)));
            else if (optional.isPresent()) {
                Vec3 vec3d2 = optional.get();
                double f = min.distanceToSqr(vec3d2);
                if (f < d || d == 0.0) results.add(new EntityHitResult(entity3, vec3d2));
            }
        }
        return results;
    }
}
