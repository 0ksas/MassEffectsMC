package com.oksas.masseffects.blocks.impl;

import com.google.common.collect.Lists;
import com.oksas.masseffects.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraft.world.level.material.Material;

import java.util.Queue;

public class LavaSponge extends Block {
    public LavaSponge(BlockBehaviour.Properties p_56796_) {
        super(p_56796_);
    }

    @Override
    public void onPlace(BlockState block, Level level, BlockPos blockPos, BlockState blockState, boolean p_56815_) {
        if (!blockState.is(block.getBlock())) {
            this.tryAbsorbLava(level, blockPos);
        }
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        this.tryAbsorbLava(level, blockPos);
        super.neighborChanged(blockState, level, blockPos, block, blockPos1, b);
    }

    protected void tryAbsorbLava(Level level, BlockPos blockPos) {
        if (this.removeLavaBreadthFirstSearch(level, blockPos)) {
            level.setBlock(blockPos, ModBlocks.MOLTEN_LAVA_SPONGE.get().defaultBlockState(), 2);
            level.levelEvent(2001, blockPos, Block.getId(Blocks.LAVA.defaultBlockState()));
        }

    }

    private boolean removeLavaBreadthFirstSearch(Level level, BlockPos blockPos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(blockPos, 0));
        int i = 0;

        while(!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getA();
            int j = tuple.getB();

            for(Direction direction : Direction.values()) {
                BlockPos blockpos1 = blockpos.relative(direction);
                BlockState blockstate = level.getBlockState(blockpos1);
                FluidState fluidstate = level.getFluidState(blockpos1);
                Material material = blockstate.getMaterial();

                if (fluidstate.getType() instanceof LavaFluid.Source || fluidstate.getType() instanceof LavaFluid.Flowing) {
                    if (blockstate.getBlock() instanceof BucketPickup && !((BucketPickup)blockstate.getBlock()).pickupBlock(level, blockpos1, blockstate).isEmpty()) {
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof LiquidBlock) {
                        level.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (material == Material.LAVA) {
                        BlockEntity blockentity = blockstate.hasBlockEntity() ? level.getBlockEntity(blockpos1) : null;
                        dropResources(blockstate, level, blockpos1, blockentity);
                        level.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    }
                }
            }

            if (i > 64) {
                break;
            }
        }

        return i > 0;
    }
}

