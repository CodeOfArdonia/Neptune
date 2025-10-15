package com.iafenvoy.neptune.ability.command;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.ability.type.Ability;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.Collection;
import java.util.Optional;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

@EventBusSubscriber
public final class AbilityCommand {
    public static final SimpleCommandExceptionType UNKNOWN_CATEGORY = new SimpleCommandExceptionType(Component.translatable("argument.neptune.ability.unknown_category"));
    public static final SimpleCommandExceptionType UNKNOWN_ABILITY = new SimpleCommandExceptionType(Component.translatable("argument.neptune.ability.unknown_ability"));

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(literal("ability")
                .then(argument("targets", EntityArgument.players())
                        .then(literal("enable")
                                .then(argument("category", ResourceLocationArgument.id())
                                        .suggests(AbilitySuggestions.ABILITY_CATEGORY)
                                        .executes(ctx -> modifyCategory(ctx, true))
                                ))
                        .then(literal("disable")
                                .then(argument("category", ResourceLocationArgument.id())
                                        .suggests(AbilitySuggestions.ABILITY_CATEGORY)
                                        .executes(ctx -> modifyCategory(ctx, false))
                                ))
                        .then(literal("grant")
                                .then(argument("category", ResourceLocationArgument.id())
                                        .suggests(AbilitySuggestions.ABILITY_CATEGORY)
                                        .then(argument("ability", ResourceLocationArgument.id())
                                                .suggests(AbilitySuggestions.ABILITY_TYPE)
                                                .executes(ctx -> grantAbility(ctx, true))
                                        )))
                        .then(literal("remove")
                                .then(argument("category", ResourceLocationArgument.id())
                                        .suggests(AbilitySuggestions.ABILITY_TYPE)
                                        .executes(ctx -> grantAbility(ctx, false))
                                ))
                )
        );
    }

    private static int modifyCategory(CommandContext<CommandSourceStack> context, boolean enable) throws CommandSyntaxException {
        Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "targets");
        Optional<AbilityCategory> optional = AbilityCategory.byId(ResourceLocationArgument.getId(context, "category"));
        if (optional.isEmpty()) throw UNKNOWN_CATEGORY.create();
        for (ServerPlayer player : players) AbilityData.get(player).setEnabled(player, enable, optional.get());
        return players.size();
    }

    private static int grantAbility(CommandContext<CommandSourceStack> context, boolean grant) throws CommandSyntaxException {
        Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "targets");
        Optional<AbilityCategory> optional = AbilityCategory.byId(ResourceLocationArgument.getId(context, "category"));
        if (optional.isEmpty()) throw UNKNOWN_CATEGORY.create();
        AbilityCategory category = optional.get();
        Ability<?> ability = category.getAbilityById(ResourceLocationArgument.getId(context, "ability"));
        if (ability == DummyAbility.EMPTY) throw UNKNOWN_ABILITY.create();
        for (ServerPlayer player : players)
            AbilityData.get(player).get(category).setActiveAbility(player, ability);
        return players.size();
    }

    private static int removeAbility(CommandContext<CommandSourceStack> context, boolean grant) throws CommandSyntaxException {
        Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "targets");
        Optional<AbilityCategory> optional = AbilityCategory.byId(ResourceLocationArgument.getId(context, "category"));
        if (optional.isEmpty()) throw UNKNOWN_CATEGORY.create();
        AbilityCategory category = optional.get();
        for (ServerPlayer player : players)
            AbilityData.get(player).get(category).removeAbility(player);
        return players.size();
    }
}
