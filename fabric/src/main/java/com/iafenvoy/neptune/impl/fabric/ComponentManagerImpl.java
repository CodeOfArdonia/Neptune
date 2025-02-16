package com.iafenvoy.neptune.impl.fabric;


import com.iafenvoy.neptune.fabric.component.PowerComponent;
import com.iafenvoy.neptune.power.PowerData;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class ComponentManagerImpl {
    public static PowerData getPowerData(@NotNull PlayerEntity player) {
        return PowerComponent.COMPONENT.get(player).getData();
    }
}
