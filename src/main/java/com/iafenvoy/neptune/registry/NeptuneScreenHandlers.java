package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.screen.handler.WeaponDeskScreenHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NeptuneScreenHandlers {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, Neptune.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<WeaponDeskScreenHandler>> WEAPON_DESK = REGISTRY.register("weapon_desk", () -> new MenuType<>(WeaponDeskScreenHandler::new, FeatureFlagSet.of(FeatureFlags.VANILLA)));
}
