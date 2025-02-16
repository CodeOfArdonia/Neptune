package com.iafenvoy.neptune;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public final class Constants {
    @Nullable
    public static MinecraftServer server = null;
    public static final Identifier POWER_KEYBINDING_SYNC = Identifier.of(Neptune.MOD_ID, "keybinding_sync");
    public static final Identifier POWER_STATE_CHANGE = Identifier.of(Neptune.MOD_ID, "power_state_change");
}
