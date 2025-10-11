package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.network.payload.AbilityKeybindingSyncPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Optional;

public class ServerNetworkHelper {
    public static void onAbilityKeybindingSync(AbilityKeybindingSyncPayload payload, IPayloadContext ctx) {
        Player player = ctx.player();
        Optional<AbilityCategory> type = AbilityCategory.byId(payload.category());
        if (type.isEmpty()) return;
        AbilityData data = AbilityData.get(player);
        if (!player.isSpectator() && data.isEnabled()) data.get(type.get()).onKeyPressed(player);
    }
}
