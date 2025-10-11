package com.iafenvoy.neptune.util;

import net.minecraft.nbt.CompoundTag;

public interface Serializable {
    Serializable EMPTY = new Serializable() {
        @Override
        public void encode(CompoundTag nbt) {
        }

        @Override
        public void decode(CompoundTag nbt) {
        }
    };

    void encode(CompoundTag nbt);

    void decode(CompoundTag nbt);

    default CompoundTag encode() {
        CompoundTag compound = new CompoundTag();
        this.encode(compound);
        return compound;
    }
}
