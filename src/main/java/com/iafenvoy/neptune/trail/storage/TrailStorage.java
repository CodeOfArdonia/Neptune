package com.iafenvoy.neptune.trail.storage;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Map;

@ApiStatus.Internal
public interface TrailStorage {
    StreamCodec<RegistryFriendlyByteBuf, Map<Integer, List<ResourceLocation>>> CODEC = ByteBufCodecs.fromCodecWithRegistries(Codec.unboundedMap(Codec.INT, ResourceLocation.CODEC.listOf()));

    void addTrail(Entity entity, ResourceLocation id);

    void removeTrail(Entity entity, ResourceLocation id);

    int getEntityLight(Entity entity, float tickDelta);
}
