package com.iafenvoy.neptune;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public final class NeptuneConstants {
    @Nullable
    public static MinecraftServer server = null;
    public static final Identifier ABILITY_KEYBINDING_SYNC = Identifier.of(Neptune.MOD_ID, "ability_keybinding_sync");
    public static final Identifier ABILITY_STATE_CHANGE = Identifier.of(Neptune.MOD_ID, "ability_state_change");

    public static void sendToAll(Identifier id, Supplier<PacketByteBuf> supplier) {
        if (server == null) return;
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList())
            NetworkManager.sendToPlayer(player, id, supplier.get());
    }
}
