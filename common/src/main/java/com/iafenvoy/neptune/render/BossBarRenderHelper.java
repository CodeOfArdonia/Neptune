package com.iafenvoy.neptune.render;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

public class BossBarRenderHelper {
    @ApiStatus.Internal
    public static final Identifier BARS_TEXTURE = new Identifier("textures/gui/bars.png");
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

    public static void addBossBarType(Class<? extends Entity> entityClass, Identifier texture, boolean disableName) {
        infos.put(entityClass, new BossBarInfo(texture, new ArrayList<>(), disableName));
    }

    @ApiStatus.Internal
    public record BossBarInfo(Identifier texture, List<UUID> bossBarId, boolean disableName) {
    }
}
