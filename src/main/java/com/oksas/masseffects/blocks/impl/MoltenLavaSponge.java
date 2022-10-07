package com.oksas.masseffects.blocks.impl;

import com.oksas.masseffects.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class MoltenLavaSponge extends Block {

    public MoltenLavaSponge(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void onPlace(BlockState block, Level level, BlockPos blockPos, BlockState blockState, boolean p_56815_) {
        if (!blockState.is(block.getBlock())) {
            tryMakeObsidian(level, blockPos, blockState);
        }
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        tryMakeObsidian(level, blockPos, blockState);
        super.neighborChanged(blockState, level, blockPos, block, blockPos1, b);
    }

    public void tryMakeObsidian(Level level, BlockPos blockPos, BlockState blockState) {
        boolean hasInteracted = false;
        for(Direction direction : Direction.values()) {
            BlockPos blockpos = blockPos.relative(direction.getOpposite());
            FluidState fluidstate = level.getFluidState(blockpos);
            if ( fluidstate.is(FluidTags.WATER)) {
                level.setBlock(blockpos, Blocks.STONE.defaultBlockState(), 3);
                level.levelEvent(1501, blockPos, 0);
                hasInteracted = true;
            }
        }

        if (hasInteracted) {
            level.setBlock(blockPos, ModBlocks.LAVA_SPONGE.get().defaultBlockState(), 2);
            level.levelEvent(2001, blockPos, Block.getId(Blocks.WATER.defaultBlockState()));

            playExtinguishSound(blockPos, level);
        }
    }

    private void playExtinguishSound(BlockPos blockPos, Level level) {
        RandomSource randomsource = RandomSource.create();

        double d0 = (double)blockPos.getX() + randomsource.nextDouble();
        double d1 = (double)blockPos.getY() + 1.0D;
        double d2 = (double)blockPos.getZ() + randomsource.nextDouble();

        level.playLocalSound(d0, d1, d2, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,
                0.2F + randomsource.nextFloat() * 0.2F, 0.9F + randomsource.nextFloat() * 0.15F, false);
    }
}
