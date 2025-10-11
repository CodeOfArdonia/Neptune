package com.iafenvoy.neptune.network;

import com.iafenvoy.neptune.ability.AbilityKeybindings;
import com.iafenvoy.neptune.network.payload.AbilityStateChangePayload;
import com.iafenvoy.neptune.network.payload.TrailActionPayload;
import com.iafenvoy.neptune.trail.storage.ClientTrailStorage;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientNetworkHelper {
    public static void onAbilityStateChange(AbilityStateChangePayload payload, IPayloadContext ctx) {
        AbilityKeybindings.onAbilityStateChange(payload);
    }

    public static void onTrailAction(TrailActionPayload payload, IPayloadContext ctx) {
        ClientTrailStorage.onTrailAction(payload);
    }
}
