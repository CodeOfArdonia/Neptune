package com.iafenvoy.neptune.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CuriosHelper {
    private static final Map<Item, BackHolder> BACK_HOLDERS = new HashMap<>();
    private static final Map<Item, BeltHolder> BELT_HOLDERS = new HashMap<>();

    //FIXME::Laggy code
    public static Map<Place, ItemStack> getEquipped(LivingEntity living) {
        Optional<ICuriosItemHandler> lazyOptional = CuriosApi.getCuriosInventory(living);
        Map<Place, ItemStack> map = new HashMap<>();
        if (lazyOptional.isEmpty()) return map;
        ICuriosItemHandler handler = lazyOptional.get();
        Map<String, ICurioStacksHandler> all = handler.getCurios();
        findAndApply(all.get("back").getStacks(), stack -> map.put(Place.BACK_RIGHT, stack), stack -> map.put(Place.BACK_LEFT, stack));
        findAndApply(all.get("belt").getStacks(), stack -> map.put(Place.BELT_RIGHT, stack), stack -> map.put(Place.BELT_LEFT, stack));
        findAndApply(all.get("necklace").getStacks(), stack -> map.put(Place.NECKLACE, stack));
        findAndApply(all.get("feet").getStacks(), stack -> map.put(Place.FEET, stack));
        findAndApply(all.get("head").getStacks(), stack -> map.put(Place.HAT, stack));
        return map;
    }

    @SafeVarargs
    private static void findAndApply(IDynamicStackHandler handler, Consumer<ItemStack>... consumers) {
        for (int i = 0; i < handler.getSlots() && i < consumers.length; i++)
            consumers[i].accept(handler.getStackInSlot(i));
    }

    @Nullable
    public static BackHolder getBack(Item item) {
        return BACK_HOLDERS.get(item);
    }

    @Nullable
    public static BeltHolder getBelt(Item item) {
        return BELT_HOLDERS.get(item);
    }

    public static void registerBack(Item item, boolean alone, BiConsumer<PoseStack, Boolean> transformer) {
        BACK_HOLDERS.put(item, new BackHolder(item, alone, transformer));
    }

    public static void registerBack(Item item, BiConsumer<PoseStack, Boolean> transformer) {
        registerBack(item, false, transformer);
    }

    public static void registerBack(boolean alone, BiConsumer<PoseStack, Boolean> transformer, Item... items) {
        for (Item item : items) registerBack(item, alone, transformer);
    }

    public static void registerBack(BiConsumer<PoseStack, Boolean> transformer, Item... items) {
        for (Item item : items) registerBack(item, transformer);
    }

    public static void registerBelt(Item item, BiConsumer<PoseStack, Boolean> transformer) {
        BELT_HOLDERS.put(item, new BeltHolder(item, transformer));
    }

    public static void registerBelt(BiConsumer<PoseStack, Boolean> transformer, Item... items) {
        for (Item item : items) registerBelt(item, transformer);
    }

    public record BackHolder(Item item, boolean alone, BiConsumer<PoseStack, Boolean> transformer) {
    }

    public record BeltHolder(Item item, BiConsumer<PoseStack, Boolean> transformer) {
    }


    public enum Place {
        BACK_LEFT(EquipmentSlot.CHEST),
        BACK_RIGHT(EquipmentSlot.CHEST),
        BELT_LEFT(EquipmentSlot.CHEST),
        BELT_RIGHT(EquipmentSlot.CHEST),
        HAT(EquipmentSlot.HEAD),
        NECKLACE(EquipmentSlot.HEAD),
        FEET(EquipmentSlot.FEET);

        private final EquipmentSlot slot;

        Place(EquipmentSlot slot) {
            this.slot = slot;
        }

        public EquipmentSlot getSlot() {
            return this.slot;
        }
    }
}
