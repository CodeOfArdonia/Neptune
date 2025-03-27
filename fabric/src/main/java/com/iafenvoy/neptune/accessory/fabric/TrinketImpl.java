package com.iafenvoy.neptune.accessory.fabric;

import com.iafenvoy.neptune.accessory.Accessory;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class TrinketImpl implements Trinket {
    private final Accessory accessory;

    public TrinketImpl(Accessory accessory) {
        this.accessory = accessory;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        this.accessory.tick(stack, entity);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        this.accessory.onEquip(stack, entity);
        entity.playSound(this.accessory.getEquipSound(stack), 1, 0);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        this.accessory.onUnequip(stack, entity);
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return this.accessory.canEquip(stack, entity);
    }

    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return this.accessory.canUnequip(stack, entity);
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return switch (this.accessory.getDropRule(stack, entity)) {
            case DEFAULT -> TrinketEnums.DropRule.DEFAULT;
            case ALWAYS_DROP -> TrinketEnums.DropRule.DROP;
            case ALWAYS_KEEP -> TrinketEnums.DropRule.KEEP;
            case DESTROY -> TrinketEnums.DropRule.DESTROY;
        };
    }
}
