package com.iafenvoy.neptune.accessory;

import com.iafenvoy.neptune.util.function.consumer.Consumer2;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class AccessoryManager {
    private static final Map<Item, BackHolder> BACK_HOLDERS = new HashMap<>();
    private static final Map<Item, BeltHolder> BELT_HOLDERS = new HashMap<>();

    @ExpectPlatform
    public static Map<Place, ItemStack> getAllEquipped(LivingEntity living) {
        throw new AssertionError("This method should be replaced by Architectury");
    }

    @SafeVarargs
    @ExpectPlatform
    public static <T extends Item & Accessory> void register(T... accessories) {
        throw new AssertionError("This method should be replaced by Architectury");
    }

    @Nullable
    public static BackHolder getBack(Item item) {
        return BACK_HOLDERS.get(item);
    }

    @Nullable
    public static BeltHolder getBelt(Item item) {
        return BELT_HOLDERS.get(item);
    }

    public static ItemStack getEquipped(LivingEntity living, Place place) {
        return getAllEquipped(living).getOrDefault(place, ItemStack.EMPTY);
    }

    public static void registerBack(Item item, boolean alone, Consumer2<MatrixStack, Boolean> transformer) {
        BACK_HOLDERS.put(item, new BackHolder(item, alone, transformer));
    }

    public static void registerBack(Item item, Consumer2<MatrixStack, Boolean> transformer) {
        registerBack(item, false, transformer);
    }

    public static void registerBack(boolean alone, Consumer2<MatrixStack, Boolean> transformer, Item... items) {
        for (Item item : items) registerBack(item, alone, transformer);
    }

    public static void registerBack(Consumer2<MatrixStack, Boolean> transformer, Item... items) {
        for (Item item : items) registerBack(item, transformer);
    }

    public static void registerBelt(Item item, Consumer2<MatrixStack, Boolean> transformer) {
        BELT_HOLDERS.put(item, new BeltHolder(item, transformer));
    }

    public static void registerBelt(Consumer2<MatrixStack, Boolean> transformer, Item... items) {
        for (Item item : items) registerBelt(item, transformer);
    }

    public record BackHolder(Item item, boolean alone, Consumer2<MatrixStack, Boolean> transformer) {

    }

    public record BeltHolder(Item item, Consumer2<MatrixStack, Boolean> transformer) {

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
