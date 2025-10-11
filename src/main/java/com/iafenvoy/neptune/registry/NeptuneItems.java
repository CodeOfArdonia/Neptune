package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NeptuneItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(Neptune.MOD_ID);

    public static final DeferredItem<Item> WEAPON_DESK = REGISTRY.register("weapon_desk", () -> new BlockItem(NeptuneBlocks.WEAPON_DESK.get(), new Item.Properties()));
}
