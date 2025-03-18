package com.iafenvoy.neptune.ability.command;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.AbilityCategory;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.suggestion.SuggestionProviders;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

import java.util.Optional;

public final class AbilitySuggestions {
    public static final SuggestionProvider<ServerCommandSource> ABILITY_CATEGORY = SuggestionProviders.register(Identifier.of(Neptune.MOD_ID, "ability_category"), (context, builder) -> CommandSource.suggestIdentifiers(AbilityCategory.streamIds(), builder));
    public static final SuggestionProvider<ServerCommandSource> ABILITY_TYPE = SuggestionProviders.register(Identifier.of(Neptune.MOD_ID, "ability_type"), (context, builder) -> {
        Optional<AbilityCategory> optional = AbilityCategory.byId(context.getArgument("category", Identifier.class));
        if (optional.isEmpty()) throw AbilityCommand.UNKNOWN_CATEGORY.create();
        return CommandSource.suggestIdentifiers(optional.get().streamAbilityIds(), builder);
    });
}
