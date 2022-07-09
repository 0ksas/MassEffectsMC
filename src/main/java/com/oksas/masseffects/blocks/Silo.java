package com.oksas.masseffects.blocks;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class Silo extends Block  {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public Silo(Properties properties) {
        super(properties);
        LOGGER.debug(properties.toString());
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
}
