package com.iafenvoy.neptune.object;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LootUtil {
    public static void loot(World world, double x, double y, double z, Identifier lootTable) {
        loot(world, x, y, z, lootTable, LootContextTypes.EMPTY);
    }

    public static void loot(World world, double x, double y, double z, Identifier lootTable, LootContextType contextType) {
        loot(world, BlockPos.ofFloored(x, y, z), lootTable, contextType);
    }

    public static void loot(World world, BlockPos pos, Identifier lootTable) {
        loot(world, pos, lootTable, LootContextTypes.EMPTY);
    }

    public static void loot(World world, BlockPos pos, Identifier lootTable, LootContextType contextType) {
        if (world instanceof ServerWorld serverWorld) {
            for (ItemStack itemstackiterator : world.getServer().getLootManager().getLootTable(lootTable).generateLoot(new LootContextParameterSet.Builder(serverWorld)
                    .add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                    .add(LootContextParameters.BLOCK_STATE, world.getBlockState(pos))
                    .addOptional(LootContextParameters.BLOCK_ENTITY, world.getBlockEntity(pos))
                    .build(contextType)))
                EntityUtil.item(serverWorld, pos.getX(), pos.getY(), pos.getZ(), itemstackiterator, 0);
        }
    }
}
