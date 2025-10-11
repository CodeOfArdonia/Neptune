package com.iafenvoy.neptune.render.armor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public interface IArmorTextureProvider {
    ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type);
}
