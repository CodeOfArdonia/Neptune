package com.iafenvoy.neptune.accessory.interfaces;

import com.iafenvoy.neptune.accessory.interfaces.internal.AbstractAccessory;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface Accessory extends AbstractAccessory, Trinket {
    @Override
    default void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        AbstractAccessory.super.tick(stack, entity);
    }

    @Override
    default void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        AbstractAccessory.super.onEquip(stack, entity);
        entity.playSound(this.getEquipSound(stack), 1, 0);
    }

    @Override
    default void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        AbstractAccessory.super.onUnequip(stack, entity);
    }

    @Override
    default boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return AbstractAccessory.super.canEquip(stack, entity);
    }

    @Override
    default boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return AbstractAccessory.super.canUnequip(stack, entity);
    }

    @Override
    default TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return switch (AbstractAccessory.super.getDropRule(stack, entity)) {
            case DEFAULT -> TrinketEnums.DropRule.DEFAULT;
            case ALWAYS_DROP -> TrinketEnums.DropRule.DROP;
            case ALWAYS_KEEP -> TrinketEnums.DropRule.KEEP;
            case DESTROY -> TrinketEnums.DropRule.DESTROY;
        };
    }
}
