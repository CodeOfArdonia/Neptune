package com.iafenvoy.neptune.power.type;

import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerData;
import com.iafenvoy.neptune.power.PowerDataHolder;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class PersistPower extends AbstractPower<PersistPower> {
    private Consumer<PowerDataHolder> unapply = data -> {
    }, tick = data -> {
    };
    @Nullable
    private Supplier<SoundEvent> unapplySound;

    public PersistPower(Identifier id, PowerCategory category) {
        super(id, category);
    }

    public PersistPower setUnapplySound(Supplier<SoundEvent> unapplySound) {
        this.unapplySound = unapplySound;
        return this;
    }

    public PersistPower onUnapply(Consumer<PowerDataHolder> unapply) {
        this.unapply = unapply;
        return this;
    }

    public PersistPower onTick(Consumer<PowerDataHolder> tick) {
        this.tick = tick;
        return this;
    }

    @Override
    public void unapply(PowerData.SinglePowerData data) {
        PowerDataHolder holder = new PowerDataHolder(data);
        playSound(holder, this.unapplySound);
        this.unapply.accept(holder);
        holder.cooldown();
        super.unapply(data);
    }

    public boolean tick(PowerData.SinglePowerData data) {
        PowerDataHolder holder = new PowerDataHolder(data);
        this.tick.accept(holder);
        if (!holder.isCancelled()) data.getPlayer().addExhaustion((float) (this.getExhaustion(data) / 20));
        return holder.isCancelled();
    }

    @Override
    protected boolean applyInternal(PowerDataHolder holder) {
        playSound(holder, this.applySound);
        this.apply.accept(holder);
        return true;
    }

    @Override
    protected PowerType getType() {
        return PowerType.PERSIST;
    }

    @Override
    protected PersistPower get() {
        return this;
    }
}
