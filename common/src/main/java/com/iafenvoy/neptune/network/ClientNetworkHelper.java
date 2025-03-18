package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.ability.ClientAbilityEvents;
import com.iafenvoy.neptune.ability.type.AbstractAbility;
import dev.architectury.networking.NetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class ClientNetworkHelper {
    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, NeptuneConstants.ABILITY_STATE_CHANGE, (buf, context) -> {
            World world = MinecraftClient.getInstance().world;
            assert world != null;
            PlayerEntity player = world.getPlayerByUuid(buf.readUuid());
            AbstractAbility<?> ability = AbstractAbility.byId(buf.readIdentifier());
            boolean enable = buf.readBoolean();
            if (!ability.isEmpty())
                (enable ? ClientAbilityEvents.ABILITY_ENABLE : ClientAbilityEvents.ABILITY_DISABLE).invoker().onChange(player, ability);
        });
    }
}
