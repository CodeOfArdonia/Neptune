package com.iafenvoy.neptune.impl.forge;

import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.forge.component.AbilityDataProvider;
import com.iafenvoy.neptune.forge.component.AbilityDataStorage;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class ComponentManagerImpl {
    public static AbilityData getAbilityData(@NotNull PlayerEntity player) {
        return player.getCapability(AbilityDataProvider.CAPABILITY).resolve().map(AbilityDataStorage::getData).orElse(new AbilityData(player));
    }
}
