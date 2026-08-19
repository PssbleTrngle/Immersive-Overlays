package cc.cassian.immersiveoverlays.compat;

import cc.cassian.immersiveoverlays.overlay.ClockOverlay;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;

public class FabricSeasonsCompat {
    public static ClockOverlay.Season getSeason(ClientLevel level) {
        //? if <26 {
        var currentSeason = io.github.lucaargolo.seasons.FabricSeasons.getCurrentSeason(level);
        return new ClockOverlay.Season(currentSeason.name(), Component.translatable(currentSeason.getTranslationKey()));
        //?} else {
        /*return ClockOverlay.Season.UNKNOWN;
        *///?}
    }
}
