package com.iafenvoy.neptune.impl.forge;

import com.iafenvoy.neptune.forge.component.PowerDataProvider;
import com.iafenvoy.neptune.forge.component.PowerDataStorage;
import com.iafenvoy.neptune.power.PowerData;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class ComponentManagerImpl {
    public static PowerData getPowerData(@NotNull PlayerEntity player) {
        return player.getCapability(PowerDataProvider.CAPABILITY).resolve().map(PowerDataStorage::getData).orElse(new PowerData(player));
    }
}
