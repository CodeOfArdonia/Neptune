package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.recipe.WeaponDeskRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NeptuneRecipes {
    public static final DeferredRegister<RecipeType<?>> TYPE_REGISTRY = DeferredRegister.create(Registries.RECIPE_TYPE, Neptune.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTRY = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Neptune.MOD_ID);

    static {
        TYPE_REGISTRY.register("weapon_desk", () -> WeaponDeskRecipe.Type.INSTANCE);
        SERIALIZER_REGISTRY.register("weapon_desk", () -> WeaponDeskRecipe.Serializer.INSTANCE);
    }
}
