package com.iafenvoy.neptune.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;

public class AbilityDataHolder {
    public static final float DAMAGE_MUL = 1.5f;
    private final AbilityData.SingleAbilityData data;
    private boolean cancelled = false;

    public AbilityDataHolder(AbilityData.SingleAbilityData data) {
        this.data = data;
    }

    public AbilityData.SingleAbilityData getData() {
        return this.data;
    }

    public PlayerEntity getPlayer() {
        return this.data.getPlayer();
    }

    public World getWorld() {
        return this.data.getPlayer().getEntityWorld();
    }

    public boolean usingWeapon() {
        return this.getPlayer().getMainHandStack().getItem() instanceof SwordItem;
    }

    public void processProjectile(PersistentProjectileEntity projectile) {
        PlayerEntity player = this.getPlayer();
        projectile.setOwner(player);
        projectile.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
        projectile.refreshPositionAndAngles(player.getX(), player.getY() + 1, player.getZ(), 0, 0);
        if (this.usingWeapon()) projectile.setCritical(true);
    }

    public float processDamage(float damage) {
        return damage * (this.usingWeapon() ? DAMAGE_MUL : 1);
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void cooldown() {
        this.data.cooldown();
    }
}
