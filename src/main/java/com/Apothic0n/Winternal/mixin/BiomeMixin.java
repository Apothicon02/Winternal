package com.Apothic0n.Winternal.mixin;

import com.Apothic0n.Winternal.Winternal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomeMixin {

    @Shadow abstract Biome.BiomeCategory getBiomeCategory();

    @Inject(method = "shouldFreeze(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Z)Z", at = @At("HEAD"), cancellable = true)
    public void shouldFreeze(LevelReader level, BlockPos pos, boolean p_47483_, CallbackInfoReturnable<Boolean> ci) {
        if (Winternal.enabled) {
            if (this.getBiomeCategory() != Biome.BiomeCategory.THEEND && level.getFluidState(pos).is(Fluids.WATER)) {
                ci.setReturnValue(true);
            }
        }
    }

    @Inject(method = "getBaseTemperature", at = @At("HEAD"), cancellable = true)
    public void getBaseTemperature(CallbackInfoReturnable<Float> ci) {
        if (Winternal.enabled) {
            if (this.getBiomeCategory() != Biome.BiomeCategory.THEEND) {
                ci.setReturnValue(-1f);
            }
        }
    }

    @Inject(method = "getPrecipitation", at = @At("HEAD"), cancellable = true)
    public void getPrecipitation(CallbackInfoReturnable<Biome.Precipitation> ci) {
        if (Winternal.enabled) {
            if (this.getBiomeCategory() != Biome.BiomeCategory.THEEND) {
                ci.setReturnValue(Biome.Precipitation.SNOW);
            }
        }
    }

    @Inject(method = "warmEnoughToRain", at = @At("HEAD"), cancellable = true)
    public void warmEnoughToRain(CallbackInfoReturnable<Boolean> ci) {
        if (Winternal.enabled) {
            if (this.getBiomeCategory() == Biome.BiomeCategory.OCEAN) {
                ci.setReturnValue(false);
            }
        }
    }

    @Inject(method = "isHumid", at = @At("HEAD"), cancellable = true)
    public void isHumid(CallbackInfoReturnable<Boolean> ci) {
        if (Winternal.enabled) {
            if (this.getBiomeCategory() != Biome.BiomeCategory.THEEND) {
                ci.setReturnValue(true);
            }
        }
    }
}
