package com.ibraheemrodrigues.heemsweeper.block.blockentity;

import com.ibraheemrodrigues.heemsweeper.block.Blocks;
import com.ibraheemrodrigues.heemsweeper.block.MineBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

public class TheOverseerBlockEntity extends BlockEntity implements Tickable {

    private int board_size = 9;
    public TheOverseerBlockEntity() {
        super(BlockEntities.THE_OVERSEER_BLOCK_ENTITY);
    }

    @Override
    public void tick() {
        int unfound = 0;
        for (int i = -Math.floorDiv(board_size, 2); i <= Math.floorDiv(board_size, 2); i++) {
            for (int j = -Math.floorDiv(board_size, 2); j <= Math.floorDiv(board_size, 2); j++) {
                BlockPos pos2 = this.pos.add(i, -4, j);
                BlockState bs = this.world.getBlockState(pos2);
                if (bs.getBlock() == Blocks.MINE_BLOCK && !  bs.get(MineBlock.FLAGGED)) unfound++;
            }
        }

        if (unfound == 0) {
            this.breakBoard();
            ItemEntity item = new ItemEntity(this.world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), new ItemStack(Items.DIAMOND, 10));
            this.world.spawnEntity(item);
        }
    }

    private void breakBoard() {
        this.world.breakBlock(this.pos, false);
        for (int i = -Math.floorDiv(board_size, 2); i <= Math.floorDiv(board_size, 2); i++) {
            for (int j = -Math.floorDiv(board_size, 2); j <= Math.floorDiv(board_size, 2); j++) {
                BlockPos pos2 = this.pos.add(i, -4, j);
                this.world.breakBlock(pos2, false);
            }
        }
    }
}
