package com.iafenvoy.neptune.compat;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.recipe.WeaponDeskRecipe;
import com.iafenvoy.neptune.registry.NeptuneBlocks;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@EmiEntrypoint
public class WeaponDeskRecipePlugin implements EmiPlugin {
    private static final EmiTexture TEXTURE = new EmiTexture(ResourceLocation.tryBuild(Neptune.MOD_ID, "textures/gui/gui_boss_spawn_recipe.png"), 0, 0, 140, 44);
    private static final EmiStack WORKSTATION = EmiStack.of(NeptuneBlocks.WEAPON_DESK.get());
    private static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(WeaponDeskRecipe.ID, WORKSTATION, TEXTURE);

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
        registry.addWorkstation(CATEGORY, WORKSTATION);
        for (RecipeHolder<WeaponDeskRecipe> recipe : registry.getRecipeManager().getAllRecipesFor(WeaponDeskRecipe.Type.INSTANCE))
            registry.addRecipe(new EmiWeaponDeskRecipe(recipe));
    }

    private record EmiWeaponDeskRecipe(RecipeHolder<WeaponDeskRecipe> holder) implements EmiRecipe {
        @Override
        public EmiRecipeCategory getCategory() {
            return CATEGORY;
        }

        @Override
        public @NotNull ResourceLocation getId() {
            return this.holder.id();
        }

        @Override
        public List<EmiIngredient> getInputs() {
            return List.of(EmiIngredient.of(this.holder.value().material().ingredient(), this.holder.value().material().count()), EmiIngredient.of(this.holder.value().stick().ingredient(), this.holder.value().stick().count()));
        }

        @Override
        public List<EmiStack> getOutputs() {
            return List.of(EmiStack.of(this.holder.value().result().copy()));
        }

        @Override
        public int getDisplayWidth() {
            return 96;
        }

        @Override
        public int getDisplayHeight() {
            return 18;
        }

        @Override
        public void addWidgets(WidgetHolder widgets) {
            List<EmiIngredient> ingredients = this.getInputs();
            widgets.addTexture(EmiTexture.EMPTY_ARROW, 46, 1);
            widgets.addSlot(ingredients.get(0), 0, 0);
            widgets.addSlot(ingredients.get(1), 20, 0);
            widgets.addSlot(this.getOutputs().getFirst(), 78, 0).recipeContext(this);
        }
    }
}
