package com.iafenvoy.neptune.network.payload;

import com.iafenvoy.neptune.Neptune;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record AbilityKeybindingSyncPayload(ResourceLocation category) implements CustomPacketPayload {
    public static final Type<AbilityKeybindingSyncPayload> TYPE = new Type<>(Neptune.id("ability_keybinding_sync"));
    public static final StreamCodec<RegistryFriendlyByteBuf, AbilityKeybindingSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, AbilityKeybindingSyncPayload::category,
            AbilityKeybindingSyncPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
