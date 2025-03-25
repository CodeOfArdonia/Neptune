package com.iafenvoy.neptune.ability.command;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.ability.type.AbstractAbility;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.Optional;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class AbilityCommand {
    public static final SimpleCommandExceptionType UNKNOWN_CATEGORY = new SimpleCommandExceptionType(Text.translatable("argument.neptune.ability.unknown_category"));
    public static final SimpleCommandExceptionType UNKNOWN_ABILITY = new SimpleCommandExceptionType(Text.translatable("argument.neptune.ability.unknown_ability"));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("ability")
                .then(argument("targets", EntityArgumentType.players())
                        .then(literal("enable")
                                .then(argument("category", IdentifierArgumentType.identifier())
                                        .suggests(AbilitySuggestions.ABILITY_CATEGORY)
                                        .executes(ctx -> modifyCategory(ctx, true))
                                ))
                        .then(literal("disable")
                                .then(argument("category", IdentifierArgumentType.identifier())
                                        .suggests(AbilitySuggestions.ABILITY_CATEGORY)
                                        .executes(ctx -> modifyCategory(ctx, false))
                                ))
                        .then(literal("grant")
                                .then(argument("category", IdentifierArgumentType.identifier())
                                        .suggests(AbilitySuggestions.ABILITY_CATEGORY)
                                        .then(argument("ability", IdentifierArgumentType.identifier())
                                                .suggests(AbilitySuggestions.ABILITY_TYPE)
                                                .executes(ctx -> grantAbility(ctx, true))
                                        )))
                        .then(literal("remove")
                                .then(argument("category", IdentifierArgumentType.identifier())
                                        .suggests(AbilitySuggestions.ABILITY_TYPE)
                                        .executes(ctx -> grantAbility(ctx, false))
                                ))
                )
        );
    }

    private static int modifyCategory(CommandContext<ServerCommandSource> context, boolean enable) throws CommandSyntaxException {
        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, "targets");
        Optional<AbilityCategory> optional = AbilityCategory.byId(IdentifierArgumentType.getIdentifier(context, "category"));
        if (optional.isEmpty()) throw UNKNOWN_CATEGORY.create();
        for (ServerPlayerEntity player : players) AbilityData.byPlayer(player).setEnabled(enable, optional.get());
        return players.size();
    }

    private static int grantAbility(CommandContext<ServerCommandSource> context, boolean grant) throws CommandSyntaxException {
        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, "targets");
        Optional<AbilityCategory> optional = AbilityCategory.byId(IdentifierArgumentType.getIdentifier(context, "category"));
        if (optional.isEmpty()) throw UNKNOWN_CATEGORY.create();
        AbilityCategory category = optional.get();
        AbstractAbility<?> ability = category.getAbilityById(IdentifierArgumentType.getIdentifier(context, "ability"));
        if (ability == DummyAbility.EMPTY) throw UNKNOWN_ABILITY.create();
        for (ServerPlayerEntity player : players)
            AbilityData.byPlayer(player).get(category).setActiveAbility(ability);
        return players.size();
    }

    private static int removeAbility(CommandContext<ServerCommandSource> context, boolean grant) throws CommandSyntaxException {
        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, "targets");
        Optional<AbilityCategory> optional = AbilityCategory.byId(IdentifierArgumentType.getIdentifier(context, "category"));
        if (optional.isEmpty()) throw UNKNOWN_CATEGORY.create();
        AbilityCategory category = optional.get();
        for (ServerPlayerEntity player : players)
            AbilityData.byPlayer(player).get(category).removeAbility();
        return players.size();
    }
}
