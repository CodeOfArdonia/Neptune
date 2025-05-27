package com.iafenvoy.neptune.compat;

import net.minecraft.item.ItemStack;

public class ReforgeStoneApi {
    public static final String GLINT_KEY = "reforge_stone:glint";
    public static final String GLINT_ALWAYS_KEY = "reforge_stone:glint_always";

    public static ItemStack apply(ItemStack stack, String color, boolean always) {
        stack.getOrCreateNbt().putString(GLINT_KEY, color);
        stack.getOrCreateNbt().putBoolean(GLINT_ALWAYS_KEY, always);
        return stack;
    }
}
