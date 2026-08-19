package cc.cassian.immersiveoverlays.mixin;

import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import com.mrbysco.lunar.client.MoonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@IfModLoaded("lunar")
@Mixin(MoonHandler.class)
public interface MoonHandlerAccessor {
	@Accessor
	static String getMoonID() {
		throw new UnsupportedOperationException();
	}
}
