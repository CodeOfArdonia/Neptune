package com.iafenvoy.neptune.accessory.interfaces;

import com.iafenvoy.neptune.accessory.interfaces.internal.AbstractAccessory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public interface Accessory extends AbstractAccessory, ICurioItem {
    @Override
    default void curioTick(SlotContext slotContext, ItemStack stack) {
        AbstractAccessory.super.tick(stack, slotContext.entity());
    }

    @Override
    default void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        AbstractAccessory.super.onEquip(stack, slotContext.entity());
    }

    @Override
    default void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        AbstractAccessory.super.onUnequip(stack, slotContext.entity());
    }

    @Override
    default boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return AbstractAccessory.super.canEquip(stack, slotContext.entity());
    }

    @Override
    default boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return AbstractAccessory.super.canUnequip(stack, slotContext.entity());
    }

    @Override
    @NotNull
    default ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(AbstractAccessory.super.getEquipSound(stack), 1, 0);
    }

    @Override
    @NotNull
    default ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return switch (AbstractAccessory.super.getDropRule(stack, slotContext.entity())) {
            case DEFAULT -> ICurio.DropRule.DEFAULT;
            case ALWAYS_DROP -> ICurio.DropRule.ALWAYS_DROP;
            case ALWAYS_KEEP -> ICurio.DropRule.ALWAYS_KEEP;
            case DESTROY -> ICurio.DropRule.DESTROY;
        };
    }
}
