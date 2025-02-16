package com.iafenvoy.neptune.power;

import com.iafenvoy.neptune.power.type.AbstractPower;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class ClientPowerEvents {
    public static final Event<PowerStateChange> POWER_ENABLE = EventFactory.of(callbacks -> (player, power) -> callbacks.forEach(x -> x.onChange(player, power)));
    public static final Event<PowerStateChange> POWER_DISABLE = EventFactory.of(callbacks -> (player, power) -> callbacks.forEach(x -> x.onChange(player, power)));

    @FunctionalInterface
    public interface PowerStateChange {
        void onChange(PlayerEntity player, AbstractPower<?> power);
    }
}
