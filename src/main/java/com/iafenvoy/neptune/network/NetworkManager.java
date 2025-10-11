package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.network.payload.AbilityKeybindingSyncPayload;
import com.iafenvoy.neptune.network.payload.AbilityStateChangePayload;
import com.iafenvoy.neptune.network.payload.TrailActionPayload;
import com.iafenvoy.neptune.trail.storage.ClientTrailStorage;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;

@EventBusSubscriber
public class NetworkManager {
    public static FriendlyByteBuf create() {
        return new FriendlyByteBuf(Unpooled.buffer());
    }

    @SubscribeEvent
    public static void registerPayload(RegisterPayloadHandlersEvent event) {
        event.registrar("1")
                .playToServer(AbilityKeybindingSyncPayload.TYPE, AbilityKeybindingSyncPayload.STREAM_CODEC, new MainThreadPayloadHandler<>(ServerNetworkHelper::onAbilityKeybindingSync))
                .playToClient(AbilityStateChangePayload.TYPE, AbilityStateChangePayload.STREAM_CODEC, new MainThreadPayloadHandler<>(ClientNetworkHelper::onAbilityStateChange))
                .playToClient(TrailActionPayload.TYPE, TrailActionPayload.STREAM_CODEC, new MainThreadPayloadHandler<>(ClientTrailStorage::onTrailAction));
    }
}
