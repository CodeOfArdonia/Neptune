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
public class SphereModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart root;

    public SphereModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        root.addOrReplaceChild("bottom_r1", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 1.5708F, 0.0F, 0.0F));
        PartDefinition single1 = root.addOrReplaceChild("single1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition bone2 = single1.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone3 = single1.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single2 = root.addOrReplaceChild("single2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
        PartDefinition bone4 = single2.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone4.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone4.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone5 = single2.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone5.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone5.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone5.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone5.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single3 = root.addOrReplaceChild("single3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        PartDefinition bone6 = single3.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone6.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone6.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone6.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone7 = single3.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone7.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone7.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone7.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone7.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single4 = root.addOrReplaceChild("single4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));
        PartDefinition bone8 = single4.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone8.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone8.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone8.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone9 = single4.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone9.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone9.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone9.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone9.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single5 = root.addOrReplaceChild("single5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        PartDefinition bone10 = single5.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone10.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone10.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone10.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone11 = single5.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone11.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone11.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone11.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone11.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single6 = root.addOrReplaceChild("single6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.9635F, 0.0F));
        PartDefinition bone12 = single6.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone12.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone12.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone12.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone13 = single6.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone13.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone13.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone13.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone13.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single7 = root.addOrReplaceChild("single7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));
        PartDefinition bone14 = single7.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone14.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone14.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone14.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone15 = single7.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone15.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone15.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone15.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone15.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single8 = root.addOrReplaceChild("single8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7489F, 0.0F));
        PartDefinition bone16 = single8.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone16.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone16.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone16.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone17 = single8.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone17.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone17.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone17.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone17.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single9 = root.addOrReplaceChild("single9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition bone18 = single9.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone18.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone18.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone18.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone19 = single9.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone19.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone19.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone19.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone19.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single10 = root.addOrReplaceChild("single10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.7489F, 0.0F));
        PartDefinition bone20 = single10.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone20.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone20.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone20.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone21 = single10.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone21.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone21.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone21.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone21.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single11 = root.addOrReplaceChild("single11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.3562F, 0.0F));
        PartDefinition bone22 = single11.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone22.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone22.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone22.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone23 = single11.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone23.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone23.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone23.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone23.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single12 = root.addOrReplaceChild("single12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.9635F, 0.0F));
        PartDefinition bone24 = single12.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone24.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone24.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone24.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone25 = single12.addOrReplaceChild("bone25", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone25.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone25.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone25.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone25.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single13 = root.addOrReplaceChild("single13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        PartDefinition bone26 = single13.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone26.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone26.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone26.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone27 = single13.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone27.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone27.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone27.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone27.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single14 = root.addOrReplaceChild("single14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        PartDefinition bone28 = single14.addOrReplaceChild("bone28", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone28.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone28.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone28.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone29 = single14.addOrReplaceChild("bone29", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone29.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone29.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone29.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone29.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single15 = root.addOrReplaceChild("single15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone30 = single15.addOrReplaceChild("bone30", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone30.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone30.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone30.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone31 = single15.addOrReplaceChild("bone31", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone31.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone31.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone31.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone31.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition single16 = root.addOrReplaceChild("single16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        PartDefinition bone32 = single16.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        bone32.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone32.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone32.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition bone33 = single16.addOrReplaceChild("bone33", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));
        bone33.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        bone33.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        bone33.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.1781F, 0.0F));
        bone33.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        return LayerDefinition.create(modelData, 16, 16);
    }

    @Override
    public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrices, @NotNull VertexConsumer vertexConsumer, int light, int overlay, int color) {
        this.root.render(matrices, vertexConsumer, light, overlay, color);
    }
}
