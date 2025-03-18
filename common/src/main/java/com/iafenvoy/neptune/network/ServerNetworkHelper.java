package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

public class ServerNetworkHelper {
    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, NeptuneConstants.ABILITY_KEYBINDING_SYNC, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            Optional<AbilityCategory> type = AbilityCategory.byId(buf.readIdentifier());
            if (type.isEmpty()) return;
            AbilityData data = AbilityData.byPlayer(player);
            if (!player.isSpectator() && data.isEnabled())
                context.queue(() -> data.get(type.get()).keyPress());
        });
    }
}
