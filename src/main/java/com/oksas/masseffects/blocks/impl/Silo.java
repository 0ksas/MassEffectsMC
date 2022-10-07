package com.oksas.masseffects.blocks.impl;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class Silo extends BaseEntityBlock {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public Silo(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.WEST));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(FACING);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        LOGGER.debug(placeContext.getHorizontalDirection().getOpposite().toString());
        return this.defaultBlockState().setValue(FACING, placeContext.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }
}
