package com.iafenvoy.neptune.impl;

import com.iafenvoy.neptune.ability.AbilityData;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class ComponentManager {
    @ExpectPlatform
    public static AbilityData getAbilityData(@NotNull PlayerEntity player) {
        throw new AssertionError("This method should be replaced by Architectury");
    }
}
