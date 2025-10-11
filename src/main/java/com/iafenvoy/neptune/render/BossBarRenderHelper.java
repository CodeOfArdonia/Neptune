package com.iafenvoy.neptune.render;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

public class BossBarRenderHelper {
    @ApiStatus.Internal
    public static final ResourceLocation BARS_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/bars.png");
    @ApiStatus.Internal
    public static final Map<Class<? extends Entity>, BossBarInfo> infos = new HashMap<>();

    public static void addBossBar(Class<? extends Entity> entityClass, UUID uuid) {
        if (!infos.containsKey(entityClass)) return;
        infos.get(entityClass).bossBarId().add(uuid);
    }

    public static void removeBossBar(Class<? extends Entity> entityClass, UUID uuid) {
        if (!infos.containsKey(entityClass)) return;
        infos.get(entityClass).bossBarId().remove(uuid);
    }

    public static void addBossBarType(Class<? extends Entity> entityClass, ResourceLocation texture, boolean disableName) {
        infos.put(entityClass, new BossBarInfo(texture, new ArrayList<>(), disableName));
    }

    @ApiStatus.Internal
    public record BossBarInfo(ResourceLocation texture, List<UUID> bossBarId, boolean disableName) {
    }
}
