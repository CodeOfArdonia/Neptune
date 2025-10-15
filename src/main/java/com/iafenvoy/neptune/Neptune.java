package com.iafenvoy.neptune;

import com.iafenvoy.neptune.ability.AbilityRegistry;
import com.iafenvoy.neptune.ability.type.Ability;
import com.iafenvoy.neptune.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(Neptune.MOD_ID)
@EventBusSubscriber
public final class Neptune {
    public static final String MOD_ID = "neptune";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String path) {
        return ResourceLocation.tryBuild(MOD_ID, path);
    }

    public Neptune(ModContainer container, IEventBus bus) {
        NeptuneAttachments.REGISTRY.register(bus);
        NeptuneBlocks.REGISTRY.register(bus);
        NeptuneBlockEntities.REGISTRY.register(bus);
        NeptuneItems.REGISTRY.register(bus);
        NeptuneRecipes.TYPE_REGISTRY.register(bus);
        NeptuneRecipes.SERIALIZER_REGISTRY.register(bus);
        NeptuneScreenHandlers.REGISTRY.register(bus);
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> AbilityRegistry.REGISTRY.forEach(Ability::init));
    }
}
