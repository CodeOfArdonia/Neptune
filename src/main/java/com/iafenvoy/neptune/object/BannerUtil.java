package com.iafenvoy.neptune.object;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public class BannerUtil {
    @Deprecated
    @SafeVarargs
    public static ItemStack create(String translateKey, Item baseItem, HolderGetter<BannerPattern> getter, Pair<ResourceKey<BannerPattern>, DyeColor>... patterns) {
        BannerPatternLayers.Builder builder = new BannerPatternLayers.Builder();
        for (Pair<ResourceKey<BannerPattern>, DyeColor> pattern : patterns)
            builder.addIfRegistered(getter, pattern.getFirst(), pattern.getSecond());
        ItemStack stack = new ItemStack(baseItem);
        stack.set(DataComponents.CUSTOM_NAME, Component.translatable(translateKey));
        stack.set(DataComponents.BANNER_PATTERNS, builder.build());
        return stack;
    }
}
