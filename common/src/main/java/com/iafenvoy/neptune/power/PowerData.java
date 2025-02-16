package com.iafenvoy.neptune.power;

import com.iafenvoy.neptune.impl.ComponentManager;
import com.iafenvoy.neptune.power.type.AbstractPower;
import com.iafenvoy.neptune.power.type.DummyPower;
import com.iafenvoy.neptune.power.type.PersistPower;
import com.iafenvoy.neptune.util.Serializable;
import com.iafenvoy.neptune.util.Tickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PowerData implements Serializable, Tickable {
    private final PlayerEntity player;
    private final Map<Identifier, Serializable> components = new ConcurrentHashMap<>();
    private final Map<PowerCategory, SinglePowerData> powerData = new HashMap<>();
    private final Set<PowerCategory> enabled = new HashSet<>();
    private boolean dirty = false;

    public PowerData(PlayerEntity player) {
        this.player = player;
    }

    protected SinglePowerData createSingle(PowerData parent, PowerCategory type) {
        SinglePowerData data = new SinglePowerData(parent, type);
        this.components.put(type.getId(), data);
        this.powerData.put(type, data);
        return data;
    }

    @Override
    public void encode(NbtCompound nbt) {
        nbt.put("enabled", this.enabled.stream().reduce(new NbtList(), (p, c) -> {
            p.add(NbtString.of(c.getId().toString()));
            return p;
        }, (a, b) -> a));
        for (Map.Entry<Identifier, Serializable> entry : this.components.entrySet())
            nbt.put(entry.getKey().toString(), entry.getValue().encode());
    }

    @Override
    public void decode(NbtCompound nbt) {
        this.enabled.clear();
        this.enabled.addAll(nbt.getList("enabled", NbtElement.STRING_TYPE).stream().map(NbtElement::asString).map(Identifier::tryParse).map(PowerCategory::byId).filter(Optional::isPresent).map(Optional::get).toList());
        for (Map.Entry<Identifier, Serializable> entry : this.components.entrySet())
            if (nbt.contains(entry.getKey().toString(), NbtElement.COMPOUND_TYPE))
                entry.getValue().decode(nbt.getCompound(entry.getKey().toString()));
    }

    @Override
    public void tick() {
        if (this.player.getEntityWorld() instanceof ServerWorld)
            for (Map.Entry<Identifier, Serializable> entry : this.components.entrySet())
                if (entry.getValue() instanceof Tickable tickable)
                    tickable.tick();
    }

    public boolean isDirty() {
        boolean dirty = this.dirty;
        this.dirty = false;
        return dirty;
    }

    public void markDirty() {
        this.dirty = true;
    }

    public boolean isEnabled(PowerCategory... category) {
        return Arrays.stream(category).allMatch(this.enabled::contains);
    }

    public void setEnabled(boolean enabled, PowerCategory... categories) {
        for (PowerCategory category : categories)
            if (enabled) this.enabled.add(category);
            else this.enabled.remove(category);
        this.markDirty();
    }

    public void enable(PowerCategory... categories) {
        this.setEnabled(true, categories);
    }

    public void disable(PowerCategory... categories) {
        this.setEnabled(false, categories);
    }

    public SinglePowerData get(PowerCategory category) {
        return this.powerData.get(category);
    }

    public void addComponent(Identifier id, Serializable serializable) {
        this.components.put(id, serializable);
    }

    public Serializable getComponent(Identifier id) {
        return this.components.getOrDefault(id, Serializable.EMPTY);
    }

    public void removeComponent(Identifier id) {
        this.components.remove(id);
    }

    public boolean powerEnabled(AbstractPower<?>... powers) {
        for (AbstractPower<?> power : powers)
            if (this.powerEnabled(power.getCategory(), power))
                return true;
        return false;
    }

    public boolean powerEnabled(PowerCategory category, AbstractPower<?> power) {
        SinglePowerData data = this.get(category);
        return data.hasPower() && data.getActivePower() == power && data.isEnabled();
    }

    public void disableAllPower() {
        this.powerData.values().forEach(SinglePowerData::disable);
    }

    public static PowerData byPlayer(@Nullable PlayerEntity player) {
        if (player == null) return new PowerData(null);
        return ComponentManager.getPowerData(player);
    }

    public static void stop(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            PowerData data = byPlayer(player);
            data.disableAllPower();
        }
    }

    public static class SinglePowerData implements Serializable, Tickable {
        private final PowerData parent;
        private final PowerCategory type;
        private AbstractPower<?> activePower = DummyPower.EMPTY;
        private boolean enabled = false;
        private int primaryCooldown = 0;
        private int secondaryCooldown = 0;

        public SinglePowerData(PowerData parent, PowerCategory type) {
            this.parent = parent;
            this.type = type;
        }

        @Override
        public void encode(NbtCompound nbt) {
            nbt.putBoolean("enabled", this.enabled);
            nbt.putInt("primaryCooldown", this.primaryCooldown);
            nbt.putInt("secondaryCooldown", this.secondaryCooldown);
            nbt.putString("activePower", this.activePower.getId().toString());
        }

        @Override
        public void decode(NbtCompound nbt) {
            this.enabled = nbt.getBoolean("enabled");
            this.primaryCooldown = nbt.getInt("primaryCooldown");
            this.secondaryCooldown = nbt.getInt("secondaryCooldown");
            this.activePower = AbstractPower.byId(Identifier.tryParse(nbt.getString("activePower")));
        }

        @Override
        public void tick() {
            State state = this.getState();
            if (state == State.DENY) {
                this.primaryCooldown--;
                this.parent.markDirty();
            } else if (state == State.RECOVER) {
                this.secondaryCooldown--;
                this.parent.markDirty();
            }
            if (this.isEnabled() && !this.parent.player.getEntityWorld().isClient && this.activePower instanceof PersistPower persistSongPower) {
                if (persistSongPower.tick(this)) this.disable();
                this.parent.markDirty();
            }
        }

        public void keyPress() {
            if (this.activePower.isEmpty()) {
                this.disable();
                return;
            }
            if (this.getState() == State.DENY) return;
            if (this.isEnabled()) {
                if (this.activePower.isPersist()) this.disable();
                else this.activePower.unapply(this);
            } else {
                if (this.activePower.isPersist()) this.enable();
                else if (this.getState() != State.DENY) {
                    boolean bl = this.getState() == State.RECOVER;
                    if (this.activePower.apply(this) && bl) {
                        this.getPlayer().addExhaustion((float) this.activePower.getExhaustion(this));
                        this.secondaryCooldown = 0;
                    }
                }
            }
        }

        public PowerCategory getType() {
            return this.type;
        }

        public boolean isEnabled() {
            return this.enabled && this.parent.enabled.contains(this.type);
        }

        public void setEnabled(boolean enabled) {
            if (!this.enabled && enabled)
                this.activePower.apply(this);
            if (this.enabled && !enabled)
                this.activePower.unapply(this);
            this.enabled = enabled;
            this.parent.markDirty();
        }

        public void enable() {
            this.setEnabled(true);
        }

        public void disable() {
            this.setEnabled(false);
        }

        public AbstractPower<?> getActivePower() {
            return this.activePower;
        }

        public void setActivePower(AbstractPower<?> activePower) {
            if (!this.activePower.isEmpty() && this.activePower != activePower)
                this.disable();
            this.activePower = activePower;
            this.parent.markDirty();
        }

        public boolean hasPower() {
            return !this.activePower.isEmpty();
        }

        public PlayerEntity getPlayer() {
            return this.parent.player;
        }

        public int getPrimaryCooldown() {
            return this.primaryCooldown;
        }

        public int getSecondaryCooldown() {
            return this.secondaryCooldown;
        }

        public State getState() {
            if (this.primaryCooldown > 0) return State.DENY;
            if (this.secondaryCooldown > 0) return State.RECOVER;
            return State.ALLOW;
        }

        public void cooldown() {
            this.primaryCooldown = this.activePower.getPrimaryCooldown(this);
            this.secondaryCooldown = this.activePower.getSecondaryCooldown(this);
        }
    }

    public enum State {
        ALLOW, RECOVER, DENY
    }
}
