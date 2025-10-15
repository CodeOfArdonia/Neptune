package com.iafenvoy.neptune.screen;

import com.iafenvoy.neptune.ability.AbilityCategory;
import com.iafenvoy.neptune.ability.AbilityData;
import com.iafenvoy.neptune.ability.AbilityKeybindings;
import com.iafenvoy.neptune.registry.NeptuneRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbilityHudRenderer {
    private static final ResourceLocation HOTBAR_SELECTION_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_selection");
    private static final ResourceLocation HOTBAR_OFFHAND_LEFT_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_offhand_left");
    private static final ResourceLocation HOTBAR_OFFHAND_RIGHT_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_offhand_right");
    private static final Font TEXT_RENDERER = Minecraft.getInstance().font;

    public static void render(Minecraft client, GuiGraphics context) {
        if (client.player == null || client.player.isSpectator()) return;
        AbilityData data = AbilityData.get(client.player);
        //Render Ability Slot
        int width = context.guiWidth();
        int height = context.guiHeight();
        int x = width / 2 + 120, y = height - 24, i = 0;
        for (AbilityCategory category : NeptuneRegistries.ABILITY_CATEGORY)
            if (category.shouldDisplay() && data.isEnabled(category)) {
                renderOne(x + i * 21, y, context, data.get(category));
                i++;
            }
    }

    private static void renderOne(int x, int y, GuiGraphics context, AbilityData.SingleAbilityData data) {
        context.pose().pushPose();
        AbilityKeybindings.KeyBindingHolder binding = AbilityKeybindings.get(data.getType());
        context.blitSprite(HOTBAR_OFFHAND_LEFT_SPRITE, x, y, 29, 24);
        //Render Cooldown
        String text = switch (data.getState()) {
            case ALLOW -> "§aR";
            case RECOVER -> String.format("§e%.1fs", 1.0 * data.getSecondaryCooldown() / 20);
            case DENY -> String.format("§c%.1fs", 1.0 * data.getPrimaryCooldown() / 20);
        };
        context.drawString(TEXT_RENDERER, text, x + 2, y - 10, 0xFFFFFFFF);
        //Render Ability Icon
        context.pose().translate(0, 0, 1);
        if (binding.isPressed() || data.isEnabled()) context.blitSprite(HOTBAR_SELECTION_SPRITE, x - 1, y, 24, 23);
        context.pose().translate(0, 0, 2);
        context.blit(data.getActiveAbility().getIconTexture(), x + 3, y + 4, 0, 0, 16, 16, 16, 16);
        context.pose().popPose();
    }
}
