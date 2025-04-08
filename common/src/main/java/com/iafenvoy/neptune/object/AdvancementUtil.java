package com.iafenvoy.neptune.object;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class AdvancementUtil {
    public static void award(ServerPlayerEntity player, Identifier id) {
        Advancement advancement = player.server.getAdvancementLoader().get(id);
        AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancement);
        if (!progress.isDone())
            for (String criteria : progress.getUnobtainedCriteria())
                player.getAdvancementTracker().grantCriterion(advancement, criteria);
    }
}
