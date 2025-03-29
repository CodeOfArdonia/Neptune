package com.iafenvoy.neptune.trail.render;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.trail.provider.TrailProvider;
import com.iafenvoy.neptune.util.Color4i;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class TrailEffect {
    private static final Identifier TRAIL_TEXTURE = Identifier.of(Neptune.MOD_ID, "textures/entity/concentrated_trail.png");
    private static final List<TrailEffect> TRAILS = new ArrayList<>();
    private final Entity entity;
    private final TrailProvider provider;
    private final TrailHolder effect;
    private final Identifier id;
    private boolean shouldRemove, shouldRender = true;

    public TrailEffect(Entity entity, TrailProvider provider, Identifier id) {
        this.entity = entity;
        this.provider = provider;
        this.effect = provider.createTail();
        this.id = id;
    }

    public static void create(Entity entity, TrailProvider provider, Identifier id) {
        create(new TrailEffect(entity, provider, id));
    }

    public static void create(TrailEffect effect) {
        TRAILS.add(effect);
    }

    public static void remove(Entity entity, Identifier id) {
        TRAILS.removeIf(effect -> effect.entity.getUuid().equals(entity.getUuid()) && effect.id.equals(id));
    }

    public static void tickAll(MinecraftClient client) {
        if (client.world != null)
            for (TrailEffect effect : TRAILS)
                if (!client.isPaused()) effect.tick(client);
    }

    public static void renderAll(VertexConsumerProvider provider, MatrixStack matrices, float tickDelta) {
        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();
        matrices.push();
        matrices.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        for (TrailEffect effect : TRAILS)
            if (!effect.shouldRemove())
                effect.render(provider, matrices, tickDelta);
        matrices.pop();
    }

    public void tick(MinecraftClient client) {
        if (client.world == null || this.effect.getLength() <= 0)
            this.shouldRemove = true;
        else if (this.provider.getCurrentPos().distanceTo(MinecraftClient.getInstance().gameRenderer.getCamera().getPos()) > this.provider.maxDistance())
            this.shouldRender = false;
        else {
            this.shouldRender = true;
            this.provider.updateTrail(this.effect);
        }
    }

    public void render(VertexConsumerProvider provider, MatrixStack matrices, float tickDelta) {
        if (!this.shouldRender) return;
        Vec3d pos = this.provider.getCurrentPos();
        this.effect.prepareRender(pos.add(0, this.entity.getHeight() / 2, 0), pos.subtract(new Vec3d(this.entity.prevX, this.entity.prevY, this.entity.prevZ)), tickDelta);

        List<TrailHolder.TrailPoint> adjustedVertical = this.effect.getVerticalRenderPoints().stream().map(p -> this.provider.adjustPoint(p, true, tickDelta)).toList();
        this.effect.getVerticalRenderPoints().clear();
        this.effect.getVerticalRenderPoints().addAll(adjustedVertical);
        List<TrailHolder.TrailPoint> adjustedHorizontal = this.effect.getHorizontalRenderPoints().stream().map(p -> this.provider.adjustPoint(p, false, tickDelta)).toList();
        this.effect.getHorizontalRenderPoints().clear();
        this.effect.getHorizontalRenderPoints().addAll(adjustedHorizontal);

        Color4i trailColor = this.provider.getTrailColor();
        int light = this.provider.getTrailLight(tickDelta);
        this.renderTrail(provider, matrices, true, trailColor, light);
        if (this.provider.shouldRenderHorizontal()) this.renderTrail(provider, matrices, false, trailColor, light);
    }

    private void renderTrail(VertexConsumerProvider provider, MatrixStack matrices, boolean vertical, Color4i color, int light) {
        VertexConsumer consumer = provider.getBuffer(Layer.TRANSLUCENT_NO_DEPTH.apply(TRAIL_TEXTURE));
        float r = color.getR();
        float g = color.getG();
        float b = color.getB();
        float a = color.getA();
        List<TrailHolder.TrailPoint> points = vertical ? this.effect.getVerticalRenderPoints() : this.effect.getHorizontalRenderPoints();
        if (points.size() >= 2) {
            for (int i = 0; i < points.size() - 1; i++) {
                TrailHolder.TrailPoint from = points.get(i);
                TrailHolder.TrailPoint to = points.get(i + 1);
                Matrix4f pose = matrices.peek().getPositionMatrix();
                Matrix3f normal = matrices.peek().getNormalMatrix();
                consumer.vertex(pose, (float) from.upper().x, (float) from.upper().y, (float) from.upper().z).color(r, g, b, i == 0 ? 0 : a).texture(0, 0).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normal, 0, 1, 0).next();
                consumer.vertex(pose, (float) to.upper().x, (float) to.upper().y, (float) to.upper().z).color(r, g, b, a).texture(1, 0).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normal, 0, 1, 0).next();
                consumer.vertex(pose, (float) to.lower().x, (float) to.lower().y, (float) to.lower().z).color(r, g, b, a).texture(1, 1).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normal, 0, 1, 0).next();
                consumer.vertex(pose, (float) from.lower().x, (float) from.lower().y, (float) from.lower().z).color(r, g, b, i == 0 ? 0 : a).texture(0, 1).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normal, 0, 1, 0).next();
            }
        }
    }

    public boolean shouldRemove() {
        return this.shouldRemove;
    }

    public static final class Layer extends RenderLayer {
        public static final Function<Identifier, RenderLayer> TRANSLUCENT_NO_DEPTH = Util.memoize(location ->
                of(Neptune.MOD_ID + ":entity_translucent_no_depth", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, TRANSLUCENT_BUFFER_SIZE, true, true, MultiPhaseParameters.builder()
                        .program(ENTITY_TRANSLUCENT_EMISSIVE_PROGRAM)
                        .texture(new Texture(location, false, false))
                        .transparency(TRANSLUCENT_TRANSPARENCY)
                        .cull(DISABLE_CULLING)
                        .writeMaskState(COLOR_MASK)
                        .overlay(ENABLE_OVERLAY_COLOR)
                        .build(true)));

        public Layer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
            super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
        }
    }
}
