package com.ibraheemrodrigues.heemsweeper.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;

public class MineBlock extends Block {

    public static final BooleanProperty FLAGGED =  BooleanProperty.of("flagged");

    public MineBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FLAGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FLAGGED);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if (world instanceof ServerWorld ) {
            if (!state.get(FLAGGED).booleanValue()){
                this.explode((World) world, pos);
            } else {
                world.setBlockState(pos, this.getDefaultState().with(FLAGGED, true), 0);
            }
        }

        super.onBroken(world, pos, state);
    }



    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        this.explode(world, pos);
        super.onDestroyedByExplosion(world, pos, explosion);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.cycle(FLAGGED));
        return ActionResult.success(world.isClient);
    }

    private static void explode(World world, BlockPos pos) {
        if (!world.isClient())
        world.createExplosion(
                null,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                5.0F,
                Explosion.DestructionType.DESTROY
        );
    }
}
