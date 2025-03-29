package com.iafenvoy.neptune.trail;

import com.iafenvoy.neptune.trail.provider.TrailProvider;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class TrailRegistry {
    private static final Map<Identifier, Set<Function<Entity, TrailProvider>>> CONSTRUCTORS = new HashMap<>();
    private static final Map<Identifier, Predicate<Entity>> REMOVE_PREDICATE = new HashMap<>();

    @SafeVarargs
    public static void register(Identifier id, Function<Entity, TrailProvider>... constructor) {
        if (!CONSTRUCTORS.containsKey(id)) CONSTRUCTORS.put(id, new HashSet<>());
        CONSTRUCTORS.get(id).addAll(List.of(constructor));
    }

    public static void registerPredicate(Identifier id, Predicate<Entity> predicate) {
        REMOVE_PREDICATE.put(id, predicate);
    }

    public static List<TrailProvider> get(Identifier id, Entity entity) {
        return CONSTRUCTORS.getOrDefault(id, Set.of()).stream().map(x -> x.apply(entity)).toList();
    }

    public static boolean shouldRemove(Entity entity, Identifier id) {
        return REMOVE_PREDICATE.getOrDefault(id, e -> false).test(entity);
    }
}
