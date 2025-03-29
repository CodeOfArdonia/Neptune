package com.iafenvoy.neptune.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerEvents {
    public static final Event<Spawn> SPAWN = Event.of(callbacks -> player -> {
        for (Spawn e : callbacks)
            e.onSpawn(player);
    });
    public static final Event<PlayerLoggedInOrOut> LOGGED_OUT = Event.of(callbacks -> player -> {
        for (PlayerLoggedInOrOut e : callbacks)
            e.handleConnection(player);
    });

    @FunctionalInterface
    public interface Spawn {
        void onSpawn(ServerPlayerEntity player);
    }

    @FunctionalInterface
    public interface PlayerLoggedInOrOut {
        void handleConnection(PlayerEntity player);
    }
}
