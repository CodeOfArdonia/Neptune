package com.iafenvoy.neptune.forge.component;

import com.iafenvoy.neptune.ability.AbilityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class AbilityDataStorage implements ITickableCapability {
    private final AbilityData playerData;

    public AbilityDataStorage(PlayerEntity player) {
        this.playerData = new AbilityData(player);
    }

    @Override
    public NbtCompound serializeNBT() {
        return this.playerData.encode();
    }

    @Override
    public void deserializeNBT(NbtCompound compound) {
        this.playerData.decode(compound);
    }

    public AbilityData getData() {
        return this.playerData;
    }

    @Override
    public void tick() {
        this.playerData.tick();
    }

    @Override
    public boolean isDirty() {
        return this.playerData.isDirty();
    }
}
