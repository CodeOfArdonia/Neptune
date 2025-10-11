package com.iafenvoy.neptune.ability;

import com.iafenvoy.neptune.ability.type.AbstractAbility;
import com.iafenvoy.neptune.ability.type.DummyAbility;
import com.iafenvoy.neptune.ability.type.PersistAbility;
import com.iafenvoy.neptune.registry.NeptuneAttachments;
import com.iafenvoy.neptune.util.Serializable;
import com.iafenvoy.neptune.util.Tickable;
import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber
public class AbilityData implements Serializable, Tickable {
    //FIXME::Do not use NBT
    public static final Codec<AbilityData> CODEC = CompoundTag.CODEC.xmap(AbilityData::new, Serializable::encode);
    private final Map<ResourceLocation, Serializable> components = new ConcurrentHashMap<>();
    private final Map<AbilityCategory, SingleAbilityData> abilityData = new HashMap<>();
    private final Set<AbilityCategory> enabled = new HashSet<>();
    private boolean dirty = false;

    public AbilityData() {
        for (AbilityCategory category : AbilityCategory.values())
            this.createSingle(category);
    }

    private AbilityData(CompoundTag tag) {
        this.decode(tag);
    }

    protected void createSingle(AbilityCategory type) {
        SingleAbilityData data = new SingleAbilityData(this, type);
        this.components.put(type.getId(), data);
        this.abilityData.put(type, data);
    }

    @Override
    public void encode(CompoundTag nbt) {
        nbt.put("enabled", this.enabled.stream().reduce(new ListTag(), (p, c) -> {
            p.add(StringTag.valueOf(c.getId().toString()));
            return p;
        }, (a, b) -> a));
        for (Map.Entry<ResourceLocation, Serializable> entry : this.components.entrySet())
            nbt.put(entry.getKey().toString(), entry.getValue().encode());
    }

    @Override
    public void decode(CompoundTag nbt) {
        this.enabled.clear();
        this.enabled.addAll(nbt.getList("enabled", Tag.TAG_STRING).stream().map(Tag::getAsString).map(ResourceLocation::tryParse).map(AbilityCategory::byId).filter(Optional::isPresent).map(Optional::get).toList());
        for (Map.Entry<ResourceLocation, Serializable> entry : this.components.entrySet())
            if (nbt.contains(entry.getKey().toString(), Tag.TAG_COMPOUND))
                entry.getValue().decode(nbt.getCompound(entry.getKey().toString()));
    }

    @Override
    public void tick(LivingEntity living) {
        if (living.getCommandSenderWorld() instanceof ServerLevel)
            for (Map.Entry<ResourceLocation, Serializable> entry : this.components.entrySet())
                if (entry.getValue() instanceof Tickable tickable)
                    tickable.tick(living);
    }

    @Override
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

    public void setEnabled(LivingEntity living, boolean enabled, AbilityCategory... categories) {
        for (AbilityCategory category : categories)
            if (enabled) this.enabled.add(category);
            else {
                this.enabled.remove(category);
                this.abilityData.get(category).disable(living);
            }
        this.markDirty();
    }

    public void enable(LivingEntity living, AbilityCategory... categories) {
        this.setEnabled(living, true, categories);
    }

    public void disable(LivingEntity living, AbilityCategory... categories) {
        this.setEnabled(living, false, categories);
    }

    public SingleAbilityData get(AbilityCategory category) {
        return this.abilityData.get(category);
    }

    public void addComponent(ResourceLocation id, Serializable serializable) {
        this.components.put(id, serializable);
    }

    public Serializable getComponent(ResourceLocation id) {
        return this.components.getOrDefault(id, Serializable.EMPTY);
    }

    public void removeComponent(ResourceLocation id) {
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

    public void disableAllAbility(LivingEntity living) {
        this.abilityData.values().forEach(x -> x.disable(living));
    }

    public static AbilityData get(@Nullable LivingEntity living) {
        if (living == null) return new AbilityData(null);
        return living.getData(NeptuneAttachments.ABILITY);
    }

    @SubscribeEvent
    public static void stop(ServerStoppingEvent event) {
        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            AbilityData data = get(player);
            data.disableAllAbility(player);
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
        public void encode(CompoundTag nbt) {
            nbt.putBoolean("enabled", this.enabled);
            nbt.putInt("primaryCooldown", this.primaryCooldown);
            nbt.putInt("secondaryCooldown", this.secondaryCooldown);
            nbt.putString("activeAbility", this.activeAbility.getId().toString());
        }

        @Override
        public void decode(CompoundTag nbt) {
            this.enabled = nbt.getBoolean("enabled");
            this.primaryCooldown = nbt.getInt("primaryCooldown");
            this.secondaryCooldown = nbt.getInt("secondaryCooldown");
            this.activeAbility = AbstractAbility.byId(ResourceLocation.tryParse(nbt.getString("activeAbility")));
        }

        @Override
        public void tick(LivingEntity living) {
            State state = this.getState();
            if (state == State.DENY) {
                this.primaryCooldown--;
                this.parent.markDirty();
            } else if (state == State.RECOVER) {
                this.secondaryCooldown--;
                this.parent.markDirty();
            }
            if (this.isEnabled() && !living.getCommandSenderWorld().isClientSide && this.activeAbility instanceof PersistAbility persistAbility) {
                if (persistAbility.tick(this, living)) this.disable(living);
                this.parent.markDirty();
            }
        }

        @Override
        public boolean isDirty() {
            return false;//Never call
        }

        public void onKeyPressed(Player player) {
            if (this.activeAbility.isEmpty()) {
                this.disable(player);
                return;
            }
            if (this.getState() == State.DENY) return;
            if (this.isEnabled()) {
                if (this.activeAbility.isPersist()) this.disable(player);
                else this.activeAbility.unapply(this, player);
            } else {
                if (this.activeAbility.isPersist()) this.enable(player);
                else if (this.getState() != State.DENY) {
                    boolean bl = this.getState() == State.RECOVER;
                    if (this.activeAbility.apply(this, player) && bl) {
                        player.causeFoodExhaustion((float) this.activeAbility.getExhaustion(this, player));
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

        public void setEnabled(LivingEntity living, boolean enabled) {
            if (!this.enabled && enabled)
                this.activeAbility.apply(this, living);
            if (this.enabled && !enabled)
                this.activeAbility.unapply(this, living);
            this.enabled = enabled;
            this.parent.markDirty();
        }

        public void enable(LivingEntity living) {
            this.setEnabled(living, true);
        }

        public void disable(LivingEntity living) {
            this.setEnabled(living, false);
        }

        public AbstractAbility<?> getActiveAbility() {
            return this.activeAbility;
        }

        public void setActiveAbility(LivingEntity living, AbstractAbility<?> activeAbility) {
            if (!this.activeAbility.isEmpty() && this.activeAbility != activeAbility)
                this.disable(living);
            this.activeAbility = activeAbility;
            this.parent.markDirty();
        }

        public void removeAbility(LivingEntity living) {
            this.setActiveAbility(living, DummyAbility.EMPTY);
        }

        public boolean hasAbility() {
            return !this.activeAbility.isEmpty();
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

        public void cooldown(LivingEntity living) {
            this.primaryCooldown = this.activeAbility.getPrimaryCooldown(this, living);
            this.secondaryCooldown = this.activeAbility.getSecondaryCooldown(this, living);
        }
    }

    public enum State {
        ALLOW, RECOVER, DENY
    }
}
