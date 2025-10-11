package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.item.block.WeaponDeskBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NeptuneBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(Neptune.MOD_ID);

    public static final DeferredBlock<Block> WEAPON_DESK = REGISTRY.register("weapon_desk", WeaponDeskBlock::new);
}
