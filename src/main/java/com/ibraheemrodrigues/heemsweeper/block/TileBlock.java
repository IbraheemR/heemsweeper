package com.ibraheemrodrigues.heemsweeper.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static com.ibraheemrodrigues.heemsweeper.block.Blocks.COUNTER_BLOCK;
import static com.ibraheemrodrigues.heemsweeper.block.Blocks.TILE_BLOCK;

public class TileBlock extends Block {
    public static final BooleanProperty FLAGGED =  BooleanProperty.of("flagged");

    public TileBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FLAGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FLAGGED);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if (state.get(FLAGGED)){
            world.setBlockState(pos, this.getDefaultState().with(FLAGGED, true), 2);
        } else {
            int count = CounterBlock.countAdjacent(world, pos);
            if (count > 0) {
                world.setBlockState(pos, COUNTER_BLOCK.getDefaultState().with(CounterBlock.ADJACENT, count), 0);
            } else {
             // Cascade reveal
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        for (int k = -1; k <= 1; k++) {
                            if (i == 0 && j == 0 && k == 0) continue;
                            BlockPos pos2 = pos.add(i, j, k);
                            BlockState bs = world.getBlockState(pos2);
                            Block block = bs.getBlock();

                            if (block == TILE_BLOCK) {
                                world.breakBlock(pos2, false);
                                block.onBroken(world, pos2, bs);

                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.cycle(FLAGGED));
        return ActionResult.success(world.isClient);
    }
}
