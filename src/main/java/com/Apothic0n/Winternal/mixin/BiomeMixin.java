package com.Apothic0n.Winternal.mixin;

import com.Apothic0n.Winternal.Winternal;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomeMixin {

    @Shadow abstract Biome.BiomeCategory getBiomeCategory();

    @Inject(method = "getBaseTemperature", at = @At("HEAD"), cancellable = true)
    public void getBaseTemperature(CallbackInfoReturnable<Float> ci) {
        if (Winternal.enabled) {
            if (!this.getBiomeCategory().getName().contains("end")) {
                ci.setReturnValue(-1f);
            }
        }
    }

    @Inject(method = "isHumid", at = @At("HEAD"), cancellable = true)
    public void isHumid(CallbackInfoReturnable<Boolean> ci) {
        if (Winternal.enabled) {
            if (!this.getBiomeCategory().getName().contains("end")) {
                ci.setReturnValue(true);
            }
        }
    }
}
