package com.iafenvoy.neptune.render.glint;

import com.iafenvoy.neptune.Neptune;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class GlintManager {
    public static final String GLINT_KEY = "glint";
    public static final String GLINT_ALWAYS_KEY = "glint_always";
    public static final List<GlintHolder> HOLDERS = new ArrayList<>();
    public static final Map<String, GlintHolder> BY_ID = new HashMap<>();

    public static final GlintHolder DEFAULT = new GlintHolder("default", null, Formatting.WHITE);
    public static final GlintHolder RED = new GlintHolder("red", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_red.png"), Formatting.RED);
    public static final GlintHolder YELLOW = new GlintHolder("yellow", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_yellow.png"), Formatting.YELLOW);
    public static final GlintHolder BLUE = new GlintHolder("blue", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_blue.png"), Formatting.BLUE);
    public static final GlintHolder ORANGE = new GlintHolder("orange", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_orange.png"), Formatting.GOLD);
    public static final GlintHolder GREEN = new GlintHolder("green", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_green.png"), Formatting.GREEN);
    public static final GlintHolder PURPLE = new GlintHolder("purple", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_purple.png"), Formatting.DARK_PURPLE);
    public static final GlintHolder WHITE = new GlintHolder("white", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_white.png"), Formatting.WHITE);
    public static final GlintHolder PINK = new GlintHolder("pink", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_pink.png"), Formatting.LIGHT_PURPLE);
    public static final GlintHolder AQUA = new GlintHolder("aqua", new Identifier(Neptune.MOD_ID, "textures/misc/glint_item_aqua.png"), Formatting.AQUA);

    public static void removeGlint(ItemStack stack) {
        stack.getOrCreateNbt().remove(GLINT_KEY);
        stack.getOrCreateNbt().remove(GLINT_ALWAYS_KEY);
    }

    public record GlintHolder(String id, Identifier texture, Formatting textColor, PredicateHolder predicates) {
        public GlintHolder(String id, Identifier texture, Formatting textColor) {
            this(id, texture, textColor, new PredicateHolder());
            HOLDERS.add(this);
            BY_ID.put(this.id, this);
        }

        public void apply(ItemStack stack, boolean always) {
            stack.getOrCreateNbt().putString(GLINT_KEY, this.id);
            stack.getOrCreateNbt().putBoolean(GLINT_ALWAYS_KEY, always);
        }

        public void addPredicate(Predicate<ItemStack> stackPredicate) {
            this.predicates.add(stackPredicate);
        }

        public boolean match(ItemStack stack) {
            return this.predicates.test(stack);
        }
    }

    private static class PredicateHolder {
        private final List<Predicate<ItemStack>> predicates = new ArrayList<>();

        public void add(Predicate<ItemStack> stackPredicate) {
            this.predicates.add(stackPredicate);
        }

        public boolean test(ItemStack stack) {
            for (Predicate<ItemStack> stackPredicate : this.predicates)
                if (stackPredicate.test(stack))
                    return true;
            return false;
        }
    }
}