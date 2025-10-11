package com.iafenvoy.neptune.recipe;

import com.iafenvoy.neptune.Neptune;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record WeaponDeskRecipe(IngredientGroup material, IngredientGroup stick,
                               ItemStack result) implements Recipe<RecipeInput> {
    public static final ResourceLocation ID = Neptune.id("weapon_desk");

    @Override
    public boolean matches(RecipeInput inventory, @NotNull Level world) {
        if (inventory.size() < 2) return false;
        ItemStack materialStack = inventory.getItem(0), stickStack = inventory.getItem(1);
        return this.material.ingredient.test(materialStack)
                && materialStack.getCount() >= this.material.count
                && this.stick.ingredient.test(stickStack)
                && stickStack.getCount() >= this.material.count;
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput inventory, HolderLookup.@NotNull Provider registryManager) {
        if (inventory.size() < 2) return ItemStack.EMPTY;
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registryManager) {
        return this.result.copy();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public enum Type implements RecipeType<WeaponDeskRecipe> {
        INSTANCE;

        @Override
        public String toString() {
            return ID.toString();
        }
    }

    public enum Serializer implements RecipeSerializer<WeaponDeskRecipe> {
        INSTANCE;

        public static final MapCodec<WeaponDeskRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
                IngredientGroup.CODEC.fieldOf("material").forGetter(WeaponDeskRecipe::material),
                IngredientGroup.CODEC.fieldOf("stick").forGetter(WeaponDeskRecipe::stick),
                ItemStack.CODEC.fieldOf("result").forGetter(WeaponDeskRecipe::result)
        ).apply(i, WeaponDeskRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, WeaponDeskRecipe> STREAM_CODEC = StreamCodec.composite(
                IngredientGroup.STREAM_CODEC, WeaponDeskRecipe::material,
                IngredientGroup.STREAM_CODEC, WeaponDeskRecipe::stick,
                ItemStack.STREAM_CODEC, WeaponDeskRecipe::result,
                WeaponDeskRecipe::new
        );

        @Override
        public @NotNull MapCodec<WeaponDeskRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, WeaponDeskRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

    public record IngredientGroup(Ingredient ingredient, int count) {
        public static final Codec<IngredientGroup> CODEC = RecordCodecBuilder.create(i -> i.group(
                Ingredient.MAP_CODEC_NONEMPTY.forGetter(IngredientGroup::ingredient),
                Codec.INT.fieldOf("count").forGetter(IngredientGroup::count)
        ).apply(i, IngredientGroup::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, IngredientGroup> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, IngredientGroup::ingredient,
                ByteBufCodecs.INT, IngredientGroup::count,
                IngredientGroup::new
        );
    }
}
