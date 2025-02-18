package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerData;
import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

public class ServerNetworkHelper {
    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, NeptuneConstants.POWER_KEYBINDING_SYNC, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            Optional<PowerCategory> type = PowerCategory.byId(buf.readIdentifier());
            if (type.isEmpty()) return;
            PowerData data = PowerData.byPlayer(player);
            if (!player.isSpectator() && data.isEnabled())
                context.queue(() -> data.get(type.get()).keyPress());
        });
    }
}
