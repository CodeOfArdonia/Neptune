package com.iafenvoy.neptune.trail.storage;

import com.iafenvoy.neptune.network.payload.TrailActionPayload;
import com.iafenvoy.neptune.trail.TrailRegistry;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.*;
import java.util.stream.Collectors;

public enum ServerTrailStorage implements TrailStorage {
    INSTANCE;
    private static final Map<Entity, Set<ResourceLocation>> PROVIDERS = new LinkedHashMap<>();

    @Override
    public void addTrail(Entity entity, ResourceLocation id) {
        if (!PROVIDERS.containsKey(entity)) PROVIDERS.put(entity, new HashSet<>());
        PROVIDERS.get(entity).add(id);
        PacketDistributor.sendToAllPlayers(new TrailActionPayload(encode(Map.of(entity, Set.of(id))), TrailActionPayload.ActionType.ADD));
    }

    @Override
    public void removeTrail(Entity entity, ResourceLocation id) {
        if (!PROVIDERS.containsKey(entity)) PROVIDERS.put(entity, new HashSet<>());
        PROVIDERS.get(entity).remove(id);
        PacketDistributor.sendToAllPlayers(new TrailActionPayload(encode(Map.of(entity, Set.of(id))), TrailActionPayload.ActionType.REMOVE));
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer)
            PacketDistributor.sendToPlayer(serverPlayer, new TrailActionPayload(encode(PROVIDERS), TrailActionPayload.ActionType.SYNC));
    }

    @SubscribeEvent
    public static void tick(ServerTickEvent.Post event) {
        Map<Entity, Set<ResourceLocation>> removed = new LinkedHashMap<>();
        for (Map.Entry<Entity, Set<ResourceLocation>> entry : PROVIDERS.entrySet())
            if (entry.getKey().isRemoved()) removed.put(entry.getKey(), entry.getValue());
            else {
                Set<ResourceLocation> shouldRemove = entry.getValue().stream().filter(x -> TrailRegistry.shouldRemove(entry.getKey(), x)).collect(Collectors.toSet());
                if (!shouldRemove.isEmpty()) removed.put(entry.getKey(), shouldRemove);
            }
        if (!removed.isEmpty())
            PacketDistributor.sendToAllPlayers(new TrailActionPayload(encode(removed), TrailActionPayload.ActionType.REMOVE));
    }

    @Override
    public int getEntityLight(Entity entity, float tickDelta) {
        return LightTexture.FULL_BRIGHT;
    }

    private static Map<Integer, List<ResourceLocation>> encode(Map<Entity, Set<ResourceLocation>> data) {
        Map<Integer, List<ResourceLocation>> map = new LinkedHashMap<>();
        for (Map.Entry<Entity, Set<ResourceLocation>> entry : data.entrySet())
            map.put(entry.getKey().getId(), List.copyOf(entry.getValue()));
        return map;
    }
}
