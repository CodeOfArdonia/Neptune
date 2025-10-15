package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.ability.type.Ability;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.iafenvoy.neptune.registry.NeptuneRegistries;
import com.iafenvoy.neptune.util.Color4i;
import com.iafenvoy.neptune.util.RandomHelper;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

public class AbilityCategory {
    private final Color4i color;
    private final BooleanSupplier shouldDisplay;
    private final List<Ability<?>> abilities = new ArrayList<>();

    public AbilityCategory(Color4i color, BooleanSupplier shouldDisplay) {
        this.color = color;
        this.shouldDisplay = shouldDisplay;
    }

    public ResourceLocation getId() {
        return NeptuneRegistries.ABILITY_CATEGORY.getKey(this);
    }

    public Color4i getColor() {
        return this.color;
    }

    public MutableComponent appendColor(MutableComponent text) {
        return text.withStyle(Style.EMPTY.withColor(this.getColor().getIntValue()));
    }

    public void registerAbility(Ability<?> ability) {
        this.abilities.add(ability);
    }

    public Ability<?> getAbilityById(ResourceLocation id) {
        return this.abilities.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(DummyAbility.EMPTY);
    }

    public Ability<?> randomOne() {
        return RandomHelper.randomOne(this.abilities);
    }

    public List<Ability<?>> getAbilities() {
        return this.abilities;
    }

    public Stream<ResourceLocation> streamAbilityIds() {
        return this.abilities.stream().map(Ability::getId);
    }

    public static Optional<AbilityCategory> byId(ResourceLocation id) {
        return NeptuneRegistries.ABILITY_CATEGORY.getOptional(id);
    }

    public boolean shouldDisplay() {
        return this.shouldDisplay.getAsBoolean();
    }
}
