package com.iafenvoy.neptune.fraction;

import net.minecraft.entity.mob.MobEntity;

@Deprecated(forRemoval = true)
public interface FractionEntity {
    Fraction getFraction();

    static <T extends MobEntity & FractionEntity> void addTarget(T entity) {
    }
}
