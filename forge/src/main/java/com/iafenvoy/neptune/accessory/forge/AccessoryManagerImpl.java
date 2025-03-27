package com.iafenvoy.neptune.accessory.forge;

import com.iafenvoy.neptune.accessory.Accessory;
import com.iafenvoy.neptune.accessory.AccessoryManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class AccessoryManagerImpl extends AccessoryManager {
    public static Map<AccessoryManager.Place, ItemStack> getAllEquipped(LivingEntity living) {
        Optional<ICuriosItemHandler> lazyOptional = CuriosApi.getCuriosInventory(living).resolve();
        Map<AccessoryManager.Place, ItemStack> map = new HashMap<>();
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

    public static <T extends Item & Accessory> void register(T accessory) {
        CuriosApi.registerCurio(accessory, new CurioImpl(accessory));
    }
}
