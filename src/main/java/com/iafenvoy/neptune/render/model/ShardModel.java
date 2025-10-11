package com.iafenvoy.neptune.render.model;

import com.iafenvoy.neptune.Neptune;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ShardModel<T extends Entity> extends EntityModel<T> {
    public static final ResourceLocation SHARD_TEXTURE_WHITE = ResourceLocation.tryBuild(Neptune.MOD_ID, "textures/entity/shard.png");
    private final ModelPart root;

    public ShardModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));
        root.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, -0.1243F, -0.51F, 0.2506F));
        root.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(2, -2).addBox(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.1243F, -0.51F, 0.2506F));
        root.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.1243F, 0.51F, 0.2506F));
        root.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(2, 6).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.1243F, 0.51F, 0.2506F));
        root.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(2, 10).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.1243F, 0.51F, -0.2506F));
        root.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, -0.1243F, 0.51F, -0.2506F));
        root.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(2, 2).addBox(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.1243F, -0.51F, -0.2506F));
        root.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, -2).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.1243F, -0.51F, -0.2506F));
        return LayerDefinition.create(modelData, 16, 16);
    }


    @Override
    public void setupAnim(@NotNull T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrices, @NotNull VertexConsumer vertices, int light, int overlay, int color) {
        this.root.render(matrices, vertices, light, overlay, color);
    }
}
