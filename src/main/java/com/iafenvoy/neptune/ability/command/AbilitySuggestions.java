package com.iafenvoy.neptune.ability.command;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.registry.NeptuneRegistries;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public final class AbilitySuggestions {
    public static final SuggestionProvider<CommandSourceStack> ABILITY_CATEGORY = SuggestionProviders.register(Neptune.id("ability_category"), (context, builder) -> SharedSuggestionProvider.suggestResource(NeptuneRegistries.ABILITY_CATEGORY.holders().filter(x -> x.value().shouldDisplay()).map(x -> x.key().location()), builder));
    public static final SuggestionProvider<CommandSourceStack> ABILITY_TYPE = SuggestionProviders.register(Neptune.id("ability_type"), (context, builder) -> {
        Optional<AbilityCategory> optional = AbilityCategory.byId(context.getArgument("category", ResourceLocation.class));
        if (optional.isEmpty()) throw AbilityCommand.UNKNOWN_CATEGORY.create();
        return SharedSuggestionProvider.suggestResource(optional.get().streamAbilityIds(), builder);
    });
}
