package cc.cassian.immersiveoverlays.compat;

import cc.cassian.immersiveoverlays.mixin.MoonHandlerAccessor;
import cc.cassian.immersiveoverlays.overlay.MoonOverlay;
import com.mrbysco.lunar.client.MoonHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import com.mrbysco.lunar.api.LunarEvent;
import net.minecraft.resources.ResourceLocation;

public class LunarCompat {
	public static MoonOverlay.MoonPhase get(ClientLevel level) {
		if (MoonHandler.isEventActive()) {
			//? if >1.21 {
			ResourceLocation moonID = ResourceLocation.parse(MoonHandlerAccessor.getMoonID());
			//?} else {
			/*ResourceLocation moonID = new ResourceLocation(MoonHandlerAccessor.getMoonID());
			*///?}
			if (moonID.getPath().equals("regular")) return null;
			return new MoonOverlay.MoonPhase(moonID.getPath(), MoonOverlay.MoonPhase.getText(moonID), MoonHandler.getMoonColor());
		}
		return null;
	}
}
