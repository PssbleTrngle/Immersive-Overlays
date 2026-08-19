package cc.cassian.immersiveoverlays.compat;

import cc.cassian.immersiveoverlays.overlay.MoonOverlay;
import dev.corgitaco.enhancedcelestials2core.EnhancedCelestials;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.TextColor;

public class EnhancedCelestials2Compat {
	public static MoonOverlay.MoonPhase get(ClientLevel level) {
		//? if <26 {
		var worldDataOptional = EnhancedCelestials.lunarForecastWorldData(level);
		if (worldDataOptional.isPresent()) {
			var worldData = worldDataOptional.get();
			var path = worldData.currentLunarEventHolder().unwrapKey().orElseThrow().location().getPath();
			if (!path.equals("default")) {
				return new MoonOverlay.MoonPhase(path, MoonOverlay.MoonPhase.getText(path), worldData.currentLunarEvent().getNameColor().map(TextColor::getValue).orElse(MoonOverlay.MoonPhase.defaultColour()));
			}
		}
		//?}
		return null;
	}
}
