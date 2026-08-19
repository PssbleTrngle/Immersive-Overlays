package cc.cassian.immersiveoverlays.overlay;

import cc.cassian.immersiveoverlays.compat.EnhancedCelestialsCompat;
import cc.cassian.immersiveoverlays.compat.ModCompat;
import cc.cassian.immersiveoverlays.config.ModConfig;
import cc.cassian.mru.client.util.HudUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;

import java.util.Locale;

public class MoonOverlay {
    public record MoonPhase(String sprite, Component text) {

        public static MoonPhase FULL_MOON = new MoonPhase("full_moon");
        public static MoonPhase WANING_GIBBOUS = new MoonPhase("waning_gibbous");
        public static MoonPhase THIRD_QUARTER = new MoonPhase("third_quarter");
        public static MoonPhase WANING_CRESCENT = new MoonPhase("waning_crescent");
        public static MoonPhase NEW_MOON = new MoonPhase("new_moon");
        public static MoonPhase WAXING_CRESCENT = new MoonPhase("waxing_crescent");
        public static MoonPhase FIRST_QUARTER = new MoonPhase("first_quarter");
        public static MoonPhase WAXING_GIBBOUS = new MoonPhase("waxing_gibbous");

        public static MoonPhase[] PHASES = new MoonPhase[]{FULL_MOON, WANING_GIBBOUS, THIRD_QUARTER, WANING_CRESCENT, NEW_MOON, WAXING_CRESCENT, FIRST_QUARTER, WAXING_GIBBOUS};

		public MoonPhase(String sprite, Component text) {
            this.sprite = "moon_phase/" + sprite.toLowerCase(Locale.ROOT);
            this.text = text;
		}

        public MoonPhase(String sprite) {
			this(sprite, Component.translatable("gui.c.moon_phase." + sprite));
		}

		public static MoonPhase getPhase(ClientLevel level) {
            if (ModCompat.ENHANCED_CELESTIALS) {
                MoonPhase phase = EnhancedCelestialsCompat.get(level);
                if (phase != null) {
                    return phase;
                }
            }
            //? if >26 {
            /*int phase = level.environmentAttributes().getDimensionValue(net.minecraft.world.attribute.EnvironmentAttributes.MOON_PHASE).index();
            *///?} else {
            int phase = level.getMoonPhase();
            //?}
			return PHASES[phase];
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

        MoonPhase moonPhase;
        //? if >1.21.10 {
        /*if (!mc.level.dimensionType().hasFixedTime()) {
        *///?} else {
        if (mc.level.dimensionType().natural()) {
        //?}
            moonPhase = MoonPhase.getPhase(mc.level);
        } else {
            return;
        }

        int xOffset = 3;
        // The amount of offset needed to display the moon icons.
        int iconXOffset = 20;
        int tooltipSize = 21;
        int yPlacement = ModConfig.get().moon_vertical_position;
        if (OverlayHelpers.playerHasPotions(mc.player, ModConfig.get().moon_horizontal_position_left)) {
            yPlacement += OverlayHelpers.moveBy(mc.player);
        }

        int fontWidth = mc.font.width(moonPhase.text)+iconXOffset;

        int windowWidth = mc.getWindow().getGuiScaledWidth();
        int xPlacement = OverlayHelpers.getPlacement(windowWidth, fontWidth, ModConfig.get().moon_horizontal_position_left);
        OverlayHelpers.renderBackground(guiGraphics, windowWidth, fontWidth, xPlacement, xOffset, yPlacement, tooltipSize, ModConfig.get().moon_horizontal_position_left);
        // render text
        HudUtils.drawString(guiGraphics, mc.font, moonPhase.text, xPlacement-xOffset+iconXOffset, yPlacement+2, ModConfig.get().moon_text_colour);
        OverlayHelpers.blitSprite(guiGraphics, moonPhase.sprite(), xPlacement-xOffset-1, yPlacement-1);
    }

    public static boolean isVisible() {
        return MoonOverlay.showMoon;
    }
}
