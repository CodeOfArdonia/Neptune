package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.accessory.AccessoryManager;
import com.iafenvoy.neptune.render.SkullRenderRegistry;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getSkinTexture", at = @At("HEAD"), cancellable = true)
    private void handleCustomSkin(CallbackInfoReturnable<Identifier> cir) {
        Optional<Identifier> optional = this.neptune$findTexture(this.getEquippedStack(EquipmentSlot.HEAD));
        if (optional.isPresent()) cir.setReturnValue(optional.get());
        else {
            optional = this.neptune$findTexture(AccessoryManager.getEquipped(this, AccessoryManager.Place.HAT));
            optional.ifPresent(cir::setReturnValue);
        }
    }

    @Unique
    private Optional<Identifier> neptune$findTexture(ItemStack head) {
        if (head.getItem() instanceof SkullRenderRegistry.SkullTextureProvider provider)
            return provider.getTexture(head);
        if (head.getItem() instanceof SkullItem skullItem && skullItem.getBlock() instanceof AbstractSkullBlock skullBlock) {
            Identifier texture = SkullRenderRegistry.getTextureFromType(skullBlock.getSkullType());
            if (texture != null) return Optional.of(texture);
            if (skullBlock.getSkullType() == SkullBlock.Type.PLAYER) {
                GameProfile gameProfile = null;
                if (head.getNbt() != null) {//Copy from net.minecraft.block.PlayerSkullBlock#onPlaced
                    NbtCompound nbtCompound = head.getNbt();
                    if (nbtCompound.contains("SkullOwner", 10))
                        gameProfile = NbtHelper.toGameProfile(nbtCompound.getCompound("SkullOwner"));
                    else if (nbtCompound.contains("SkullOwner", 8) && !Util.isBlank(nbtCompound.getString("SkullOwner")))
                        gameProfile = new GameProfile(null, nbtCompound.getString("SkullOwner"));
                }
                if (gameProfile != null) {
                    PlayerSkinProvider skinProvider = MinecraftClient.getInstance().getSkinProvider();
                    Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = skinProvider.getTextures(gameProfile);
                    if (map.containsKey(MinecraftProfileTexture.Type.SKIN))
                        return Optional.of(skinProvider.loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN));
                }
            }
        }
        return Optional.empty();
    }
}
