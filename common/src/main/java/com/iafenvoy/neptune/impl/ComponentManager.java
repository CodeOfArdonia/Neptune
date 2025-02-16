package com.iafenvoy.neptune.impl;

import com.iafenvoy.neptune.power.PowerData;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class ComponentManager {
    @ExpectPlatform
    public static PowerData getPowerData(@NotNull PlayerEntity player) {
        throw new AssertionError("This method should be replaced by Architectury");
    }
}
