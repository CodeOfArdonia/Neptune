package com.iafenvoy.neptune.event;

import com.iafenvoy.neptune.util.function.consumer.Consumer3;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class OriginsEvents {
    public static final Event<Consumer3<PlayerEntity, Identifier, Identifier>> ON_CONFIRM = Event.of(consumer3s -> (player, layer, origin) -> {
        for (Consumer3<PlayerEntity, Identifier, Identifier> consumer3 : consumer3s)
            consumer3.accept(player, layer, origin);
    });
}
