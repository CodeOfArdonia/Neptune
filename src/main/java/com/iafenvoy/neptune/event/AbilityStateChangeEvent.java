package com.iafenvoy.neptune.event;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;

//Client only event
public class AbilityStateChangeEvent extends Event {
    private final Player player;
    private final AbstractAbility<?> ability;
    private final boolean enabled;

    public AbilityStateChangeEvent(Player player, AbstractAbility<?> ability, boolean enabled) {
        this.player = player;
        this.ability = ability;
        this.enabled = enabled;
    }

    public Player getPlayer() {
        return this.player;
    }

    public AbstractAbility<?> getAbility() {
        return this.ability;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
