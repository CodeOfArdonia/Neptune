package com.iafenvoy.neptune.accessory.interfaces.internal;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface AbstractAccessory {
    default void tick(ItemStack stack, LivingEntity entity) {
    }

    default void onEquip(ItemStack stack, LivingEntity entity) {
    }

    default void onUnequip(ItemStack stack, LivingEntity entity) {
    }

    default SoundEvent getEquipSound(ItemStack stack) {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    default boolean canEquip(ItemStack stack, LivingEntity entity) {
        return true;
    }

    default boolean canUnequip(ItemStack stack, LivingEntity entity) {
        return !EnchantmentHelper.hasBindingCurse(stack);
    }

    default DropRule getDropRule(ItemStack stack, LivingEntity entity) {
        return DropRule.DEFAULT;
    }

    enum DropRule {
        DEFAULT, ALWAYS_DROP, ALWAYS_KEEP, DESTROY
    }
}
