package com.iafenvoy.neptune.trail.storage;

import com.iafenvoy.neptune.Neptune;
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
    ResourceLocation SYNC_TRAIL = ResourceLocation.tryBuild(Neptune.MOD_ID, "sync_trail");
    ResourceLocation ADD_TRAIL = ResourceLocation.tryBuild(Neptune.MOD_ID, "add_trail");
    ResourceLocation REMOVE_TRAIL = ResourceLocation.tryBuild(Neptune.MOD_ID, "remove_trail");

    void addTrail(Entity entity, ResourceLocation id);

    void removeTrail(Entity entity, ResourceLocation id);

    int getEntityLight(Entity entity, float tickDelta);
}
