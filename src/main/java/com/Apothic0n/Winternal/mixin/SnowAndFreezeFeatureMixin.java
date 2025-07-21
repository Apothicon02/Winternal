package com.Apothic0n.Winternal.mixin;

import com.Apothic0n.Winternal.Winternal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

@Mixin(SnowAndFreezeFeature.class)
public class SnowAndFreezeFeatureMixin {
    @Inject(method = "place", at = @At("HEAD"))
    public void place(FeaturePlaceContext<NoneFeatureConfiguration> p_160368_, CallbackInfoReturnable<Boolean> ci) {
        if (Winternal.enabled) {
            WorldGenLevel worldgenlevel = p_160368_.level();
            BlockPos blockpos = p_160368_.origin();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

            for(int i = 0; i < 16; ++i) {
                for(int j = 0; j < 16; ++j) {
                    int k = blockpos.getX() + i;
                    int l = blockpos.getZ() + j;
                    int i1 = worldgenlevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, k, l);
                    blockpos$mutableblockpos.set(k, i1, l);
                    blockpos$mutableblockpos1.set(blockpos$mutableblockpos).move(Direction.DOWN, 1);

                    BlockState blockstate = worldgenlevel.getBlockState(blockpos$mutableblockpos1);
                    BlockState state = worldgenlevel.getBlockState(blockpos$mutableblockpos);
                    if (state.getMaterial().isReplaceable() && blockstate.is(BlockTags.DIRT)) {
                        worldgenlevel.setBlock(blockpos$mutableblockpos, Blocks.SNOW.defaultBlockState(), UPDATE_ALL);
                        if (blockstate.hasProperty(SnowyDirtBlock.SNOWY)) {
                            worldgenlevel.setBlock(blockpos$mutableblockpos1, blockstate.setValue(SnowyDirtBlock.SNOWY, Boolean.valueOf(true)), 2);
                        }
                        if (state.getBlock() instanceof DoublePlantBlock) {
                            worldgenlevel.setBlock(blockpos$mutableblockpos.above(), Blocks.AIR.defaultBlockState(), UPDATE_ALL);
                        }
                    }
                }
            }
        }
    }
}
