package com.iafenvoy.neptune.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class ServerNetworkHelper {
    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, NetworkConstants.BLOCK_ENTITY_DATA_SYNC, (buf, context) -> {
            BlockPos pos = buf.readBlockPos();
            PlayerEntity player = context.getPlayer();
            context.queue(() -> {
                if (player instanceof ServerPlayerEntity serverPlayer)
                    sendBlockEntityData(serverPlayer, pos, player.getWorld().getBlockEntity(pos));
            });
        });
    }

    public static void sendBlockEntityData(ServerPlayerEntity serverPlayer, BlockPos pos, BlockEntity entity) {
        NbtCompound compound = new NbtCompound();
        if (entity != null) compound = entity.createNbt();
        NetworkManager.sendToPlayer(serverPlayer, NetworkConstants.BLOCK_ENTITY_DATA_SYNC, PacketBufferUtils.create().writeBlockPos(pos).writeNbt(compound));
    }
}
