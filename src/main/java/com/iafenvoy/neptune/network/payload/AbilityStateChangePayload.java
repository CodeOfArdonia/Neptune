package com.iafenvoy.neptune.network.payload;

import com.iafenvoy.neptune.Neptune;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record AbilityStateChangePayload(UUID player, ResourceLocation ability,
                                        boolean enable) implements CustomPacketPayload {
    public static final Type<AbilityStateChangePayload> TYPE = new Type<>(Neptune.id("ability_state_change"));
    public static final StreamCodec<RegistryFriendlyByteBuf, AbilityStateChangePayload> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, AbilityStateChangePayload::player,
            ResourceLocation.STREAM_CODEC, AbilityStateChangePayload::ability,
            ByteBufCodecs.BOOL, AbilityStateChangePayload::enable,
            AbilityStateChangePayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
