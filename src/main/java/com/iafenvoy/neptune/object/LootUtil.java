package com.iafenvoy.neptune.object;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class LootUtil {
    public static void loot(Level world, double x, double y, double z, ResourceLocation lootTable) {
        loot(world, x, y, z, lootTable, LootContextParamSets.EMPTY);
    }

    public static void loot(Level world, double x, double y, double z, ResourceLocation lootTable, LootContextParamSet contextType) {
        loot(world, BlockPos.containing(x, y, z), lootTable, contextType);
    }

    public static void loot(Level world, BlockPos pos, ResourceLocation lootTable) {
        loot(world, pos, lootTable, LootContextParamSets.EMPTY);
    }

    public static void loot(Level world, BlockPos pos, ResourceLocation lootTable, LootContextParamSet contextType) {
        if (world instanceof ServerLevel serverWorld) {
            for (ItemStack itemstackiterator : world.registryAccess().registryOrThrow(Registries.LOOT_TABLE).get(lootTable).getRandomItems(new LootParams.Builder(serverWorld)
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                    .withParameter(LootContextParams.BLOCK_STATE, world.getBlockState(pos))
                    .withOptionalParameter(LootContextParams.BLOCK_ENTITY, world.getBlockEntity(pos))
                    .create(contextType)))
                EntityUtil.item(serverWorld, pos.getX(), pos.getY(), pos.getZ(), itemstackiterator, 0);
        }
    }
}
