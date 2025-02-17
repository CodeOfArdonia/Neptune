package com.iafenvoy.neptune.render.model;

import com.iafenvoy.neptune.Neptune;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ShardModel<T extends Entity> extends EntityModel<T> {
    public static final Identifier SHARD_TEXTURE_WHITE = Identifier.of(Neptune.MOD_ID, "textures/entity/shard.png");
    private final ModelPart root;

    public ShardModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));
        root.addChild("cube_r1", ModelPartBuilder.create().uv(0, 2).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, -0.1243F, -0.51F, 0.2506F));
        root.addChild("cube_r2", ModelPartBuilder.create().uv(2, -2).cuboid(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, -0.1243F, -0.51F, 0.2506F));
        root.addChild("cube_r3", ModelPartBuilder.create().uv(0, 10).cuboid(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.1243F, 0.51F, 0.2506F));
        root.addChild("cube_r4", ModelPartBuilder.create().uv(2, 6).cuboid(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.1243F, 0.51F, 0.2506F));
        root.addChild("cube_r5", ModelPartBuilder.create().uv(2, 10).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, -0.1243F, 0.51F, -0.2506F));
        root.addChild("cube_r6", ModelPartBuilder.create().uv(0, 6).cuboid(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, -0.1243F, 0.51F, -0.2506F));
        root.addChild("cube_r7", ModelPartBuilder.create().uv(2, 2).cuboid(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.1243F, -0.51F, -0.2506F));
        root.addChild("cube_r8", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.1243F, -0.51F, -0.2506F));
        return TexturedModelData.of(modelData, 16, 16);
    }


    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
