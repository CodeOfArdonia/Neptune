package com.iafenvoy.neptune.screen.handler;

import com.google.common.collect.Lists;
import com.iafenvoy.neptune.recipe.WeaponDeskRecipe;
import com.iafenvoy.neptune.registry.NeptuneBlocks;
import com.iafenvoy.neptune.registry.NeptuneScreenHandlers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

import java.util.List;

public class WeaponDeskScreenHandler extends AbstractContainerMenu {
    private final ContainerLevelAccess context;
    private final DataSlot selectedRecipe;
    private final Level world;
    private List<RecipeHolder<WeaponDeskRecipe>> availableRecipes;
    long lastTakeTime;
    private final Slot materialSlot, stickSlot, outputSlot;
    Runnable contentsChangedListener;
    private final Container input;
    private final ResultContainer output;

    public WeaponDeskScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public WeaponDeskScreenHandler(int syncId, Inventory playerInventory, final ContainerLevelAccess context) {
        super(NeptuneScreenHandlers.WEAPON_DESK.get(), syncId);
        this.selectedRecipe = DataSlot.standalone();
        this.availableRecipes = Lists.newArrayList();
        this.contentsChangedListener = () -> {
        };
        this.input = new SimpleContainer(2) {
            public void setChanged() {
                super.setChanged();
                WeaponDeskScreenHandler.this.slotsChanged(this);
                WeaponDeskScreenHandler.this.contentsChangedListener.run();
            }
        };
        this.output = new ResultContainer();
        this.context = context;
        this.world = playerInventory.player.level();
        this.materialSlot = this.addSlot(new Slot(this.input, 0, 12, 23));
        this.stickSlot = this.addSlot(new Slot(this.input, 1, 12, 43));
        this.outputSlot = this.addSlot(new Slot(this.output, 2, 143, 33) {
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            public void onTake(Player player, ItemStack stack) {
                stack.onCraftedBy(player.level(), player, stack.getCount());
                RecipeHolder<?> recipe = WeaponDeskScreenHandler.this.output.getRecipeUsed();
                if (recipe == null || !(recipe.value() instanceof WeaponDeskRecipe weaponDeskRecipe)) return;
                ItemStack itemStack = WeaponDeskScreenHandler.this.materialSlot.remove(weaponDeskRecipe.material().count());
                WeaponDeskScreenHandler.this.stickSlot.remove(weaponDeskRecipe.stick().count());
                if (!itemStack.isEmpty())
                    WeaponDeskScreenHandler.this.populateResult();
                context.execute((world, pos) -> {
                    long l = world.getGameTime();
                    if (WeaponDeskScreenHandler.this.lastTakeTime != l) {
                        world.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                        WeaponDeskScreenHandler.this.lastTakeTime = l;
                    }

                });
                super.onTake(player, stack);
            }
        });

        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addDataSlot(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public List<RecipeHolder<WeaponDeskRecipe>> getAvailableRecipes() {
        return this.availableRecipes;
    }

    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    public boolean canCraft() {
        return this.materialSlot.hasItem() && this.stickSlot.hasItem() && !this.availableRecipes.isEmpty();
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.context, player, NeptuneBlocks.WEAPON_DESK.get());
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.isInBounds(id)) {
            this.selectedRecipe.set(id);
            this.populateResult();
        }
        return true;
    }

    private boolean isInBounds(int id) {
        return id >= 0 && id < this.availableRecipes.size();
    }

    @Override
    public void slotsChanged(Container inventory) {
        this.updateInput(new RecipeWrapper(new InvWrapper(inventory)));
    }

    private void updateInput(RecipeInput input) {
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.set(ItemStack.EMPTY);
        if (this.materialSlot.hasItem() && this.stickSlot.hasItem())
            this.availableRecipes = this.world.getRecipeManager().getRecipesFor(WeaponDeskRecipe.Type.INSTANCE, input, this.world);
    }

    private void populateResult() {
        if (!this.availableRecipes.isEmpty() && this.isInBounds(this.selectedRecipe.get())) {
            RecipeHolder<WeaponDeskRecipe> stonecuttingRecipe = this.availableRecipes.get(this.selectedRecipe.get());
            ItemStack itemStack = stonecuttingRecipe.value().assemble(new RecipeWrapper(new InvWrapper(this.input)), this.world.registryAccess());
            if (itemStack.isItemEnabled(this.world.enabledFeatures())) {
                this.output.setRecipeUsed(stonecuttingRecipe);
                this.outputSlot.set(itemStack);
            } else {
                this.outputSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.outputSlot.set(ItemStack.EMPTY);
        }

        this.broadcastChanges();
    }

    @Override
    public MenuType<?> getType() {
        return NeptuneScreenHandlers.WEAPON_DESK.get();
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.output && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasItem()) {
            ItemStack itemStack2 = slot2.getItem();
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (slot == 1) {
                item.onCraftedBy(itemStack2, player.level(), player);
                if (!this.moveItemStackTo(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickCraft(itemStack2, itemStack);
            } else if (slot == 0) {
                if (!this.moveItemStackTo(itemStack2, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.world.getRecipeManager().getRecipeFor(RecipeType.STONECUTTING, new SingleRecipeInput(itemStack2), this.world).isPresent()) {
                if (!this.moveItemStackTo(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 2 && slot < 29) {
                if (!this.moveItemStackTo(itemStack2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 29 && slot < 38 && !this.moveItemStackTo(itemStack2, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setByPlayer(ItemStack.EMPTY);
            }

            slot2.setChanged();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTake(player, itemStack2);
            this.broadcastChanges();
        }

        return itemStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.output.removeItemNoUpdate(1);
        this.context.execute((world, pos) -> this.clearContainer(player, this.input));
    }
}