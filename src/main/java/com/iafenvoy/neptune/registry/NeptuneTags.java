package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class NeptuneTags {
    public static final TagKey<Item> RENDER_BIG_WEAPON = create("render/big_weapon");
    public static final TagKey<Item> RENDER_REVERSE_WEAPON = create("render/reverse_weapon");

    private static TagKey<Item> create(String id) {
        return TagKey.create(Registries.ITEM, Neptune.id(id));
    }
}
