package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import com.iafenvoy.neptune.event.AbilityStateChangeEvent;
import com.iafenvoy.neptune.network.payload.AbilityStateChangePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.handling.IPayloadContext;

@OnlyIn(Dist.CLIENT)
public class ClientNetworkHelper {
    public static void onAbilityStateChange(AbilityStateChangePayload payload, IPayloadContext ctx) {
        Level world = Minecraft.getInstance().level;
        assert world != null;
        Player player = world.getPlayerByUUID(payload.player());
        AbstractAbility<?> ability = AbstractAbility.byId(payload.ability());
        boolean enable = payload.enable();
        if (!ability.isEmpty()) NeoForge.EVENT_BUS.post(new AbilityStateChangeEvent(player, ability, enable));
    }
}
