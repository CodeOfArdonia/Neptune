package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.Neptune;
import dev.architectury.event.events.client.ClientPlayerEvent;
import dev.architectury.networking.NetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ClientNetworkHelper {
    private static final List<BlockPos> REQUESTING = new LinkedList<>();

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, NetworkConstants.BLOCK_ENTITY_DATA_SYNC, (buf, context) -> {
            BlockPos pos = buf.readBlockPos();
            NbtCompound compound = buf.readNbt();
            context.queue(() -> {
                World world = MinecraftClient.getInstance().world;
                if (world != null) {
                    BlockEntity entity = world.getBlockEntity(pos);
                    if (entity != null) entity.readNbt(compound);
                }
                REQUESTING.remove(pos);
            });
        });
        ClientPlayerEvent.CLIENT_PLAYER_QUIT.register(player -> {
            if (MinecraftClient.getInstance().player != player) return;
            Neptune.LOGGER.info("[Neptune] Disconnected from server, block entity data queue cleared.");
            REQUESTING.clear();
        });
    }

    public static void requestBlockEntityData(BlockPos pos) {
        if (REQUESTING.contains(pos)) return;
        REQUESTING.add(pos);
        NetworkManager.sendToServer(NetworkConstants.BLOCK_ENTITY_DATA_SYNC, PacketBufferUtils.create().writeBlockPos(pos));
    }
}
