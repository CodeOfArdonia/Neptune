package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.compat.CuriosHelper;
import com.iafenvoy.neptune.render.SkullRenderRegistry;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getSkin", at = @At("HEAD"), cancellable = true)
    private void handleCustomSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        Optional<SkullRenderRegistry.SkinInfo> optional = this.neptune$findTexture(this.getItemBySlot(EquipmentSlot.HEAD));
        if (optional.isPresent()) cir.setReturnValue(optional.get().copyOrCreate(cir.getReturnValue()));
        else {
            optional = this.neptune$findTexture(CuriosHelper.getEquipped(this).get(CuriosHelper.Place.HAT));
            optional.ifPresent(x -> cir.setReturnValue(x.copyOrCreate(cir.getReturnValue())));
        }
    }

    @Unique
    private Optional<SkullRenderRegistry.SkinInfo> neptune$findTexture(ItemStack head) {
        if (head.getItem() instanceof SkullRenderRegistry.SkullTextureProvider provider)
            return provider.getTexture(head);
        if (head.getItem() instanceof PlayerHeadItem skullItem && skullItem.getBlock() instanceof AbstractSkullBlock skullBlock) {
            SkullRenderRegistry.SkinInfo texture = SkullRenderRegistry.getTextureFromType(skullBlock.getType());
            if (texture != null) return Optional.of(texture);
            if (skullBlock.getType() == SkullBlock.Types.PLAYER) {
                ResolvableProfile profile = head.get(DataComponents.PROFILE);
                if (profile != null) {
                    SkinManager manager = Minecraft.getInstance().getSkinManager();
                    return Optional.of(new SkullRenderRegistry.SkinInfo(manager.getInsecureSkin(profile.gameProfile())));
                }
            }
        }
        return Optional.empty();
    }
}
