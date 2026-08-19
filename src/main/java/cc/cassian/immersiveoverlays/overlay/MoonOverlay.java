package cc.cassian.immersiveoverlays.overlay;

import cc.cassian.immersiveoverlays.config.ModConfig;
import cc.cassian.mru.client.util.HudUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;

import java.util.Locale;

public class MoonOverlay {
    public enum MoonPhase {
        FULL_MOON(0, "full_moon"),
        WANING_GIBBOUS(1, "waning_gibbous"),
        THIRD_QUARTER(2, "third_quarter"),
        WANING_CRESCENT(3, "waning_crescent"),
        NEW_MOON(4, "new_moon"),
        WAXING_CRESCENT(5, "waxing_crescent"),
        FIRST_QUARTER(6, "first_quarter"),
        WAXING_GIBBOUS(7, "waxing_gibbous");

		MoonPhase(int phase, String name) {

        }

		public static MoonPhase getPhase(ClientLevel level) {
			return values()[level.getMoonPhase()];
		}
	}


    public static boolean showMoon = false;

    //~ if >1.21 'float'-> 'net.minecraft.client.DeltaTracker'
    public static void renderGameOverlayEvent(GuiGraphics guiGraphics, net.minecraft.client.DeltaTracker deltaTracker) {
        if (!isVisible() || !ModConfig.get().moon_enable)
            return;
        var mc = Minecraft.getInstance();
        if (OverlayHelpers.shouldCancelRender(mc))
            return;
        if (mc.level == null || mc.player == null) return;

        String moonPhase = "";
        Component moonPhaseText = Component.empty();
        //? if >1.21.10 {
        /*if (!mc.level.dimensionType().hasFixedTime()) {
        *///?} else {
        if (mc.level.dimensionType().natural()) {
        //?}
            moonPhase = MoonPhase.getPhase(mc.level).name().toLowerCase(Locale.ROOT);
            moonPhaseText = Component.translatable("gui.c.moon_phase." + moonPhase);
        }

        int xOffset = 3;
        // The amount of offset needed to display the moon icons, if visible.
        int iconXOffset = 20;
        int tooltipSize = 21;
        int yPlacement = ModConfig.get().moon_vertical_position;
        if (OverlayHelpers.playerHasPotions(mc.player, ModConfig.get().moon_horizontal_position_left)) {
            yPlacement += OverlayHelpers.moveBy(mc.player);
        }
        int iconYPlacement = yPlacement;
        int textYPlacement = yPlacement + 2;

        int fontWidth = mc.font.width(moonPhaseText)+iconXOffset;



        int windowWidth = mc.getWindow().getGuiScaledWidth();
        int xPlacement = OverlayHelpers.getPlacement(windowWidth, fontWidth, ModConfig.get().moon_horizontal_position_left);
        OverlayHelpers.renderBackground(guiGraphics, windowWidth, fontWidth, xPlacement, xOffset, yPlacement, tooltipSize, ModConfig.get().moon_horizontal_position_left);
        // render text
        HudUtils.drawString(guiGraphics, mc.font, moonPhaseText, xPlacement-xOffset+iconXOffset, textYPlacement, ModConfig.get().moon_text_colour);
        OverlayHelpers.blitSprite(guiGraphics,"moon_phase_" +  moonPhase, xPlacement-xOffset-1, iconYPlacement-1);
    }

    public static boolean isVisible() {
        return MoonOverlay.showMoon;
    }
}
