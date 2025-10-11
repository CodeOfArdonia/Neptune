package com.iafenvoy.neptune.trail.render;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.trail.provider.TrailProvider;
import com.iafenvoy.neptune.util.Color4i;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public class TrailEffect {
    private static final ResourceLocation TRAIL_TEXTURE = ResourceLocation.tryBuild(Neptune.MOD_ID, "textures/entity/concentrated_trail.png");
    private static final List<TrailEffect> TRAILS = new ArrayList<>();
    private final Entity entity;
    private final TrailProvider provider;
    private final TrailHolder effect;
    private final ResourceLocation id;
    private boolean shouldRemove, shouldRender = true;

    public TrailEffect(Entity entity, TrailProvider provider, ResourceLocation id) {
        this.entity = entity;
        this.provider = provider;
        this.effect = provider.createTail();
        this.id = id;
    }

    public static void create(Entity entity, TrailProvider provider, ResourceLocation id) {
        create(new TrailEffect(entity, provider, id));
    }

    public static void create(TrailEffect effect) {
        TRAILS.add(effect);
    }

    public static void remove(Entity entity, ResourceLocation id) {
        TRAILS.removeIf(effect -> effect.entity.getUUID().equals(entity.getUUID()) && effect.id.equals(id));
    }

    @SubscribeEvent
    public static void tickAll(ClientTickEvent.Post event) {
        Minecraft client = Minecraft.getInstance();
        if (client.level != null)
            for (TrailEffect effect : TRAILS)
                if (!client.isPaused()) effect.tick(client);
    }

    @SubscribeEvent
    public static void renderAll(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_ENTITIES) return;
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vec3 cameraPos = camera.getPosition();
        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();
        poseStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        for (TrailEffect effect : TRAILS)
            if (!effect.shouldRemove())
                effect.render(Minecraft.getInstance().renderBuffers().bufferSource(), poseStack, event.getRenderTick());
        poseStack.popPose();
    }

    public void tick(Minecraft client) {
        if (client.level == null || this.effect.getLength() <= 0)
            this.shouldRemove = true;
        else if (this.provider.getCurrentPos().distanceTo(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition()) > this.provider.maxDistance())
            this.shouldRender = false;
        else {
            this.shouldRender = true;
            this.provider.updateTrail(this.effect);
        }
    }

    public void render(MultiBufferSource provider, PoseStack matrices, float tickDelta) {
        if (!this.shouldRender) return;
        Vec3 pos = this.provider.getCurrentPos();
        this.effect.prepareRender(pos.add(0, this.entity.getBbHeight() / 2, 0), pos.subtract(new Vec3(this.entity.xo, this.entity.yo, this.entity.zo)), tickDelta);

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

    private void renderTrail(MultiBufferSource provider, PoseStack matrices, boolean vertical, Color4i color, int light) {
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
                Matrix4f pose = matrices.last().pose();
                PoseStack.Pose normal = matrices.last();
                consumer.addVertex(pose, (float) from.upper().x, (float) from.upper().y, (float) from.upper().z).setColor(r, g, b, i == 0 ? 0 : a).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(normal, 0, 1, 0);
                consumer.addVertex(pose, (float) to.upper().x, (float) to.upper().y, (float) to.upper().z).setColor(r, g, b, a).setUv(1, 0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(normal, 0, 1, 0);
                consumer.addVertex(pose, (float) to.lower().x, (float) to.lower().y, (float) to.lower().z).setColor(r, g, b, a).setUv(1, 1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(normal, 0, 1, 0);
                consumer.addVertex(pose, (float) from.lower().x, (float) from.lower().y, (float) from.lower().z).setColor(r, g, b, i == 0 ? 0 : a).setUv(0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(normal, 0, 1, 0);
            }
        }
    }

    public boolean shouldRemove() {
        return this.shouldRemove;
    }

    public static final class Layer extends RenderType {
        public static final Function<ResourceLocation, RenderType> TRANSLUCENT_NO_DEPTH = Util.memoize(location ->
                create(Neptune.MOD_ID + ":entity_translucent_no_depth", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, MEGABYTE, true, true, CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER)
                        .setTextureState(new TextureStateShard(location, false, false))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .setWriteMaskState(COLOR_WRITE)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(true)));

        public Layer(String name, VertexFormat vertexFormat, VertexFormat.Mode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
            super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
        }
    }
}
