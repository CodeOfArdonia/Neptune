package com.iafenvoy.neptune.object.entity;

import com.iafenvoy.neptune.render.Stage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class StagedMonsterEntityBase extends Monster implements Stage.StagedEntity {
    private static final EntityDataAccessor<Integer> STAGE = SynchedEntityData.defineId(StagedMonsterEntityBase.class, EntityDataSerializers.INT);
    private final Stage stage;

    protected StagedMonsterEntityBase(EntityType<? extends Monster> entityType, Level world, Stage stage) {
        super(entityType, world);
        this.stage = stage;
        this.entityData.set(STAGE, this.stage.getIndex());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(STAGE, 0);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        if (nbt.contains("stage"))
            this.setStage(nbt.getInt("stage"));
        else this.setStage(this.stage);
    }

    @Override
    public CompoundTag saveWithoutId(CompoundTag nbt) {
        super.saveWithoutId(nbt);
        nbt.putInt("stage", this.getStageIndex());
        return nbt;
    }

    public void setStage(Stage stage) {
        this.setStage(stage.getIndex());
    }

    public void setStage(int stage) {
        this.entityData.set(STAGE, stage, true);
    }

    @Override
    public Stage getStage() {
        return Stage.getByIndex(this.getStageIndex());
    }

    public int getStageIndex() {
        return this.entityData.get(STAGE);
    }
}
