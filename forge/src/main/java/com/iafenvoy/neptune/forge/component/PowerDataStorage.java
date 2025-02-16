package com.iafenvoy.neptune.forge.component;

import com.iafenvoy.neptune.power.PowerData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PowerDataStorage implements ITickableCapability {
    private final PowerData playerData;

    public PowerDataStorage(PlayerEntity player) {
        this.playerData = new PowerData(player);
    }

    @Override
    public NbtCompound serializeNBT() {
        return this.playerData.encode();
    }

    @Override
    public void deserializeNBT(NbtCompound compound) {
        this.playerData.decode(compound);
    }

    public PowerData getData() {
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
