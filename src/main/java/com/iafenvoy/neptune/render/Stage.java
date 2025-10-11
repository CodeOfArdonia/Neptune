package com.iafenvoy.neptune.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

import java.util.HashMap;

public enum Stage {
    Zero(0),
    First(1),
    Second(2),
    Third(3),
    Forth(4),
    Fifth(5),
    Sixth(6);
    private final int index;

    Stage(int index) {
        this.index = index;
    }

    public static Stage getByIndex(int index) {
        for (Stage s : Stage.values())
            if (s.index == index)
                return s;
        return Stage.Zero;
    }

    public static StagedEntityTextureProvider ofProvider(String modId, String... textures) {
        return new StagedEntityTextureProvider(modId, textures);
    }

    public int getIndex() {
        return this.index;
    }

    public interface StagedEntity {
        Stage getStage();
    }

    public static class StagedEntityTextureProvider {
        private final String modId;
        private final HashMap<Stage, String> textureIds = new HashMap<>();
        private String eyeTextureId = null;

        public StagedEntityTextureProvider(String modId, String... textures) {
            this.modId = modId;
            Stage[] stages = Stage.values();
            for (int i = 0; i < stages.length - 1 && i < textures.length; i++)
                this.textureIds.put(stages[i + 1], textures[i]);
        }

        public StagedEntityTextureProvider setEyeTextureId(String eyeTextureId) {
            this.eyeTextureId = eyeTextureId;
            return this;
        }

        public ResourceLocation getTexture() {
            return this.getTexture(Stage.First);
        }

        public ResourceLocation getTexture(Stage stage) {
            return buildTexture(this.modId, this.getTextureId(stage));
        }

        private String getTextureId(Stage stage) {
            if (this.textureIds.containsKey(stage))
                return this.textureIds.get(stage);
            if (this.textureIds.isEmpty())
                return "";
            return this.textureIds.get(Stage.First);
        }

        public <T extends Mob> EntityRendererBase<T> createRenderer(EntityRendererProvider.Context context) {
            if (this.eyeTextureId == null)
                return new EntityRendererBase<>(context, this, null);
            return new EntityRendererBase<>(context, this, ResourceLocation.tryBuild(this.modId, this.eyeTextureId));
        }

        private static ResourceLocation buildTexture(String modId, String id) {
            return ResourceLocation.tryBuild(modId, "textures/entities/" + id + ".png");
        }
    }
}
