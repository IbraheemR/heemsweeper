package com.ibraheemrodrigues.heemsweeper.block.blockentity;

import com.ibraheemrodrigues.heemsweeper.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntities {
    public static BlockEntityType<TheOverseerBlockEntity> THE_OVERSEER_BLOCK_ENTITY;

    public static void init() {
        THE_OVERSEER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "heemsweeper:demo", BlockEntityType.Builder.create(TheOverseerBlockEntity::new, Blocks.THE_OVERSEER).build(null));
    }

}
