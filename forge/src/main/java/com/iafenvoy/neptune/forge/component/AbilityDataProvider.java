package com.iafenvoy.neptune.forge.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbilityDataProvider implements ICapabilitySerializable<NbtCompound> {
    public static final Capability<AbilityDataStorage> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    private AbilityDataStorage storage;
    private final LazyOptional<AbilityDataStorage> storageLazyOptional = LazyOptional.of(this::getOrCreateStorage);
    private final PlayerEntity player;

    public AbilityDataProvider(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return CAPABILITY.orEmpty(capability, this.storageLazyOptional);
    }

    @Override
    public NbtCompound serializeNBT() {
        return this.getOrCreateStorage().serializeNBT();
    }

    @Override
    public void deserializeNBT(NbtCompound arg) {
        this.getOrCreateStorage().deserializeNBT(arg);
    }

    private AbilityDataStorage getOrCreateStorage() {
        if (this.storage == null)
            this.storage = new AbilityDataStorage(this.player);
        return this.storage;
    }
}
