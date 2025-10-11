package com.iafenvoy.neptune.object;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class DamageUtil {
    public static DamageSource build(Level world, DamageSource origin, ResourceKey<DamageType> newType) {
        Registry<DamageType> registry = world.damageSources().damageTypes;
        DamageType type = registry.get(newType);
        return new DamageSource(registry.wrapAsHolder(type), origin.getDirectEntity(), origin.getEntity());
    }

    public static DamageSource build(Entity entity, ResourceKey<DamageType> newType) {
        Registry<DamageType> registry = entity.damageSources().damageTypes;
        DamageType type = registry.get(newType);
        return new DamageSource(registry.wrapAsHolder(type), entity, entity);
    }
}
