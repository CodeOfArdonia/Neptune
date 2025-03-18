package com.iafenvoy.neptune;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public final class NeptuneConstants {
    @Nullable
    public static MinecraftServer server = null;
    public static final Identifier ABILITY_KEYBINDING_SYNC = Identifier.of(Neptune.MOD_ID, "ability_keybinding_sync");
    public static final Identifier ABILITY_STATE_CHANGE = Identifier.of(Neptune.MOD_ID, "ability_state_change");
}
