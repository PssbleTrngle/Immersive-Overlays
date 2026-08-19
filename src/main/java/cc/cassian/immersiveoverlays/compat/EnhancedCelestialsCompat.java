package cc.cassian.immersiveoverlays.compat;

import cc.cassian.immersiveoverlays.overlay.MoonOverlay;
import dev.corgitaco.enhancedcelestials.EnhancedCelestials;
import net.minecraft.client.multiplayer.ClientLevel;

public class EnhancedCelestialsCompat {
	public static MoonOverlay.MoonPhase get(ClientLevel level) {
		//? if <26 {
		var worldDataOptional = EnhancedCelestials.lunarForecastWorldData(level);
		if (worldDataOptional.isPresent()) {
			var worldData = worldDataOptional.get();
			var path = worldData.currentLunarEventHolder().unwrapKey().get().location().getPath();
			if (!path.equals("default")) {
				return new MoonOverlay.MoonPhase(path, worldData.currentLunarEvent().getTextComponents().name().getComponent());
			}
		}
		//?}
		return null;
	}
}
