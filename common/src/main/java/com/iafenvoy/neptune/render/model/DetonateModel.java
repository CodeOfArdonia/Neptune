package com.iafenvoy.neptune.render.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class DetonateModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart detonate;

    public DetonateModel(ModelPart root) {
        this.detonate = root.getChild("detonate");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("detonate", ModelPartBuilder.create().uv(0, 0).cuboid(-4F, -0.5F, -0.5F, 8F, 1F, 1F, new Dilation(0.0F)), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.detonate.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
