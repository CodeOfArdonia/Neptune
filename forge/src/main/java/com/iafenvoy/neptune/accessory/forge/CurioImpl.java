package com.iafenvoy.neptune.accessory.forge;

import com.iafenvoy.neptune.accessory.Accessory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class CurioImpl implements ICurioItem {
    private final Accessory accessory;

    public CurioImpl(Accessory accessory) {
        this.accessory = accessory;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        this.accessory.tick(stack, slotContext.entity());
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        this.accessory.onEquip(stack, slotContext.entity());
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        this.accessory.onUnequip(stack, slotContext.entity());
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return this.accessory.canEquip(stack, slotContext.entity());
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return this.accessory.canUnequip(stack, slotContext.entity());
    }

    @Override
    @NotNull
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(this.accessory.getEquipSound(stack), 1, 0);
    }

    @Override
    @NotNull
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return switch (this.accessory.getDropRule(stack, slotContext.entity())) {
            case DEFAULT -> ICurio.DropRule.DEFAULT;
            case ALWAYS_DROP -> ICurio.DropRule.ALWAYS_DROP;
            case ALWAYS_KEEP -> ICurio.DropRule.ALWAYS_KEEP;
            case DESTROY -> ICurio.DropRule.DESTROY;
        };
    }
}
