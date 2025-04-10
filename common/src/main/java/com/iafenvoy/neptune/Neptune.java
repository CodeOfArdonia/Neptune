package com.iafenvoy.neptune;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import com.iafenvoy.neptune.event.PlayerEvents;
import com.iafenvoy.neptune.network.ServerNetworkHelper;
import com.iafenvoy.neptune.registry.*;
import com.iafenvoy.neptune.trail.storage.ServerTrailStorage;
import com.mojang.logging.LogUtils;
import dev.architectury.event.events.common.TickEvent;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import org.slf4j.Logger;

public final class Neptune {
    public static final String MOD_ID = "neptune";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        NeptuneBlocks.REGISTRY.register();
        NeptuneItems.REGISTRY.register();
        NeptuneBlockEntities.REGISTRY.register();
        NeptuneScreenHandlers.REGISTRY.register();
        NeptuneRecipes.TYPE_REGISTRY.register();
        NeptuneRecipes.SERIALIZER_REGISTRY.register();
    }

    public static void process() {
        ServerNetworkHelper.init();
        CreativeTabRegistry.appendBuiltin(Registries.ITEM_GROUP.get(ItemGroups.FUNCTIONAL), NeptuneBlocks.WEAPON_DESK.get());
        AbstractAbility.initAll();

        TickEvent.SERVER_POST.register(x -> ServerTrailStorage.tick());
        PlayerEvents.SPAWN.register(ServerTrailStorage::onPlayerJoin);
    }
}
