package com.ibraheemrodrigues.heemsweeper.block;

import com.ibraheemrodrigues.heemsweeper.block.blockentity.TheOverseerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class OverseerBlock extends Block implements BlockEntityProvider {
    public OverseerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TheOverseerBlockEntity();
    }
}
