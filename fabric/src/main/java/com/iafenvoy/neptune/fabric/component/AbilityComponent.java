package com.iafenvoy.neptune.fabric.component;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.AbilityData;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AbilityComponent implements ComponentV3, AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<AbilityComponent> COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(Objects.requireNonNull(Identifier.of(Neptune.MOD_ID, "ability")), AbilityComponent.class);

    private final PlayerEntity entity;
    private final AbilityData data;

    public AbilityComponent(PlayerEntity entity) {
        this.entity = entity;
        this.data = new AbilityData(entity);
    }

    public PlayerEntity getEntity() {
        return this.entity;
    }

    public AbilityData getData() {
        return this.data;
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound tag) {
        this.data.decode(tag);
    }

    @Override
    public void writeToNbt(@NotNull NbtCompound tag) {
        this.data.encode(tag);
    }

    @Override
    public void tick() {
        this.data.tick();
        if (this.data.isDirty() && !this.entity.getEntityWorld().isClient)
            COMPONENT.sync(this.entity);
    }
}
