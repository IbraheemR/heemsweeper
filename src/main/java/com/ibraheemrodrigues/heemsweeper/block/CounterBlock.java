package com.ibraheemrodrigues.heemsweeper.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class CounterBlock extends Block {
    public static final IntProperty ADJACENT = IntProperty.of("adjacent", 1, 26);

    public CounterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(ADJACENT, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ADJACENT);
    }

    public static int countAdjacent(WorldAccess world, BlockPos pos) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    BlockPos pos2 = pos.add(i, j, k);
                    BlockState bs = world.getBlockState(pos2);
                    if (bs.getBlock() == Blocks.MINE_BLOCK) count ++;
                }
            }
        }

        return count;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(Blocks.TILE_BLOCK);
    }
}
