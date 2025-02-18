package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.NeptuneConstants;
import com.iafenvoy.neptune.power.ClientPowerEvents;
import com.iafenvoy.neptune.power.type.AbstractPower;
import dev.architectury.networking.NetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class ClientNetworkHelper {
    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, NeptuneConstants.POWER_STATE_CHANGE, (buf, context) -> {
            World world = MinecraftClient.getInstance().world;
            assert world != null;
            PlayerEntity player = world.getPlayerByUuid(buf.readUuid());
            AbstractPower<?> power = AbstractPower.byId(buf.readIdentifier());
            boolean enable = buf.readBoolean();
            if (!power.isEmpty())
                (enable ? ClientPowerEvents.POWER_ENABLE : ClientPowerEvents.POWER_DISABLE).invoker().onChange(player, power);
        });
    }
}
