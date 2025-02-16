package com.iafenvoy.neptune.util;

import net.minecraft.nbt.NbtCompound;

public interface Serializable {
    Serializable EMPTY = new Serializable() {
        @Override
        public void encode(NbtCompound nbt) {
        }

        @Override
        public void decode(NbtCompound nbt) {
        }
    };

    void encode(NbtCompound nbt);

    void decode(NbtCompound nbt);

    default NbtCompound encode() {
        NbtCompound compound = new NbtCompound();
        this.encode(compound);
        return compound;
    }
}
