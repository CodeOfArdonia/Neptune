package com.iafenvoy.neptune.trail;

import com.iafenvoy.neptune.trail.provider.TrailProvider;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class TrailRegistry {
    private static final Map<ResourceLocation, Set<Function<Entity, TrailProvider>>> CONSTRUCTORS = new HashMap<>();
    private static final Map<ResourceLocation, Predicate<Entity>> REMOVE_PREDICATE = new HashMap<>();

    @SafeVarargs
    public static void register(ResourceLocation id, Function<Entity, TrailProvider>... constructor) {
        if (!CONSTRUCTORS.containsKey(id)) CONSTRUCTORS.put(id, new HashSet<>());
        CONSTRUCTORS.get(id).addAll(List.of(constructor));
    }

    public static void registerPredicate(ResourceLocation id, Predicate<Entity> predicate) {
        REMOVE_PREDICATE.put(id, predicate);
    }

    public static List<TrailProvider> get(ResourceLocation id, Entity entity) {
        return CONSTRUCTORS.getOrDefault(id, Set.of()).stream().map(x -> x.apply(entity)).toList();
    }

    public static boolean shouldRemove(Entity entity, ResourceLocation id) {
        return REMOVE_PREDICATE.getOrDefault(id, e -> false).test(entity);
    }
}
