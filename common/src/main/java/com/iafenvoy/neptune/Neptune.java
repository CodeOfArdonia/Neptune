package com.iafenvoy.neptune;

import com.iafenvoy.neptune.registry.*;
import com.mojang.logging.LogUtils;
import dev.architectury.platform.Platform;
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

        if (!Platform.isModLoaded("sponsor_core"))
            throw new RuntimeException("Cannot find Sponsor Core, please re-download " + MOD_ID + " or contact author.");
    }

    public static void process() {
        CreativeTabRegistry.appendBuiltin(Registries.ITEM_GROUP.get(ItemGroups.FUNCTIONAL), NeptuneBlocks.WEAPON_DESK.get());
    }
}
