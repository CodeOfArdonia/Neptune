package com.iafenvoy.neptune.ability.command;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.AbilityCategory;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.Optional;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.resources.ResourceLocation;

public final class AbilitySuggestions {
    public static final SuggestionProvider<CommandSourceStack> ABILITY_CATEGORY = SuggestionProviders.register(ResourceLocation.tryBuild(Neptune.MOD_ID, "ability_category"), (context, builder) -> SharedSuggestionProvider.suggestResource(AbilityCategory.streamIds(), builder));
    public static final SuggestionProvider<CommandSourceStack> ABILITY_TYPE = SuggestionProviders.register(ResourceLocation.tryBuild(Neptune.MOD_ID, "ability_type"), (context, builder) -> {
        Optional<AbilityCategory> optional = AbilityCategory.byId(context.getArgument("category", ResourceLocation.class));
        if (optional.isEmpty()) throw AbilityCommand.UNKNOWN_CATEGORY.create();
        return SharedSuggestionProvider.suggestResource(optional.get().streamAbilityIds(), builder);
    });
}
