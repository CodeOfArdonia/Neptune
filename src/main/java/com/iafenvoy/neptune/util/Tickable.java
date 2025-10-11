package com.iafenvoy.neptune.util;

import net.minecraft.world.entity.LivingEntity;

public interface Tickable {
    void tick(LivingEntity living);

    boolean isDirty();
}
