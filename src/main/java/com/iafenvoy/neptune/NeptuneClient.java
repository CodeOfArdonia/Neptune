package com.iafenvoy.neptune;

import com.iafenvoy.neptune.registry.NeptuneItems;
import com.iafenvoy.neptune.registry.NeptuneScreenHandlers;
import com.iafenvoy.neptune.screen.AbilityHudRenderer;
import com.iafenvoy.neptune.screen.gui.WeaponDeskScreen;
import com.iafenvoy.neptune.trail.TrailManager;
import com.iafenvoy.neptune.trail.storage.ClientTrailStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(value = Neptune.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber
public final class NeptuneClient {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(NeptuneScreenHandlers.WEAPON_DESK.get(), WeaponDeskScreen::new);
    }

    @SubscribeEvent
    public static void process(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TrailManager.PROXY = ClientTrailStorage.INSTANCE;
        });
    }

    @SubscribeEvent
    public static void renderAbilityHud(RenderGuiEvent.Post event) {
        AbilityHudRenderer.render(Minecraft.getInstance(), event.getGuiGraphics());
    }

    @SubscribeEvent
    public static void registerCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
            event.accept(NeptuneItems.WEAPON_DESK.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
