package cc.cassian.immersiveoverlays.compat;

import cc.cassian.immersiveoverlays.overlay.ClockOverlay;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import sereneseasons.api.season.SeasonHelper;

import java.util.Locale;

public class SereneSeasonsCompat {
    public static ClockOverlay.Season getSeason(ClientLevel level, BlockPos pos) {
        var biome = level.getBiome(pos);
        var state = SeasonHelper.getSeasonState(level);
        if (SeasonHelper.usesTropicalSeasons(biome)) {
            return new ClockOverlay.Season(state.getTropicalSeason().name(), Component.translatable("desc.sereneseasons."+state.getTropicalSeason().name().toLowerCase(Locale.ROOT)));
        } else {
            return new ClockOverlay.Season(state.getSubSeason().name(), Component.translatable("desc.sereneseasons."+state.getSubSeason().name().toLowerCase(Locale.ROOT)));
        }
    }
}
