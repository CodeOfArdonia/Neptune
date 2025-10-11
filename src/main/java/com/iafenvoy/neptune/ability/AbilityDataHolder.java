package com.iafenvoy.neptune.ability;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class AbilityDataHolder {
    public static final float DAMAGE_MUL = 1.5f;
    private final AbilityData.SingleAbilityData data;
    private final LivingEntity living;
    private boolean cancelled = false;

    public AbilityDataHolder(AbilityData.SingleAbilityData data, LivingEntity living) {
        this.data = data;
        this.living = living;
    }

    public AbilityData.SingleAbilityData getData() {
        return this.data;
    }

    public LivingEntity getEntity() {
        return this.living;
    }

    public Level getWorld() {
        return this.living.getCommandSenderWorld();
    }

    public boolean usingWeapon() {
        return this.getEntity().getMainHandItem().getItem() instanceof SwordItem;
    }

    public void processProjectile(AbstractArrow projectile) {
        projectile.setOwner(this.living);
        projectile.pickup = AbstractArrow.Pickup.DISALLOWED;
        projectile.moveTo(this.living.getX(), this.living.getY() + 1, this.living.getZ(), 0, 0);
        if (this.usingWeapon()) projectile.setCritArrow(true);
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
        this.data.cooldown(this.living);
    }
}
