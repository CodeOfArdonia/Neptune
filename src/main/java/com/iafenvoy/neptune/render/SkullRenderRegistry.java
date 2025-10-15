package com.iafenvoy.neptune.render;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber
public class SkullRenderRegistry {
    private static final List<SkullInfoWithModel> SKULL_INFO_WITH_MODEL = new ArrayList<>();
    private static final List<SkullInfoWithLayer> SKULL_INFO_WITH_LAYER = new ArrayList<>();

    public static void register(SkullBlock.Type type, ResourceLocation texture, Block... blocks) {
        register(type, texture, ModelLayers.ZOMBIE_HEAD, blocks);
    }

    public static void register(SkullBlock.Type type, ResourceLocation texture, ModelLayerLocation layer, Block... blocks) {
        register(type, texture, PlayerSkin.Model.WIDE, layer, blocks);
    }

    public static void register(SkullBlock.Type type, ResourceLocation texture, PlayerSkin.Model skinModel, ModelLayerLocation layer, Block... blocks) {
        SKULL_INFO_WITH_LAYER.add(new SkullInfoWithLayer(type, texture, skinModel, layer, List.of(blocks)));
    }

    public static void register(SkullBlock.Type type, ResourceLocation texture, SkullModelBase model, Block... blocks) {
        register(type, texture, PlayerSkin.Model.WIDE, model, blocks);
    }

    public static void register(SkullBlock.Type type, ResourceLocation texture, PlayerSkin.Model skinModel, SkullModelBase model, Block... blocks) {
        SKULL_INFO_WITH_MODEL.add(new SkullInfoWithModel(type, texture, skinModel, model, List.of(blocks)));
    }

    @Nullable
    public static SkinInfo getTextureFromType(SkullBlock.Type type) {
        return SKULL_INFO_WITH_MODEL.stream().filter(x -> x.type == type).findFirst().map(x -> new SkinInfo(x.texture, x.skinModel)).orElse(SKULL_INFO_WITH_LAYER.stream().filter(x -> x.type == type).findFirst().map(x -> new SkinInfo(x.texture, x.skinModel)).orElse(null));
    }

    @SubscribeEvent
    public static void getSkulls(EntityRenderersEvent.CreateSkullModels event) {
        for (SkullInfoWithModel info : SKULL_INFO_WITH_MODEL) {
            SkullBlockRenderer.SKIN_BY_TYPE.put(info.type, info.texture);
            event.registerSkullModel(info.type, info.model);
        }
        for (SkullInfoWithLayer info : SKULL_INFO_WITH_LAYER) {
            SkullBlockRenderer.SKIN_BY_TYPE.put(info.type, info.texture);
            event.registerSkullModel(info.type, new SkullModel(event.getEntityModelSet().bakeLayer(info.layer)));
        }
    }

    @ApiStatus.Internal
    public static boolean supported(Block block) {
        for (SkullInfoWithModel info : SKULL_INFO_WITH_MODEL)
            if (info.blocks.contains(block)) return true;
        for (SkullInfoWithLayer info : SKULL_INFO_WITH_LAYER)
            if (info.blocks.contains(block)) return true;
        return false;
    }

    private record SkullInfoWithModel(SkullBlock.Type type, ResourceLocation texture, PlayerSkin.Model skinModel,
                                      SkullModelBase model, List<Block> blocks) {
    }

    private record SkullInfoWithLayer(SkullBlock.Type type, ResourceLocation texture, PlayerSkin.Model skinModel,
                                      ModelLayerLocation layer, List<Block> blocks) {
    }

    public record SkinInfo(ResourceLocation texture, PlayerSkin.Model model) {
        public SkinInfo(PlayerSkin skin) {
            this(skin.texture(), skin.model());
        }

        public PlayerSkin copyOrCreate(PlayerSkin origin) {
            if (origin == null) return new PlayerSkin(this.texture(), null, null, null, this.model, true);
            return new PlayerSkin(this.texture(), origin.textureUrl(), origin.capeTexture(), origin.elytraTexture(), this.model, origin.secure());
        }
    }

    public interface SkullTextureProvider {
        Optional<SkinInfo> getTexture(ItemStack stack);
    }
}
