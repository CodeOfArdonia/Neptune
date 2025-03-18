package com.iafenvoy.neptune.impl.fabric;


import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.fabric.component.AbilityComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class ComponentManagerImpl {
    public static AbilityData getAbilityData(@NotNull PlayerEntity player) {
        return AbilityComponent.COMPONENT.get(player).getData();
    }
}
