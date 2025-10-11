package com.iafenvoy.neptune.object.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RestrictedItem extends Item {
    private final RestrictPredicate predicate;

    public RestrictedItem(Properties settings, RestrictPredicate predicate) {
        super(settings);
        this.predicate = predicate;
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!stack.is(this)) return;
        if (this.predicate.canExist(stack, world, entity, slot, selected)) return;
        if (this.isDamageable(stack)) stack.setDamageValue(this.getMaxDamage(stack) + 1);
        stack.setCount(0);
    }

    @FunctionalInterface
    public interface RestrictPredicate {
        boolean canExist(ItemStack stack, Level world, Entity entity, int slot, boolean selected);
    }
}
