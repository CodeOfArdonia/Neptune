package com.iafenvoy.neptune.object.entity;

import com.iafenvoy.neptune.render.BossBarRenderHelper;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EntityWithBossBar extends Monster {
    public final ServerBossEvent bossBar;

    protected EntityWithBossBar(EntityType<? extends Monster> entityType, Level world, BossEvent.BossBarColor barColor) {
        super(entityType, world);
        this.bossBar = new ServerBossEvent(this.getDisplayName(), barColor, BossEvent.BossBarOverlay.PROGRESS);
        BossBarRenderHelper.addBossBar(this.getClass(), this.bossBar.getId());
    }

    @Override
    public void onClientRemoval() {
        super.onClientRemoval();
        BossBarRenderHelper.removeBossBar(this.getClass(), this.bossBar.getId());
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
