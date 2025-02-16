package com.iafenvoy.neptune.screen;

import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerData;
import com.iafenvoy.neptune.power.PowerKeybindings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PowerHudRenderer {
    private static final Identifier WIDGETS_TEXTURE = Identifier.of(Identifier.DEFAULT_NAMESPACE, "textures/gui/widgets.png");
    private static final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

    public static void render(MinecraftClient client, DrawContext context) {
        if (client.player == null || client.player.isSpectator()) return;
        PowerData data = PowerData.byPlayer(client.player);
        if (!data.isEnabled()) return;
        //Render Power Slot
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();
        int x = width / 2 + 120, y = height - 22, i = 0;
        for (PowerCategory category : PowerCategory.values())
            if (category.shouldDisplay() && data.isEnabled(category)) {
                renderOne(x + i * 21, y, context, data.get(category));
                i++;
            }
    }

    private static void renderOne(int x, int y, DrawContext context, PowerData.SinglePowerData data) {
        PowerKeybindings.KeyBindingHolder binding = PowerKeybindings.get(data.getType());
        context.drawTexture(WIDGETS_TEXTURE, x, y, 59, 22, 23, 23);
        //Render Cooldown
        String text = switch (data.getState()) {
            case ALLOW -> "§aR";
            case RECOVER -> String.format("§e%.1fs", 1.0 * data.getSecondaryCooldown() / 20);
            case DENY -> String.format("§c%.1fs", 1.0 * data.getPrimaryCooldown() / 20);
        };
        context.drawTextWithShadow(textRenderer, text, x + 2, y - 10, 0xFFFFFFFF);
        //Render Power Icon
        if (binding.isPressed() || data.isEnabled()) context.drawTexture(WIDGETS_TEXTURE, x + 1, y + 1, 1, 23, 23, 23);
        context.drawTexture(data.getActivePower().getIconTexture(), x + 4, y + 4, 0, 0, 16, 16, 16, 16);
    }
}
