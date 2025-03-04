package com.iafenvoy.neptune.object.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RestrictedItem extends Item {
    private final RestrictPredicate predicate;

    public RestrictedItem(Settings settings, RestrictPredicate predicate) {
        super(settings);
        this.predicate = predicate;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!stack.isOf(this)) return;
        if (this.predicate.canExist(stack, world, entity, slot, selected)) return;
        if (this.isDamageable()) stack.setDamage(this.getMaxDamage() + 1);
        stack.setCount(0);
    }

    @FunctionalInterface
    public interface RestrictPredicate {
        boolean canExist(ItemStack stack, World world, Entity entity, int slot, boolean selected);
    }
}
