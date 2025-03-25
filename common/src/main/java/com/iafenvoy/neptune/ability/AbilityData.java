package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.iafenvoy.neptune.ability.type.PersistAbility;
import com.iafenvoy.neptune.impl.ComponentManager;
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

public class AbilityData implements Serializable, Tickable {
    private final PlayerEntity player;
    private final Map<Identifier, Serializable> components = new ConcurrentHashMap<>();
    private final Map<AbilityCategory, SingleAbilityData> abilityData = new HashMap<>();
    private final Set<AbilityCategory> enabled = new HashSet<>();
    private boolean dirty = false;

    public AbilityData(PlayerEntity player) {
        this.player = player;
        for (AbilityCategory category : AbilityCategory.values())
            this.createSingle(category);
    }

    protected void createSingle(AbilityCategory type) {
        SingleAbilityData data = new SingleAbilityData(this, type);
        this.components.put(type.getId(), data);
        this.abilityData.put(type, data);
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
        this.enabled.addAll(nbt.getList("enabled", NbtElement.STRING_TYPE).stream().map(NbtElement::asString).map(Identifier::tryParse).map(AbilityCategory::byId).filter(Optional::isPresent).map(Optional::get).toList());
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

    public boolean isEnabled(AbilityCategory... category) {
        return Arrays.stream(category).allMatch(this.enabled::contains);
    }

    public void setEnabled(boolean enabled, AbilityCategory... categories) {
        for (AbilityCategory category : categories)
            if (enabled) this.enabled.add(category);
            else {
                this.enabled.remove(category);
                this.abilityData.get(category).disable();
            }
        this.markDirty();
    }

    public void enable(AbilityCategory... categories) {
        this.setEnabled(true, categories);
    }

    public void disable(AbilityCategory... categories) {
        this.setEnabled(false, categories);
    }

    public SingleAbilityData get(AbilityCategory category) {
        return this.abilityData.get(category);
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

    public boolean abilityEnabled(AbstractAbility<?>... abilities) {
        for (AbstractAbility<?> ability : abilities)
            if (this.abilityEnabled(ability.getCategory(), ability))
                return true;
        return false;
    }

    public boolean abilityEnabled(AbilityCategory category, AbstractAbility<?> ability) {
        SingleAbilityData data = this.get(category);
        return data.hasAbility() && data.getActiveAbility() == ability && data.isEnabled();
    }

    public void disableAllAbility() {
        this.abilityData.values().forEach(SingleAbilityData::disable);
    }

    public static AbilityData byPlayer(@Nullable PlayerEntity player) {
        if (player == null) return new AbilityData(null);
        return ComponentManager.getAbilityData(player);
    }

    public static void stop(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            AbilityData data = byPlayer(player);
            data.disableAllAbility();
        }
    }

    public static class SingleAbilityData implements Serializable, Tickable {
        private final AbilityData parent;
        private final AbilityCategory type;
        private AbstractAbility<?> activeAbility = DummyAbility.EMPTY;
        private boolean enabled = false;
        private int primaryCooldown = 0;
        private int secondaryCooldown = 0;

        public SingleAbilityData(AbilityData parent, AbilityCategory type) {
            this.parent = parent;
            this.type = type;
        }

        @Override
        public void encode(NbtCompound nbt) {
            nbt.putBoolean("enabled", this.enabled);
            nbt.putInt("primaryCooldown", this.primaryCooldown);
            nbt.putInt("secondaryCooldown", this.secondaryCooldown);
            nbt.putString("activeAbility", this.activeAbility.getId().toString());
        }

        @Override
        public void decode(NbtCompound nbt) {
            this.enabled = nbt.getBoolean("enabled");
            this.primaryCooldown = nbt.getInt("primaryCooldown");
            this.secondaryCooldown = nbt.getInt("secondaryCooldown");
            this.activeAbility = AbstractAbility.byId(Identifier.tryParse(nbt.getString("activeAbility")));
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
            if (this.isEnabled() && !this.parent.player.getEntityWorld().isClient && this.activeAbility instanceof PersistAbility persistAbility) {
                if (persistAbility.tick(this)) this.disable();
                this.parent.markDirty();
            }
        }

        public void keyPress() {
            if (this.activeAbility.isEmpty()) {
                this.disable();
                return;
            }
            if (this.getState() == State.DENY) return;
            if (this.isEnabled()) {
                if (this.activeAbility.isPersist()) this.disable();
                else this.activeAbility.unapply(this);
            } else {
                if (this.activeAbility.isPersist()) this.enable();
                else if (this.getState() != State.DENY) {
                    boolean bl = this.getState() == State.RECOVER;
                    if (this.activeAbility.apply(this) && bl) {
                        this.getPlayer().addExhaustion((float) this.activeAbility.getExhaustion(this));
                        this.secondaryCooldown = 0;
                    }
                }
            }
        }

        public AbilityCategory getType() {
            return this.type;
        }

        public boolean allowEnable() {
            return this.parent.enabled.contains(this.type);
        }

        public boolean isEnabled() {
            return this.allowEnable() && this.enabled;
        }

        public void setEnabled(boolean enabled) {
            if (!this.enabled && enabled)
                this.activeAbility.apply(this);
            if (this.enabled && !enabled)
                this.activeAbility.unapply(this);
            this.enabled = enabled;
            this.parent.markDirty();
        }

        public void enable() {
            this.setEnabled(true);
        }

        public void disable() {
            this.setEnabled(false);
        }

        public AbstractAbility<?> getActiveAbility() {
            return this.activeAbility;
        }

        public void setActiveAbility(AbstractAbility<?> activeAbility) {
            if (!this.activeAbility.isEmpty() && this.activeAbility != activeAbility)
                this.disable();
            this.activeAbility = activeAbility;
            this.parent.markDirty();
        }

        public void removeAbility() {
            this.setActiveAbility(DummyAbility.EMPTY);
        }

        public boolean hasAbility() {
            return !this.activeAbility.isEmpty();
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
            this.primaryCooldown = this.activeAbility.getPrimaryCooldown(this);
            this.secondaryCooldown = this.activeAbility.getSecondaryCooldown(this);
        }
    }

    public enum State {
        ALLOW, RECOVER, DENY
    }
}
