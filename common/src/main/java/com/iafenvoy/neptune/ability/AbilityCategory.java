package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.iafenvoy.neptune.util.Color4i;
import com.iafenvoy.neptune.util.RandomHelper;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

public class AbilityCategory {
    private static final List<AbilityCategory> CATEGORIES = new LinkedList<>();
    private final Identifier id;
    private final Color4i color;
    private final BooleanSupplier shouldDisplay;
    private final List<AbstractAbility<?>> abilities = new ArrayList<>();
    private final Map<Identifier, AbstractAbility<?>> byId = new HashMap<>();

    public AbilityCategory(Identifier id, Color4i color, BooleanSupplier shouldDisplay) {
        this.id = id;
        this.color = color;
        this.shouldDisplay = shouldDisplay;
        CATEGORIES.add(this);
    }

    public Identifier getId() {
        return this.id;
    }

    public Color4i getColor() {
        return this.color;
    }

    public MutableText appendColor(MutableText text) {
        return text.fillStyle(Style.EMPTY.withColor(this.getColor().getIntValue()));
    }

    public void registerAbility(AbstractAbility<?> ability) {
        this.abilities.add(ability);
        AbstractAbility<?> p = this.byId.put(ability.getId(), ability);
        if (p != null)
            throw new IllegalArgumentException("Duplicated id " + p.getId() + " for ability type " + this.id + "!");
    }

    public AbstractAbility<?> getAbilityById(Identifier id) {
        return this.abilities.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(DummyAbility.EMPTY);
    }

    public AbstractAbility<?> randomOne() {
        return RandomHelper.randomOne(this.abilities);
    }

    public Stream<Identifier> streamAbilityIds() {
        return this.abilities.stream().map(AbstractAbility::getId);
    }

    public static Optional<AbilityCategory> byId(Identifier id) {
        return CATEGORIES.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public boolean shouldDisplay() {
        return this.shouldDisplay.getAsBoolean();
    }

    public static List<AbilityCategory> values() {
        return CATEGORIES;
    }

    public static Stream<Identifier> streamIds() {
        return CATEGORIES.stream().map(AbilityCategory::getId);
    }
}
