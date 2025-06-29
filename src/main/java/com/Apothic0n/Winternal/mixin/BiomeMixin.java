package com.Apothic0n.Winternal.mixin;

import com.Apothic0n.Winternal.Winternal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Biome.class)
public abstract class BiomeMixin {

//    @Inject(method = "getBaseTemperature", at = @At("HEAD"), cancellable = true)
//    public void getBaseTemperature(CallbackInfoReturnable<Float> ci) {
//        ci.setReturnValue(-1f);
//    }

    @Final
    @Shadow
    private Biome.ClimateSettings climateSettings;

    @Shadow public abstract float getDownfall();

    /**
     * @author Apothicon
     * @reason Make temperature freezing everywhere.
     */
    @Overwrite
    public final float getBaseTemperature() {
        if (Winternal.enabled) {
            return -1f;
        }
        return this.climateSettings.temperature;
    }

    /**
     * @author Apothicon
     * @reason Make weather everywhere.
     */
    @Overwrite
    public boolean isHumid() {
        if (Winternal.enabled) {
            return true;
        }
        return this.getDownfall() > 0.85f;
    }
}
