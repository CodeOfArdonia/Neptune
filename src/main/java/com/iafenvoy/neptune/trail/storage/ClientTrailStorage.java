package com.iafenvoy.neptune.trail.storage;

import com.google.common.collect.ImmutableSet;
import com.iafenvoy.neptune.network.payload.TrailActionPayload;
import com.iafenvoy.neptune.trail.TrailRegistry;
import com.iafenvoy.neptune.trail.provider.TrailProvider;
import com.iafenvoy.neptune.trail.render.TrailEffect;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.*;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public enum ClientTrailStorage implements TrailStorage {
    INSTANCE;
    private static final Map<Entity, Map<ResourceLocation, List<TrailProvider>>> PROVIDERS = new LinkedHashMap<>();
    private static final Int2ObjectOpenHashMap<List<ResourceLocation>> UNAVAILABLE_CACHE = new Int2ObjectOpenHashMap<>();

    @Override
    public void addTrail(Entity entity, ResourceLocation id) {
        if (entity instanceof ServerPlayer) return;
        List<TrailProvider> providers = TrailRegistry.get(id, entity);
        if (!PROVIDERS.containsKey(entity)) PROVIDERS.put(entity, new HashMap<>());
        PROVIDERS.get(entity).put(id, providers);
        providers.forEach(p -> TrailEffect.create(entity, p, id));
    }

    @Override
    public void removeTrail(Entity entity, ResourceLocation id) {
        if (entity instanceof ServerPlayer) return;
        if (PROVIDERS.containsKey(entity)) PROVIDERS.get(entity).remove(id);
        TrailEffect.remove(entity, id);
    }

    @Override
    public int getEntityLight(Entity entity, float tickDelta) {
        return Minecraft.getInstance().getEntityRenderDispatcher().getPackedLightCoords(entity, tickDelta);
    }

    public static void onTrailAction(TrailActionPayload payload) {
        Map<Entity, Set<ResourceLocation>> data = decode(payload.data());
        switch (payload.action()) {
            case ADD -> {
                for (Map.Entry<Entity, Set<ResourceLocation>> entry : data.entrySet())
                    for (ResourceLocation id : entry.getValue())
                        INSTANCE.addTrail(entry.getKey(), id);
            }
            case REMOVE -> {
                for (Map.Entry<Entity, Set<ResourceLocation>> entry : data.entrySet())
                    for (ResourceLocation id : entry.getValue())
                        INSTANCE.removeTrail(entry.getKey(), id);
            }
            case SYNC -> {
                PROVIDERS.clear();
                UNAVAILABLE_CACHE.clear();
                for (Map.Entry<Entity, Set<ResourceLocation>> entry : data.entrySet())
                    for (ResourceLocation id : entry.getValue())
                        INSTANCE.addTrail(entry.getKey(), id);
            }
        }
    }

    public static Map<Entity, Set<ResourceLocation>> decode(Map<Integer, List<ResourceLocation>> data) {
        ClientLevel world = Minecraft.getInstance().level;
        if (world == null) return Map.of();
        Map<Entity, Set<ResourceLocation>> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<ResourceLocation>> entry : data.entrySet()) {
            Entity entity = world.getEntity(entry.getKey());
            if (entity != null)
                result.computeIfAbsent(entity, e -> ImmutableSet.<ResourceLocation>builder().addAll(entry.getValue()).build());
        }
        return result;
    }

    @SubscribeEvent
    public static void onNewEntityOnClient(PlayerEvent.StartTracking event) {
        Entity entity = event.getTarget();
        List<ResourceLocation> ids = UNAVAILABLE_CACHE.remove(entity.getId());
        if (ids != null) ids.forEach(id -> INSTANCE.addTrail(entity, id));
    }
}
