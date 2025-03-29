package com.iafenvoy.neptune.trail.storage;

import com.iafenvoy.neptune.trail.TrailRegistry;
import com.iafenvoy.neptune.trail.provider.TrailProvider;
import com.iafenvoy.neptune.trail.render.TrailEffect;
import dev.architectury.networking.NetworkManager;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.*;

@Environment(EnvType.CLIENT)
public enum ClientTrailStorage implements TrailStorage {
    INSTANCE;
    private static final Map<Entity, Map<Identifier, List<TrailProvider>>> PROVIDERS = new LinkedHashMap<>();
    private static final Int2ObjectOpenHashMap<List<Identifier>> UNAVAILABLE_CACHE = new Int2ObjectOpenHashMap<>();

    @Override
    public void addTrail(Entity entity, Identifier id) {
        if (entity instanceof ServerPlayerEntity) return;
        List<TrailProvider> providers = TrailRegistry.get(id, entity);
        if (!PROVIDERS.containsKey(entity)) PROVIDERS.put(entity, new HashMap<>());
        PROVIDERS.get(entity).put(id, providers);
        providers.forEach(p -> TrailEffect.create(entity, p, id));
    }

    @Override
    public void removeTrail(Entity entity, Identifier id) {
        if (entity instanceof ServerPlayerEntity) return;
        if (PROVIDERS.containsKey(entity)) PROVIDERS.get(entity).remove(id);
        TrailEffect.remove(entity, id);
    }

    @Override
    public int getEntityLight(Entity entity, float tickDelta) {
        return MinecraftClient.getInstance().getEntityRenderDispatcher().getLight(entity, tickDelta);
    }

    public static void registerNetworking() {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SYNC_TRAIL, (buf, ctx) -> {
            PROVIDERS.clear();
            UNAVAILABLE_CACHE.clear();
            Map<Entity, Set<Identifier>> data = decodeBuf(buf);
            for (Map.Entry<Entity, Set<Identifier>> entry : data.entrySet())
                for (Identifier id : entry.getValue())
                    INSTANCE.addTrail(entry.getKey(), id);
        });
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, ADD_TRAIL, (buf, ctx) -> {
            Map<Entity, Set<Identifier>> data = decodeBuf(buf);
            for (Map.Entry<Entity, Set<Identifier>> entry : data.entrySet())
                for (Identifier id : entry.getValue())
                    INSTANCE.addTrail(entry.getKey(), id);
        });
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, REMOVE_TRAIL, (buf, ctx) -> {
            Map<Entity, Set<Identifier>> data = decodeBuf(buf);
            for (Map.Entry<Entity, Set<Identifier>> entry : data.entrySet())
                for (Identifier id : entry.getValue())
                    INSTANCE.removeTrail(entry.getKey(), id);
        });
    }

    private static Map<Entity, Set<Identifier>> decodeBuf(PacketByteBuf buf) {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return Map.of();
        Map<Entity, Set<Identifier>> result = new LinkedHashMap<>();
        int count = buf.readInt();
        for (int i = 0; i < count; i++) {
            int entityId = buf.readInt();
            Entity entity = world.getEntityById(entityId);
            int size = buf.readInt();
            Set<Identifier> set = new HashSet<>();
            for (int j = 0; j < size; j++)
                set.add(buf.readIdentifier());
            if (entity == null) {
                if (!UNAVAILABLE_CACHE.containsKey(entityId)) UNAVAILABLE_CACHE.put(entityId, new LinkedList<>());
                UNAVAILABLE_CACHE.get(entityId).addAll(set);
            } else result.put(entity, set);
        }
        return result;
    }

    public static void onNewEntityOnClient(int entityId) {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return;
        Entity entity = world.getEntityById(entityId);
        List<Identifier> ids = UNAVAILABLE_CACHE.remove(entityId);
        if (ids != null) ids.forEach(id -> INSTANCE.addTrail(entity, id));
    }
}
