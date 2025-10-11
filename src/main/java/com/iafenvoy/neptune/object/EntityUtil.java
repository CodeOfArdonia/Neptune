package com.iafenvoy.neptune.object;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class EntityUtil {
    public static <M extends Mob> void summon(EntityType<M> entityType, ServerLevel world, double x, double y, double z) {
        summon(entityType, world, x, y, z, world.getRandom().nextFloat() * 360F);
    }

    public static <M extends Mob> void summon(EntityType<M> entityType, ServerLevel world, double x, double y, double z, float yaw) {
        Mob entityToSpawn = entityType.create(world);
        if (entityToSpawn != null) {
            entityToSpawn.moveTo(x, y, z, yaw, 0);
            entityToSpawn.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null);
            world.addFreshEntity(entityToSpawn);
        }
    }

    public static void lightening(ServerLevel world, double x, double y, double z) {
        lightening(world, x, y, z, true);
    }

    public static void lightening(ServerLevel world, double x, double y, double z, boolean cosmetic) {
        LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(world);
        if (entityToSpawn != null) {
            entityToSpawn.moveTo(VecUtil.createBottomCenter(x, y, z));
            entityToSpawn.setVisualOnly(cosmetic);
            world.addFreshEntity(entityToSpawn);
        }
    }

    public static void item(ServerLevel world, double x, double y, double z, ItemLike item, int pickUpDelay) {
        item(world, x, y, z, new ItemStack(item), pickUpDelay);
    }

    public static void item(ServerLevel world, double x, double y, double z, ItemStack item, int pickUpDelay) {
        ItemEntity entityToSpawn = new ItemEntity(world, x, y + 1.0d, z, item);
        entityToSpawn.setPickUpDelay(pickUpDelay);
        world.addFreshEntity(entityToSpawn);
    }
}
