package com.iafenvoy.neptune.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CircleShieldModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart swirl1;
    private final ModelPart swirl2;
    private final ModelPart swirl3;
    private final ModelPart swirl4;
    private final ModelPart swirl5;
    private final ModelPart swirl7;
    private final ModelPart swirl6;
    private final ModelPart swirl8;
    private final ModelPart swirl9;

    public CircleShieldModel(ModelPart root) {
        this.swirl1 = root.getChild("swirl1");
        this.swirl2 = root.getChild("swirl2");
        this.swirl3 = root.getChild("swirl3");
        this.swirl4 = root.getChild("swirl4");
        this.swirl5 = root.getChild("swirl5");
        this.swirl7 = root.getChild("swirl7");
        this.swirl6 = root.getChild("swirl6");
        this.swirl8 = root.getChild("swirl8");
        this.swirl9 = root.getChild("swirl9");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition swirl1 = modelPartData.addOrReplaceChild("swirl1", CubeListBuilder.create().texOffs(24, 46).addBox(-0.5153F, -1.8813F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0153F, 8.8813F, -17.0F));
        swirl1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 87).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1492F, 0.4847F, -0.5F, 0.0F, 0.0F, -1.0472F));
        swirl1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(80, 58).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1187F, -0.5153F, -0.5F, 0.0F, 0.0F, 2.0944F));
        swirl1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 81).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3508F, -0.3813F, -0.5F, 0.0F, 0.0F, 2.618F));
        swirl1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 87).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3813F, 0.3508F, -0.5F, 0.0F, 0.0F, -0.5236F));
        swirl1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 87).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1187F, 0.4847F, -0.5F, 0.0F, 0.0F, -1.5708F));
        swirl1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(80, 86).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3508F, 0.3508F, -0.5F, 0.0F, 0.0F, -2.0944F));
        swirl1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(84, 70).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4847F, 0.1187F, -0.5F, 0.0F, 0.0F, -2.618F));
        swirl1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 77).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4847F, -0.1492F, -0.5F, 0.0F, 0.0F, -3.1416F));
        swirl1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(60, 69).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1492F, -0.5153F, -0.5F, 0.0F, 0.0F, 1.5708F));
        swirl1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(60, 67).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3813F, -0.3813F, -0.5F, 0.0F, 0.0F, 1.0472F));
        swirl1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(20, 64).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5153F, -0.1492F, -0.5F, 0.0F, 0.0F, 0.5236F));
        PartDefinition swirl2 = modelPartData.addOrReplaceChild("swirl2", CubeListBuilder.create().texOffs(6, 87).addBox(-1.0F, -3.7321F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.7321F, -16.5F));
        swirl2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(84, 85).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7321F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));
        swirl2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(84, 82).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -0.866F, 0.5F, 0.0F, 0.0F, -1.0472F));
        swirl2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(18, 87).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.866F, -1.5F, 0.5F, 0.0F, 0.0F, -0.5236F));
        swirl2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(62, 86).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -0.866F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(56, 86).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.866F, -1.5F, 0.5F, 0.0F, 0.0F, 0.5236F));
        swirl2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(74, 86).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.866F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(68, 86).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.866F, 1.5F, 0.5F, 0.0F, 0.0F, 0.5236F));
        swirl2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 86).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.7321F, 0.5F, 0.0F, 0.0F, 0.0F));
        swirl2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(44, 86).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.866F, 1.5F, 0.5F, 0.0F, 0.0F, -0.5236F));
        swirl2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(50, 86).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.866F, 0.5F, 0.0F, 0.0F, -1.0472F));
        swirl2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(12, 87).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7321F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));
        PartDefinition swirl3 = modelPartData.addOrReplaceChild("swirl3", CubeListBuilder.create().texOffs(52, 73).addBox(-1.5F, -5.5981F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5981F, -16.0F));
        swirl3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(84, 76).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.366F, -3.366F, -0.5F, 0.0F, 0.0F, -0.5236F));
        swirl3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(84, 67).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.866F, -2.2321F, -0.5F, 0.0F, 0.0F, -1.0472F));
        swirl3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(84, 64).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5981F, -0.5F, -0.5F, 0.0F, 0.0F, -1.5708F));
        swirl3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(84, 61).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.366F, 1.366F, -0.5F, 0.0F, 0.0F, -2.0944F));
        swirl3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(84, 58).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2321F, 2.866F, -0.5F, 0.0F, 0.0F, -2.618F));
        swirl3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(36, 84).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.5981F, -0.5F, 0.0F, 0.0F, -3.1416F));
        swirl3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(28, 84).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.366F, 3.366F, -0.5F, 0.0F, 0.0F, 2.618F));
        swirl3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(20, 84).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.866F, 2.2321F, -0.5F, 0.0F, 0.0F, 2.0944F));
        swirl3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(84, 15).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5981F, 0.5F, -0.5F, 0.0F, 0.0F, 1.5708F));
        swirl3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(84, 12).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.366F, -1.366F, -0.5F, 0.0F, 0.0F, 1.0472F));
        swirl3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(84, 79).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2321F, -2.866F, -0.5F, 0.0F, 0.0F, 0.5236F));
        PartDefinition swirl4 = modelPartData.addOrReplaceChild("swirl4", CubeListBuilder.create().texOffs(84, 0).addBox(-1.7514F, -7.8947F, -0.5385F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2486F, 8.8947F, -15.4615F));
        swirl4.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(84, 9).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2155F, -0.4306F, 1.4615F, 0.0F, 0.0F, -1.5708F));
        swirl4.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(84, 6).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7514F, -2.1627F, 1.4615F, 0.0F, 0.0F, -1.0472F));
        swirl4.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(84, 3).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4834F, -3.4306F, 1.4615F, 0.0F, 0.0F, -0.5236F));
        swirl4.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(24, 81).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2486F, -2.1627F, 1.4615F, 0.0F, 0.0F, 1.0472F));
        swirl4.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(10, 84).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9807F, -3.4306F, 1.4615F, 0.0F, 0.0F, 0.5236F));
        swirl4.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(74, 83).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7514F, 1.3014F, 1.4615F, 0.0F, 0.0F, 1.0472F));
        swirl4.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(72, 58).addBox(-2.0F, 2.0F, -1.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(64, 83).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4834F, 2.5694F, 1.4615F, 0.0F, 0.0F, 0.5236F));
        swirl4.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(54, 83).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2486F, 3.0335F, 1.4615F, 0.0F, 0.0F, 0.0F));
        swirl4.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(44, 83).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9807F, 2.5694F, 1.4615F, 0.0F, 0.0F, -0.5236F));
        swirl4.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 83).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2486F, 1.3014F, 1.4615F, 0.0F, 0.0F, -1.0472F));
        swirl4.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(34, 81).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7127F, -0.4306F, 1.4615F, 0.0F, 0.0F, -1.5708F));
        PartDefinition swirl5 = modelPartData.addOrReplaceChild("swirl5", CubeListBuilder.create().texOffs(48, 80).addBox(-2.4792F, -9.4079F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0208F, 8.4079F, -15.0F));
        swirl5.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(80, 36).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.8093F, -3.4438F, 0.5F, 0.0F, 0.0F, 2.0944F));
        swirl5.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 80).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9433F, -7.4079F, 0.5F, 0.0F, 0.0F, 2.618F));
        swirl5.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(80, 33).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.3093F, 1.4222F, 0.5F, 0.0F, 0.0F, 1.5708F));
        swirl5.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(80, 30).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.3093F, 5.8864F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl5.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(80, 27).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3452F, 8.7524F, 0.5F, 0.0F, 0.0F, 0.5236F));
        swirl5.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(12, 81).addBox(-4.0F, -3.0F, -1.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4792F, 7.2524F, 0.5F, 0.0F, 0.0F, -3.1416F));
        swirl5.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(72, 80).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3869F, 7.0203F, 0.5F, 0.0F, 0.0F, 2.618F));
        swirl5.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(60, 80).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.6189F, 4.8864F, 0.5F, 0.0F, 0.0F, 2.0944F));
        swirl5.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(80, 24).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.351F, 1.4222F, 0.5F, 0.0F, 0.0F, 1.5708F));
        swirl5.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(80, 21).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1189F, -2.4438F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl5.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(80, 18).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9849F, -5.6758F, 0.5F, 0.0F, 0.0F, 0.5236F));
        PartDefinition swirl7 = modelPartData.addOrReplaceChild("swirl7", CubeListBuilder.create().texOffs(73, 55).addBox(-3.0F, -11.1962F, -0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5981F, -14.5F));
        swirl7.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(79, 44).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7321F, -6.7321F, -0.5F, 0.0F, 0.0F, -0.5236F));
        swirl7.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(79, 40).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7321F, -4.4641F, -0.5F, 0.0F, 0.0F, -1.0472F));
        swirl7.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(33, 78).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.1962F, -1.0F, -0.5F, 0.0F, 0.0F, -1.5708F));
        swirl7.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(17, 78).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7321F, 2.7321F, -0.5F, 0.0F, 0.0F, -2.0944F));
        swirl7.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(77, 73).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.4641F, 5.7321F, -0.5F, 0.0F, 0.0F, -2.618F));
        swirl7.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(69, 77).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 7.1962F, -0.5F, 0.0F, 0.0F, -3.1416F));
        swirl7.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(53, 77).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7321F, 6.7321F, -0.5F, 0.0F, 0.0F, 2.618F));
        swirl7.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(1, 77).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7321F, 4.4641F, -0.5F, 0.0F, 0.0F, 2.0944F));
        swirl7.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(37, 74).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1962F, 1.0F, -0.5F, 0.0F, 0.0F, 1.5708F));
        swirl7.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(21, 74).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7321F, -2.7321F, -0.5F, 0.0F, 0.0F, 1.0472F));
        swirl7.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(61, 73).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4641F, -5.7321F, -0.5F, 0.0F, 0.0F, 0.5236F));
        PartDefinition swirl6 = modelPartData.addOrReplaceChild("swirl6", CubeListBuilder.create().texOffs(61, 19).addBox(-3.3925F, -15.9804F, -0.6154F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.3589F, 9.0856F, -13.9231F));
        swirl6.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(73, 49).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3207F, -1.0522F, 3.3846F, 0.0F, 0.0F, -1.5708F));
        swirl6.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(1, 71).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3925F, -4.5163F, 3.3846F, 0.0F, 0.0F, -1.0472F));
        swirl6.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(45, 61).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8566F, -7.0522F, 3.3846F, 0.0F, 0.0F, -0.5236F));
        swirl6.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(61, 33).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6075F, -4.5163F, 3.3846F, 0.0F, 0.0F, 1.0472F));
        swirl6.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(61, 26).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0716F, -7.0522F, 3.3846F, 0.0F, 0.0F, 0.5236F));
        swirl6.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(41, 68).addBox(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3925F, 2.4119F, 3.3846F, 0.0F, 0.0F, 1.0472F));
        swirl6.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(60, 39).addBox(-4.0F, 6.0F, -2.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(21, 68).addBox(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8566F, 4.9478F, 3.3846F, 0.0F, 0.0F, 0.5236F));
        swirl6.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(65, 67).addBox(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6075F, 5.876F, 3.3846F, 0.0F, 0.0F, 0.0F));
        swirl6.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(65, 61).addBox(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0716F, 4.9478F, 3.3846F, 0.0F, 0.0F, -0.5236F));
        swirl6.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(1, 65).addBox(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6075F, 2.4119F, 3.3846F, 0.0F, 0.0F, -1.0472F));
        swirl6.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(25, 61).addBox(-4.0F, 3.0F, -4.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5357F, -1.0522F, 3.3846F, 0.0F, 0.0F, -1.5708F));
        PartDefinition swirl8 = modelPartData.addOrReplaceChild("swirl8", CubeListBuilder.create().texOffs(2, 2).addBox(-6.0F, -22.5478F, -0.5F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.7536F, -12.5F));
        swirl8.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(2, 26).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4641F, -13.6196F, -1.5F, 0.0F, 0.0F, -0.5236F));
        swirl8.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(2, 18).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.4641F, -9.0837F, -1.5F, 0.0F, 0.0F, -1.0472F));
        swirl8.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(2, 41).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.3923F, -2.1555F, -1.5F, 0.0F, 0.0F, -1.5708F));
        swirl8.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(32, 37).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.4641F, 5.3086F, -1.5F, 0.0F, 0.0F, -2.0944F));
        swirl8.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(2, 34).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.9282F, 11.3086F, -1.5F, 0.0F, 0.0F, -2.618F));
        swirl8.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(32, 30).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 14.2368F, -1.5F, 0.0F, 0.0F, -3.1416F));
        swirl8.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(32, 23).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4641F, 13.3086F, -1.5F, 0.0F, 0.0F, 2.618F));
        swirl8.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(32, 16).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4641F, 8.7727F, -1.5F, 0.0F, 0.0F, 2.0944F));
        swirl8.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(32, 9).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.3923F, 1.8445F, -1.5F, 0.0F, 0.0F, 1.5708F));
        swirl8.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(32, 2).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.4641F, -5.6196F, -1.5F, 0.0F, 0.0F, 1.0472F));
        swirl8.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(2, 10).addBox(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.9282F, -11.6196F, -1.5F, 0.0F, 0.0F, 0.5236F));
        PartDefinition swirl9 = modelPartData.addOrReplaceChild("swirl9", CubeListBuilder.create().texOffs(31, 43).addBox(-5.0569F, -18.6033F, -0.5F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0569F, 8.2732F, -13.5F));
        swirl9.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(61, 7).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.7172F, -6.6751F, 1.5F, 0.0F, 0.0F, 2.0944F));
        swirl9.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(61, 1).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9851F, -14.6033F, 1.5F, 0.0F, 0.0F, 2.618F));
        swirl9.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(1, 59).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.7172F, 3.0569F, 1.5F, 0.0F, 0.0F, 1.5708F));
        swirl9.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(49, 55).addBox(-8.0F, -5.0F, -2.0F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.7172F, 11.9851F, 1.5F, 0.0F, 0.0F, 1.0472F));
        swirl9.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(55, 43).addBox(-8.0F, -5.0F, -2.0F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.789F, 17.7172F, 1.5F, 0.0F, 0.0F, 0.5236F));
        swirl9.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(25, 55).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0569F, 14.7172F, 1.5F, 0.0F, 0.0F, -3.1416F));
        swirl9.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(1, 53).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6751F, 14.2531F, 1.5F, 0.0F, 0.0F, 2.618F));
        swirl9.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(49, 49).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.1392F, 9.9851F, 1.5F, 0.0F, 0.0F, 2.0944F));
        swirl9.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(25, 49).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.6033F, 3.0569F, 1.5F, 0.0F, 0.0F, 1.5708F));
        swirl9.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(1, 47).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.1392F, -4.6751F, 1.5F, 0.0F, 0.0F, 1.0472F));
        swirl9.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(61, 13).addBox(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.8713F, -11.1392F, 1.5F, 0.0F, 0.0F, 0.5236F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrices, @NotNull VertexConsumer vertexConsumer, int light, int overlay, int color) {
        this.swirl1.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl2.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl3.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl4.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl5.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl7.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl6.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl8.render(matrices, vertexConsumer, light, overlay, color);
        this.swirl9.render(matrices, vertexConsumer, light, overlay, color);
    }
}