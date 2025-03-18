package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class ClientAbilityEvents {
    public static final Event<AbilityStateChange> ABILITY_ENABLE = EventFactory.of(callbacks -> (player, ability) -> callbacks.forEach(x -> x.onChange(player, ability)));
    public static final Event<AbilityStateChange> ABILITY_DISABLE = EventFactory.of(callbacks -> (player, ability) -> callbacks.forEach(x -> x.onChange(player, ability)));

    @FunctionalInterface
    public interface AbilityStateChange {
        void onChange(PlayerEntity player, AbstractAbility<?> ability);
    }
}
