package com.iafenvoy.neptune.network.payload;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.trail.storage.TrailStorage;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public record TrailActionPayload(Map<Integer, List<ResourceLocation>> data,
                                 ActionType action) implements CustomPacketPayload {
    public static final Type<TrailActionPayload> TYPE = new Type<>(Neptune.id("add_trail"));
    public static final StreamCodec<RegistryFriendlyByteBuf, TrailActionPayload> STREAM_CODEC = StreamCodec.composite(
            TrailStorage.CODEC, TrailActionPayload::data,
            ActionType.STREAM_CODEC, TrailActionPayload::action,
            TrailActionPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public enum ActionType {
        ADD, REMOVE, SYNC;

        public static final StreamCodec<ByteBuf, ActionType> STREAM_CODEC = ByteBufCodecs.idMapper(x -> ActionType.values()[x], Enum::ordinal);
    }
}
