package com.ibraheemrodrigues.heemsweeper.block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static final MineBlock MINE_BLOCK = new MineBlock(FabricBlockSettings.of(Material.STONE));
    public static final TileBlock TILE_BLOCK = new TileBlock(FabricBlockSettings.of(Material.STONE));
    public static final CounterBlock COUNTER_BLOCK = new CounterBlock(FabricBlockSettings.of(Material.STONE));

    public static final OverseerBlock THE_OVERSEER = new OverseerBlock(FabricBlockSettings.of(Material.STONE));

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier("heemsweeper", "the_overseer"), THE_OVERSEER);
        Registry.register(Registry.BLOCK, new Identifier("heemsweeper", "mine"), MINE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("heemsweeper", "tile"), TILE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("heemsweeper", "counter"), COUNTER_BLOCK);

        Registry.register(Registry.ITEM, new Identifier("heemsweeper", "the_overseer"), new BlockItem(THE_OVERSEER, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("heemsweeper", "mine"), new BlockItem(MINE_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("heemsweeper", "tile"), new BlockItem(TILE_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));

    }
}
