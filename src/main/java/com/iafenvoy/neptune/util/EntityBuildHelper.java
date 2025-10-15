package com.iafenvoy.neptune.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class EntityBuildHelper {
    public static final Dimension PLAYER = new Dimension(0.6F, 1.8F);

    public static <T extends Entity> Supplier<EntityType<T>> build(String name, EntityType.EntityFactory<T> constructor, MobCategory category, int trackingRange, int updateInterval, boolean fireImmune, Dimension dimension) {
        return () -> {
            EntityType.Builder<T> builder = EntityType.Builder.of(constructor, category).clientTrackingRange(trackingRange).updateInterval(updateInterval).sized(dimension.sizeX, dimension.sizeY);
            if (fireImmune) builder.fireImmune();
            return builder.build(name);
        };
    }

    public record Dimension(float sizeX, float sizeY) {
        public Dimension scale(float scale) {
            return new Dimension(this.sizeX * scale, this.sizeY * scale);
        }
    }
}
