package com.iafenvoy.neptune.screen;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.ability.AbilityKeybindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbilityHudRenderer {
    private static final ResourceLocation WIDGETS_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/widgets.png");
    private static final Font TEXT_RENDERER = Minecraft.getInstance().font;

    public static void render(Minecraft client, GuiGraphics context) {
        if (client.player == null || client.player.isSpectator()) return;
        AbilityData data = AbilityData.get(client.player);
        if (!data.isEnabled()) return;
        //Render Ability Slot
        int width = context.guiWidth();
        int height = context.guiHeight();
        int x = width / 2 + 120, y = height - 22, i = 0;
        for (AbilityCategory category : AbilityCategory.values())
            if (category.shouldDisplay() && data.isEnabled(category)) {
                renderOne(x + i * 21, y, context, data.get(category));
                i++;
            }
    }

    private static void renderOne(int x, int y, GuiGraphics context, AbilityData.SingleAbilityData data) {
        AbilityKeybindings.KeyBindingHolder binding = AbilityKeybindings.get(data.getType());
        context.blit(WIDGETS_TEXTURE, x, y, 59, 22, 23, 23);
        //Render Cooldown
        String text = switch (data.getState()) {
            case ALLOW -> "§aR";
            case RECOVER -> String.format("§e%.1fs", 1.0 * data.getSecondaryCooldown() / 20);
            case DENY -> String.format("§c%.1fs", 1.0 * data.getPrimaryCooldown() / 20);
        };
        context.drawString(TEXT_RENDERER, text, x + 2, y - 10, 0xFFFFFFFF);
        //Render Ability Icon
        if (binding.isPressed() || data.isEnabled()) context.blit(WIDGETS_TEXTURE, x + 1, y + 1, 1, 23, 23, 23);
        context.blit(data.getActiveAbility().getIconTexture(), x + 4, y + 4, 0, 0, 16, 16, 16, 16);
    }
}
