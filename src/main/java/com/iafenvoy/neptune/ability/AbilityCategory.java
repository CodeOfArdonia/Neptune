package com.iafenvoy.neptune.ability;

import com.google.common.base.Suppliers;
import com.iafenvoy.neptune.ability.type.Ability;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.iafenvoy.neptune.registry.NeptuneRegistries;
import com.iafenvoy.neptune.util.Color4i;
import com.iafenvoy.neptune.util.RandomHelper;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

@EventBusSubscriber
public class AbilityCategory {
    public static final Supplier<AbilityCategory> EMPTY = Suppliers.memoize(() -> new AbilityCategory(new Color4i(0, 0, 0, 0), () -> false));
    private final Color4i color;
    private final BooleanSupplier shouldDisplay;
    private final Supplier<List<Ability<?>>> abilities = Suppliers.memoize(() -> NeptuneRegistries.ABILITY.stream().filter(x -> x.getCategory() == this).toList());

    @SubscribeEvent
    public static void registerDefaulted(RegisterEvent event) {
        if (event.getRegistryKey() == NeptuneRegistries.ABILITY_CATEGORY_KEY)
            event.register(NeptuneRegistries.ABILITY_CATEGORY_KEY, ResourceLocation.withDefaultNamespace("empty"), EMPTY);
    }

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

    public Ability<?> getAbilityById(ResourceLocation id) {
        return this.abilities.get().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(DummyAbility.EMPTY);
    }

    public Ability<?> randomOne() {
        return RandomHelper.randomOne(this.abilities.get());
    }

    public List<Ability<?>> getAbilities() {
        return this.abilities.get();
    }

    public Stream<ResourceLocation> streamAbilityIds() {
        return this.abilities.get().stream().map(Ability::getId);
    }

    public static Optional<AbilityCategory> byId(ResourceLocation id) {
        return NeptuneRegistries.ABILITY_CATEGORY.getOptional(id);
    }

    public boolean shouldDisplay() {
        return this.shouldDisplay.getAsBoolean();
    }
}
