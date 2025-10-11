package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.iafenvoy.neptune.util.Color4i;
import com.iafenvoy.neptune.util.RandomHelper;
import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;

public class AbilityCategory {
    private static final List<AbilityCategory> CATEGORIES = new LinkedList<>();
    private final ResourceLocation id;
    private final Color4i color;
    private final BooleanSupplier shouldDisplay;
    private final List<AbstractAbility<?>> abilities = new ArrayList<>();
    private final Map<ResourceLocation, AbstractAbility<?>> byId = new HashMap<>();

    public AbilityCategory(ResourceLocation id, Color4i color, BooleanSupplier shouldDisplay) {
        this.id = id;
        this.color = color;
        this.shouldDisplay = shouldDisplay;
        CATEGORIES.add(this);
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public Color4i getColor() {
        return this.color;
    }

    public MutableComponent appendColor(MutableComponent text) {
        return text.withStyle(Style.EMPTY.withColor(this.getColor().getIntValue()));
    }

    public void registerAbility(AbstractAbility<?> ability) {
        this.abilities.add(ability);
        AbstractAbility<?> p = this.byId.put(ability.getId(), ability);
        if (p != null)
            throw new IllegalArgumentException("Duplicated id " + p.getId() + " for ability type " + this.id + "!");
    }

    public AbstractAbility<?> getAbilityById(ResourceLocation id) {
        return this.abilities.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(DummyAbility.EMPTY);
    }

    public AbstractAbility<?> randomOne() {
        return RandomHelper.randomOne(this.abilities);
    }

    public List<AbstractAbility<?>> getAbilities() {
        return this.abilities;
    }

    public Stream<ResourceLocation> streamAbilityIds() {
        return this.abilities.stream().map(AbstractAbility::getId);
    }

    public static Optional<AbilityCategory> byId(ResourceLocation id) {
        return CATEGORIES.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public boolean shouldDisplay() {
        return this.shouldDisplay.getAsBoolean();
    }

    public static List<AbilityCategory> values() {
        return CATEGORIES;
    }

    public static Stream<ResourceLocation> streamIds() {
        return CATEGORIES.stream().map(AbilityCategory::getId);
    }
}
