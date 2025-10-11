package com.iafenvoy.neptune.object.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Supplier;

public class ToolMaterialUtil {
    @SafeVarargs
    public static Tier of(int uses, float speed, float attackDamageBonus, TagKey<Block> incorrectTag, int enchantmentLevel, Supplier<ItemLike>... repairIngredients) {
        return new Tier() {
            @Override
            public int getUses() {
                return uses;
            }

            @Override
            public float getSpeed() {
                return speed;
            }

            @Override
            public float getAttackDamageBonus() {
                return attackDamageBonus;
            }

            @Override
            public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
                return incorrectTag;
            }

            @Override
            public int getEnchantmentValue() {
                return enchantmentLevel;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(Arrays.stream(repairIngredients).map(Supplier::get).toArray(ItemLike[]::new));
            }
        };
    }
}
