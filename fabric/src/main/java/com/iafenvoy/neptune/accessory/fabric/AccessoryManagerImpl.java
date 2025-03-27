package com.iafenvoy.neptune.accessory.fabric;

import com.iafenvoy.neptune.accessory.AccessoryManager;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.SlotType;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Environment(EnvType.CLIENT)
public class AccessoryManagerImpl extends AccessoryManager {
    public static Map<AccessoryManager.Place, ItemStack> getAllEquipped(LivingEntity living) {
        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(living);
        Map<AccessoryManager.Place, ItemStack> map = new HashMap<>();
        if (optional.isEmpty()) return map;
        TrinketComponent component = optional.get();
        List<Pair<SlotReference, ItemStack>> all = component.getAllEquipped();
        findAndApply(all, AccessoryManagerImpl::isBack, stack -> map.put(Place.BACK_RIGHT, stack), stack -> map.put(Place.BACK_LEFT, stack));
        findAndApply(all, AccessoryManagerImpl::isBelt, stack -> map.put(Place.BELT_RIGHT, stack), stack -> map.put(Place.BELT_LEFT, stack));
        findAndApply(all, AccessoryManagerImpl::isNecklace, stack -> map.put(Place.NECKLACE, stack));
        findAndApply(all, AccessoryManagerImpl::isShoes, stack -> map.put(Place.FEET, stack));
        findAndApply(all, AccessoryManagerImpl::isHat, stack -> map.put(Place.HAT, stack));
        return map;
    }

    @SafeVarargs
    private static void findAndApply(List<Pair<SlotReference, ItemStack>> all, Predicate<SlotReference> predicate, Consumer<ItemStack>... consumers) {
        List<Pair<SlotReference, ItemStack>> stacks = all.stream().filter(x -> predicate.test(x.getLeft())).toList();
        for (int i = 0; i < stacks.size() && i < consumers.length; i++)
            consumers[i].accept(stacks.get(i).getRight());
    }

    public static boolean isBack(SlotReference reference) {
        SlotType type = reference.inventory().getSlotType();
        return type.getGroup().equals("chest") && type.getName().equals("back");
    }

    public static boolean isBelt(SlotReference reference) {
        SlotType type = reference.inventory().getSlotType();
        return type.getGroup().equals("legs") && type.getName().equals("belt");
    }

    public static boolean isNecklace(SlotReference reference) {
        SlotType type = reference.inventory().getSlotType();
        return type.getGroup().equals("chest") && type.getName().equals("necklace");
    }

    public static boolean isShoes(SlotReference reference) {
        SlotType type = reference.inventory().getSlotType();
        return type.getGroup().equals("feet") && type.getName().equals("shoes");
    }

    public static boolean isHat(SlotReference reference) {
        SlotType type = reference.inventory().getSlotType();
        return type.getGroup().equals("head") && type.getName().equals("hat");
    }
}
