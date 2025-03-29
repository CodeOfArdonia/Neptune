package com.iafenvoy.neptune.trail.storage;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.network.PacketBufferUtils;
import com.iafenvoy.neptune.trail.TrailRegistry;
import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum ServerTrailStorage implements TrailStorage {
    INSTANCE;
    private static final Map<Entity, Set<Identifier>> PROVIDERS = new LinkedHashMap<>();

    @Override
    public void addTrail(Entity entity, Identifier id) {
        if (!PROVIDERS.containsKey(entity)) PROVIDERS.put(entity, new HashSet<>());
        PROVIDERS.get(entity).add(id);
        NeptuneConstants.sendToAll(ADD_TRAIL, () -> encodeBuf(Map.of(entity, Set.of(id)), PacketBufferUtils.create()));
    }

    @Override
    public void removeTrail(Entity entity, Identifier id) {
        if (!PROVIDERS.containsKey(entity)) PROVIDERS.put(entity, new HashSet<>());
        PROVIDERS.get(entity).remove(id);
        NeptuneConstants.sendToAll(REMOVE_TRAIL, () -> encodeBuf(Map.of(entity, Set.of(id)), PacketBufferUtils.create()));
    }

    public static void onPlayerJoin(ServerPlayerEntity player) {
        NetworkManager.sendToPlayer(player, SYNC_TRAIL, encodeBuf(PROVIDERS, PacketBufferUtils.create()));
    }

    public static void tick() {
        Map<Entity, Set<Identifier>> removed = new LinkedHashMap<>();
        for (Map.Entry<Entity, Set<Identifier>> entry : PROVIDERS.entrySet())
            if (entry.getKey().isRemoved()) removed.put(entry.getKey(), entry.getValue());
            else {
                Set<Identifier> shouldRemove = entry.getValue().stream().filter(x -> TrailRegistry.shouldRemove(entry.getKey(), x)).collect(Collectors.toSet());
                if (!shouldRemove.isEmpty()) removed.put(entry.getKey(), shouldRemove);
            }
        NeptuneConstants.sendToAll(REMOVE_TRAIL, () -> encodeBuf(removed, PacketBufferUtils.create()));
    }

    @Override
    public int getEntityLight(Entity entity, float tickDelta) {
        return MAX_LIGHT;
    }

    private static PacketByteBuf encodeBuf(Map<Entity, Set<Identifier>> data, PacketByteBuf buf) {
        buf.writeInt(data.size());
        for (Map.Entry<Entity, Set<Identifier>> entry : data.entrySet()) {
            buf.writeInt(entry.getKey().getId());
            Set<Identifier> set = entry.getValue();
            buf.writeInt(set.size());
            for (Identifier id : set) buf.writeIdentifier(id);
        }
        return buf;
    }
}
