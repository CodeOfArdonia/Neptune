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

import java.util.function.Function;

public class BannerUtil {
    @SafeVarargs
    public static Function<HolderGetter<BannerPattern>, ItemStack> lazy(String translateKey, Item baseItem, Pair<ResourceKey<BannerPattern>, DyeColor>... patterns) {
        return getter -> create(translateKey, baseItem, getter, patterns);
    }

    @SafeVarargs
    public static ItemStack create(String translateKey, Item baseItem, HolderGetter<BannerPattern> getter, Pair<ResourceKey<BannerPattern>, DyeColor>... patterns) {
        BannerPatternLayers.Builder builder = new BannerPatternLayers.Builder();
        for (Pair<ResourceKey<BannerPattern>, DyeColor> pattern : patterns)
            builder.add(getter.getOrThrow(pattern.getFirst()), pattern.getSecond());
        ItemStack stack = new ItemStack(baseItem);
        stack.set(DataComponents.CUSTOM_NAME, Component.translatable(translateKey));
        stack.set(DataComponents.BANNER_PATTERNS, builder.build());
        return stack;
    }
}
