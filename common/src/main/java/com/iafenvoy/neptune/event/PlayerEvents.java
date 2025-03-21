package com.iafenvoy.neptune.event;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerEvents {
    public static final Event<PlayerLoggedInOrOut> LOGGED_OUT = Event.of(callbacks -> player -> {
        for (PlayerLoggedInOrOut e : callbacks)
            e.handleConnection(player);
    });

    @FunctionalInterface
    public interface PlayerLoggedInOrOut {
        void handleConnection(PlayerEntity player);
    }
}
