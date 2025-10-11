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

@OnlyIn(Dist.CLIENT)
public class DetonateModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart detonate;

    public DetonateModel(ModelPart root) {
        this.detonate = root.getChild("detonate");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("detonate", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -0.5F, -0.5F, 8F, 1F, 1F, new CubeDeformation(0.0F)), PartPose.ZERO);
        return LayerDefinition.create(modelData, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.detonate.render(matrices, vertices, light, overlay, color);
    }
}
