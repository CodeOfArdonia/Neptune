package com.iafenvoy.neptune.mixin;

import com.iafenvoy.neptune.render.BossBarRenderHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(BossBarHud.class)
public class BossBarHudMixin {
    @Inject(method = "renderBossBar(Lnet/minecraft/client/gui/DrawContext;IILnet/minecraft/entity/boss/BossBar;II)V", at = @At("HEAD"), cancellable = true)
    private void onRenderHud(DrawContext context, int x, int y, BossBar bossBar, int width, int height, CallbackInfo ci) {
        if (neptune$render(context, x, y, bossBar, width, height))
            ci.cancel();
    }

    @Unique
    private static boolean neptune$render(DrawContext context, int x, int y, BossBar bossBar, int width, int height) {
        for (BossBarRenderHelper.BossBarInfo info : BossBarRenderHelper.infos.values())
            if (info.bossBarId().contains(bossBar.getUuid())) {
                if (height == 5) {
                    context.drawTexture(BossBarRenderHelper.BARS_TEXTURE, x, y, 0, bossBar.getColor().ordinal() * 5 * 2 + 5, width, 5);
                    context.drawTexture(info.texture(), x - 10, y - 7, 0, 0, 0, 206, 19, 206, 19);
                }
                if (info.disableName()) bossBar.setName(Text.literal(""));
                return true;
            }
        return false;
    }
}
