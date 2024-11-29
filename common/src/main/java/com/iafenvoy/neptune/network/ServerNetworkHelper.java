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
                BlockEntity entity = player.getWorld().getBlockEntity(pos);
                NbtCompound compound = new NbtCompound();
                if (entity != null) compound = entity.createNbt();
                if (player instanceof ServerPlayerEntity serverPlayer)
                    NetworkManager.sendToPlayer(serverPlayer, NetworkConstants.BLOCK_ENTITY_DATA_SYNC, PacketBufferUtils.create().writeBlockPos(pos).writeNbt(compound));
            });
        });
    }
}
