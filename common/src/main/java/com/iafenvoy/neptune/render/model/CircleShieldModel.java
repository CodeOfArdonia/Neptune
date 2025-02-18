package com.iafenvoy.neptune.render.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
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

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData swirl1 = modelPartData.addChild("swirl1", ModelPartBuilder.create().uv(24, 46).cuboid(-0.5153F, -1.8813F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0153F, 8.8813F, -17.0F));
        swirl1.addChild("cube_r1", ModelPartBuilder.create().uv(32, 87).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.1492F, 0.4847F, -0.5F, 0.0F, 0.0F, -1.0472F));
        swirl1.addChild("cube_r2", ModelPartBuilder.create().uv(80, 58).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.1187F, -0.5153F, -0.5F, 0.0F, 0.0F, 2.0944F));
        swirl1.addChild("cube_r3", ModelPartBuilder.create().uv(44, 81).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.3508F, -0.3813F, -0.5F, 0.0F, 0.0F, 2.618F));
        swirl1.addChild("cube_r4", ModelPartBuilder.create().uv(28, 87).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.3813F, 0.3508F, -0.5F, 0.0F, 0.0F, -0.5236F));
        swirl1.addChild("cube_r5", ModelPartBuilder.create().uv(24, 87).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.1187F, 0.4847F, -0.5F, 0.0F, 0.0F, -1.5708F));
        swirl1.addChild("cube_r6", ModelPartBuilder.create().uv(80, 86).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.3508F, 0.3508F, -0.5F, 0.0F, 0.0F, -2.0944F));
        swirl1.addChild("cube_r7", ModelPartBuilder.create().uv(84, 70).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.4847F, 0.1187F, -0.5F, 0.0F, 0.0F, -2.618F));
        swirl1.addChild("cube_r8", ModelPartBuilder.create().uv(48, 77).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.4847F, -0.1492F, -0.5F, 0.0F, 0.0F, -3.1416F));
        swirl1.addChild("cube_r9", ModelPartBuilder.create().uv(60, 69).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.1492F, -0.5153F, -0.5F, 0.0F, 0.0F, 1.5708F));
        swirl1.addChild("cube_r10", ModelPartBuilder.create().uv(60, 67).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.3813F, -0.3813F, -0.5F, 0.0F, 0.0F, 1.0472F));
        swirl1.addChild("cube_r11", ModelPartBuilder.create().uv(20, 64).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5153F, -0.1492F, -0.5F, 0.0F, 0.0F, 0.5236F));
        ModelPartData swirl2 = modelPartData.addChild("swirl2", ModelPartBuilder.create().uv(6, 87).cuboid(-1.0F, -3.7321F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.7321F, -16.5F));
        swirl2.addChild("cube_r12", ModelPartBuilder.create().uv(84, 85).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.7321F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));
        swirl2.addChild("cube_r13", ModelPartBuilder.create().uv(84, 82).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -0.866F, 0.5F, 0.0F, 0.0F, -1.0472F));
        swirl2.addChild("cube_r14", ModelPartBuilder.create().uv(18, 87).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.866F, -1.5F, 0.5F, 0.0F, 0.0F, -0.5236F));
        swirl2.addChild("cube_r15", ModelPartBuilder.create().uv(62, 86).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -0.866F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl2.addChild("cube_r16", ModelPartBuilder.create().uv(56, 86).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.866F, -1.5F, 0.5F, 0.0F, 0.0F, 0.5236F));
        swirl2.addChild("cube_r17", ModelPartBuilder.create().uv(74, 86).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 0.866F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl2.addChild("cube_r18", ModelPartBuilder.create().uv(68, 86).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.866F, 1.5F, 0.5F, 0.0F, 0.0F, 0.5236F));
        swirl2.addChild("cube_r19", ModelPartBuilder.create().uv(0, 86).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.7321F, 0.5F, 0.0F, 0.0F, 0.0F));
        swirl2.addChild("cube_r20", ModelPartBuilder.create().uv(44, 86).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.866F, 1.5F, 0.5F, 0.0F, 0.0F, -0.5236F));
        swirl2.addChild("cube_r21", ModelPartBuilder.create().uv(50, 86).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 0.866F, 0.5F, 0.0F, 0.0F, -1.0472F));
        swirl2.addChild("cube_r22", ModelPartBuilder.create().uv(12, 87).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.7321F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));
        ModelPartData swirl3 = modelPartData.addChild("swirl3", ModelPartBuilder.create().uv(52, 73).cuboid(-1.5F, -5.5981F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.5981F, -16.0F));
        swirl3.addChild("cube_r23", ModelPartBuilder.create().uv(84, 76).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.366F, -3.366F, -0.5F, 0.0F, 0.0F, -0.5236F));
        swirl3.addChild("cube_r24", ModelPartBuilder.create().uv(84, 67).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.866F, -2.2321F, -0.5F, 0.0F, 0.0F, -1.0472F));
        swirl3.addChild("cube_r25", ModelPartBuilder.create().uv(84, 64).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.5981F, -0.5F, -0.5F, 0.0F, 0.0F, -1.5708F));
        swirl3.addChild("cube_r26", ModelPartBuilder.create().uv(84, 61).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.366F, 1.366F, -0.5F, 0.0F, 0.0F, -2.0944F));
        swirl3.addChild("cube_r27", ModelPartBuilder.create().uv(84, 58).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.2321F, 2.866F, -0.5F, 0.0F, 0.0F, -2.618F));
        swirl3.addChild("cube_r28", ModelPartBuilder.create().uv(36, 84).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 3.5981F, -0.5F, 0.0F, 0.0F, -3.1416F));
        swirl3.addChild("cube_r29", ModelPartBuilder.create().uv(28, 84).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.366F, 3.366F, -0.5F, 0.0F, 0.0F, 2.618F));
        swirl3.addChild("cube_r30", ModelPartBuilder.create().uv(20, 84).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.866F, 2.2321F, -0.5F, 0.0F, 0.0F, 2.0944F));
        swirl3.addChild("cube_r31", ModelPartBuilder.create().uv(84, 15).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.5981F, 0.5F, -0.5F, 0.0F, 0.0F, 1.5708F));
        swirl3.addChild("cube_r32", ModelPartBuilder.create().uv(84, 12).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.366F, -1.366F, -0.5F, 0.0F, 0.0F, 1.0472F));
        swirl3.addChild("cube_r33", ModelPartBuilder.create().uv(84, 79).cuboid(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.2321F, -2.866F, -0.5F, 0.0F, 0.0F, 0.5236F));
        ModelPartData swirl4 = modelPartData.addChild("swirl4", ModelPartBuilder.create().uv(84, 0).cuboid(-1.7514F, -7.8947F, -0.5385F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.2486F, 8.8947F, -15.4615F));
        swirl4.addChild("cube_r34", ModelPartBuilder.create().uv(84, 9).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.2155F, -0.4306F, 1.4615F, 0.0F, 0.0F, -1.5708F));
        swirl4.addChild("cube_r35", ModelPartBuilder.create().uv(84, 6).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.7514F, -2.1627F, 1.4615F, 0.0F, 0.0F, -1.0472F));
        swirl4.addChild("cube_r36", ModelPartBuilder.create().uv(84, 3).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.4834F, -3.4306F, 1.4615F, 0.0F, 0.0F, -0.5236F));
        swirl4.addChild("cube_r37", ModelPartBuilder.create().uv(24, 81).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.2486F, -2.1627F, 1.4615F, 0.0F, 0.0F, 1.0472F));
        swirl4.addChild("cube_r38", ModelPartBuilder.create().uv(10, 84).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.9807F, -3.4306F, 1.4615F, 0.0F, 0.0F, 0.5236F));
        swirl4.addChild("cube_r39", ModelPartBuilder.create().uv(74, 83).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.7514F, 1.3014F, 1.4615F, 0.0F, 0.0F, 1.0472F));
        swirl4.addChild("cube_r40", ModelPartBuilder.create().uv(72, 58).cuboid(-2.0F, 2.0F, -1.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)).uv(64, 83).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.4834F, 2.5694F, 1.4615F, 0.0F, 0.0F, 0.5236F));
        swirl4.addChild("cube_r41", ModelPartBuilder.create().uv(54, 83).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2486F, 3.0335F, 1.4615F, 0.0F, 0.0F, 0.0F));
        swirl4.addChild("cube_r42", ModelPartBuilder.create().uv(44, 83).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.9807F, 2.5694F, 1.4615F, 0.0F, 0.0F, -0.5236F));
        swirl4.addChild("cube_r43", ModelPartBuilder.create().uv(0, 83).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.2486F, 1.3014F, 1.4615F, 0.0F, 0.0F, -1.0472F));
        swirl4.addChild("cube_r44", ModelPartBuilder.create().uv(34, 81).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.7127F, -0.4306F, 1.4615F, 0.0F, 0.0F, -1.5708F));
        ModelPartData swirl5 = modelPartData.addChild("swirl5", ModelPartBuilder.create().uv(48, 80).cuboid(-2.4792F, -9.4079F, -0.5F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.0208F, 8.4079F, -15.0F));
        swirl5.addChild("cube_r45", ModelPartBuilder.create().uv(80, 36).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.8093F, -3.4438F, 0.5F, 0.0F, 0.0F, 2.0944F));
        swirl5.addChild("cube_r46", ModelPartBuilder.create().uv(0, 80).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.9433F, -7.4079F, 0.5F, 0.0F, 0.0F, 2.618F));
        swirl5.addChild("cube_r47", ModelPartBuilder.create().uv(80, 33).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-9.3093F, 1.4222F, 0.5F, 0.0F, 0.0F, 1.5708F));
        swirl5.addChild("cube_r48", ModelPartBuilder.create().uv(80, 30).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-7.3093F, 5.8864F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl5.addChild("cube_r49", ModelPartBuilder.create().uv(80, 27).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.3452F, 8.7524F, 0.5F, 0.0F, 0.0F, 0.5236F));
        swirl5.addChild("cube_r50", ModelPartBuilder.create().uv(12, 81).cuboid(-4.0F, -3.0F, -1.0F, 5.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.4792F, 7.2524F, 0.5F, 0.0F, 0.0F, -3.1416F));
        swirl5.addChild("cube_r51", ModelPartBuilder.create().uv(72, 80).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.3869F, 7.0203F, 0.5F, 0.0F, 0.0F, 2.618F));
        swirl5.addChild("cube_r52", ModelPartBuilder.create().uv(60, 80).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.6189F, 4.8864F, 0.5F, 0.0F, 0.0F, 2.0944F));
        swirl5.addChild("cube_r53", ModelPartBuilder.create().uv(80, 24).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.351F, 1.4222F, 0.5F, 0.0F, 0.0F, 1.5708F));
        swirl5.addChild("cube_r54", ModelPartBuilder.create().uv(80, 21).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.1189F, -2.4438F, 0.5F, 0.0F, 0.0F, 1.0472F));
        swirl5.addChild("cube_r55", ModelPartBuilder.create().uv(80, 18).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.9849F, -5.6758F, 0.5F, 0.0F, 0.0F, 0.5236F));
        ModelPartData swirl7 = modelPartData.addChild("swirl7", ModelPartBuilder.create().uv(73, 55).cuboid(-3.0F, -11.1962F, -0.5F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.5981F, -14.5F));
        swirl7.addChild("cube_r56", ModelPartBuilder.create().uv(79, 44).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.7321F, -6.7321F, -0.5F, 0.0F, 0.0F, -0.5236F));
        swirl7.addChild("cube_r57", ModelPartBuilder.create().uv(79, 40).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.7321F, -4.4641F, -0.5F, 0.0F, 0.0F, -1.0472F));
        swirl7.addChild("cube_r58", ModelPartBuilder.create().uv(33, 78).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-7.1962F, -1.0F, -0.5F, 0.0F, 0.0F, -1.5708F));
        swirl7.addChild("cube_r59", ModelPartBuilder.create().uv(17, 78).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-6.7321F, 2.7321F, -0.5F, 0.0F, 0.0F, -2.0944F));
        swirl7.addChild("cube_r60", ModelPartBuilder.create().uv(77, 73).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.4641F, 5.7321F, -0.5F, 0.0F, 0.0F, -2.618F));
        swirl7.addChild("cube_r61", ModelPartBuilder.create().uv(69, 77).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 7.1962F, -0.5F, 0.0F, 0.0F, -3.1416F));
        swirl7.addChild("cube_r62", ModelPartBuilder.create().uv(53, 77).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.7321F, 6.7321F, -0.5F, 0.0F, 0.0F, 2.618F));
        swirl7.addChild("cube_r63", ModelPartBuilder.create().uv(1, 77).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.7321F, 4.4641F, -0.5F, 0.0F, 0.0F, 2.0944F));
        swirl7.addChild("cube_r64", ModelPartBuilder.create().uv(37, 74).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.1962F, 1.0F, -0.5F, 0.0F, 0.0F, 1.5708F));
        swirl7.addChild("cube_r65", ModelPartBuilder.create().uv(21, 74).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.7321F, -2.7321F, -0.5F, 0.0F, 0.0F, 1.0472F));
        swirl7.addChild("cube_r66", ModelPartBuilder.create().uv(61, 73).cuboid(-4.0F, -4.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.4641F, -5.7321F, -0.5F, 0.0F, 0.0F, 0.5236F));
        ModelPartData swirl6 = modelPartData.addChild("swirl6", ModelPartBuilder.create().uv(61, 19).cuboid(-3.3925F, -15.9804F, -0.6154F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.3589F, 9.0856F, -13.9231F));
        swirl6.addChild("cube_r67", ModelPartBuilder.create().uv(73, 49).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-6.3207F, -1.0522F, 3.3846F, 0.0F, 0.0F, -1.5708F));
        swirl6.addChild("cube_r68", ModelPartBuilder.create().uv(1, 71).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.3925F, -4.5163F, 3.3846F, 0.0F, 0.0F, -1.0472F));
        swirl6.addChild("cube_r69", ModelPartBuilder.create().uv(45, 61).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.8566F, -7.0522F, 3.3846F, 0.0F, 0.0F, -0.5236F));
        swirl6.addChild("cube_r70", ModelPartBuilder.create().uv(61, 33).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.6075F, -4.5163F, 3.3846F, 0.0F, 0.0F, 1.0472F));
        swirl6.addChild("cube_r71", ModelPartBuilder.create().uv(61, 26).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0716F, -7.0522F, 3.3846F, 0.0F, 0.0F, 0.5236F));
        swirl6.addChild("cube_r72", ModelPartBuilder.create().uv(41, 68).cuboid(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.3925F, 2.4119F, 3.3846F, 0.0F, 0.0F, 1.0472F));
        swirl6.addChild("cube_r73", ModelPartBuilder.create().uv(60, 39).cuboid(-4.0F, 6.0F, -2.0F, 8.0F, 2.0F, 0.0F, new Dilation(0.0F)).uv(21, 68).cuboid(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.8566F, 4.9478F, 3.3846F, 0.0F, 0.0F, 0.5236F));
        swirl6.addChild("cube_r74", ModelPartBuilder.create().uv(65, 67).cuboid(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.6075F, 5.876F, 3.3846F, 0.0F, 0.0F, 0.0F));
        swirl6.addChild("cube_r75", ModelPartBuilder.create().uv(65, 61).cuboid(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0716F, 4.9478F, 3.3846F, 0.0F, 0.0F, -0.5236F));
        swirl6.addChild("cube_r76", ModelPartBuilder.create().uv(1, 65).cuboid(-4.0F, 4.0F, -4.0F, 8.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.6075F, 2.4119F, 3.3846F, 0.0F, 0.0F, -1.0472F));
        swirl6.addChild("cube_r77", ModelPartBuilder.create().uv(25, 61).cuboid(-4.0F, 3.0F, -4.0F, 8.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.5357F, -1.0522F, 3.3846F, 0.0F, 0.0F, -1.5708F));
        ModelPartData swirl8 = modelPartData.addChild("swirl8", ModelPartBuilder.create().uv(2, 2).cuboid(-6.0F, -22.5478F, -0.5F, 12.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.7536F, -12.5F));
        swirl8.addChild("cube_r78", ModelPartBuilder.create().uv(2, 26).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.4641F, -13.6196F, -1.5F, 0.0F, 0.0F, -0.5236F));
        swirl8.addChild("cube_r79", ModelPartBuilder.create().uv(2, 18).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-11.4641F, -9.0837F, -1.5F, 0.0F, 0.0F, -1.0472F));
        swirl8.addChild("cube_r80", ModelPartBuilder.create().uv(2, 41).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-14.3923F, -2.1555F, -1.5F, 0.0F, 0.0F, -1.5708F));
        swirl8.addChild("cube_r81", ModelPartBuilder.create().uv(32, 37).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-13.4641F, 5.3086F, -1.5F, 0.0F, 0.0F, -2.0944F));
        swirl8.addChild("cube_r82", ModelPartBuilder.create().uv(2, 34).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.9282F, 11.3086F, -1.5F, 0.0F, 0.0F, -2.618F));
        swirl8.addChild("cube_r83", ModelPartBuilder.create().uv(32, 30).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 14.2368F, -1.5F, 0.0F, 0.0F, -3.1416F));
        swirl8.addChild("cube_r84", ModelPartBuilder.create().uv(32, 23).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.4641F, 13.3086F, -1.5F, 0.0F, 0.0F, 2.618F));
        swirl8.addChild("cube_r85", ModelPartBuilder.create().uv(32, 16).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(11.4641F, 8.7727F, -1.5F, 0.0F, 0.0F, 2.0944F));
        swirl8.addChild("cube_r86", ModelPartBuilder.create().uv(32, 9).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(14.3923F, 1.8445F, -1.5F, 0.0F, 0.0F, 1.5708F));
        swirl8.addChild("cube_r87", ModelPartBuilder.create().uv(32, 2).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(13.4641F, -5.6196F, -1.5F, 0.0F, 0.0F, 1.0472F));
        swirl8.addChild("cube_r88", ModelPartBuilder.create().uv(2, 10).cuboid(-8.0F, -8.0F, 1.0F, 12.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(8.9282F, -11.6196F, -1.5F, 0.0F, 0.0F, 0.5236F));
        ModelPartData swirl9 = modelPartData.addChild("swirl9", ModelPartBuilder.create().uv(31, 43).cuboid(-5.0569F, -18.6033F, -0.5F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0569F, 8.2732F, -13.5F));
        swirl9.addChild("cube_r89", ModelPartBuilder.create().uv(61, 7).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-17.7172F, -6.6751F, 1.5F, 0.0F, 0.0F, 2.0944F));
        swirl9.addChild("cube_r90", ModelPartBuilder.create().uv(61, 1).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-11.9851F, -14.6033F, 1.5F, 0.0F, 0.0F, 2.618F));
        swirl9.addChild("cube_r91", ModelPartBuilder.create().uv(1, 59).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-18.7172F, 3.0569F, 1.5F, 0.0F, 0.0F, 1.5708F));
        swirl9.addChild("cube_r92", ModelPartBuilder.create().uv(49, 55).cuboid(-8.0F, -5.0F, -2.0F, 10.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-14.7172F, 11.9851F, 1.5F, 0.0F, 0.0F, 1.0472F));
        swirl9.addChild("cube_r93", ModelPartBuilder.create().uv(55, 43).cuboid(-8.0F, -5.0F, -2.0F, 10.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-6.789F, 17.7172F, 1.5F, 0.0F, 0.0F, 0.5236F));
        swirl9.addChild("cube_r94", ModelPartBuilder.create().uv(25, 55).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0569F, 14.7172F, 1.5F, 0.0F, 0.0F, -3.1416F));
        swirl9.addChild("cube_r95", ModelPartBuilder.create().uv(1, 53).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.6751F, 14.2531F, 1.5F, 0.0F, 0.0F, 2.618F));
        swirl9.addChild("cube_r96", ModelPartBuilder.create().uv(49, 49).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(11.1392F, 9.9851F, 1.5F, 0.0F, 0.0F, 2.0944F));
        swirl9.addChild("cube_r97", ModelPartBuilder.create().uv(25, 49).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(14.6033F, 3.0569F, 1.5F, 0.0F, 0.0F, 1.5708F));
        swirl9.addChild("cube_r98", ModelPartBuilder.create().uv(1, 47).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(14.1392F, -4.6751F, 1.5F, 0.0F, 0.0F, 1.0472F));
        swirl9.addChild("cube_r99", ModelPartBuilder.create().uv(61, 13).cuboid(-8.0F, -4.0F, -2.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(9.8713F, -11.1392F, 1.5F, 0.0F, 0.0F, 0.5236F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.swirl1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl4.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl5.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl7.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl6.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl8.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        this.swirl9.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}