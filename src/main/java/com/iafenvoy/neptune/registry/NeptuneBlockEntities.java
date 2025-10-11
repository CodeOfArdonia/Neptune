package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.item.block.entity.WeaponDeskBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NeptuneBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Neptune.MOD_ID);

    @SuppressWarnings("DataFlowIssue")
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WeaponDeskBlockEntity>> WEAPON_DESK = REGISTRY.register("weapon_desk", () -> BlockEntityType.Builder.of(WeaponDeskBlockEntity::new, NeptuneBlocks.WEAPON_DESK.get()).build(null));
}
