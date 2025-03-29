package com.iafenvoy.neptune;

import com.iafenvoy.neptune.network.ClientNetworkHelper;
import com.iafenvoy.neptune.object.item.CanActiveSwordItem;
import com.iafenvoy.neptune.registry.NeptuneScreenHandlers;
import com.iafenvoy.neptune.trail.TrailManager;
import com.iafenvoy.neptune.trail.render.TrailEffect;
import com.iafenvoy.neptune.trail.storage.ClientTrailStorage;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.util.Identifier;

public final class NeptuneClient {
    public static void init() {
    }

    public static void process() {
        NeptuneScreenHandlers.initClient();
        ClientNetworkHelper.init();
        ItemPropertiesRegistry.registerGeneric(new Identifier(Neptune.MOD_ID, CanActiveSwordItem.ACTIVE_KEY), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().getBoolean(CanActiveSwordItem.ACTIVE_KEY) ? 1 : 0);

        ClientTickEvent.CLIENT_POST.register(TrailEffect::tickAll);
        TrailManager.PROXY = ClientTrailStorage.INSTANCE;
        ClientTrailStorage.registerNetworking();
    }
}
