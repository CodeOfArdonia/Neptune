package com.iafenvoy.neptune.registry;

import com.iafenvoy.neptune.Neptune;
import com.iafenvoy.neptune.ability.AbilityData;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class NeptuneAttachments {
    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Neptune.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<AbilityData>> ABILITY = REGISTRY.register("ability", () -> AttachmentType.builder(AbilityData::new).serialize(AbilityData.CODEC).sync(ByteBufCodecs.fromCodecWithRegistries(AbilityData.CODEC)).copyOnDeath().build());

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            AbilityData data = living.getData(ABILITY);
            data.tick(living);
            if (data.isDirty()) living.syncData(ABILITY);
        }
    }
}
