package com.ibraheemrodrigues.heemsweeper;

import com.ibraheemrodrigues.heemsweeper.block.Blocks;
import com.ibraheemrodrigues.heemsweeper.block.blockentity.BlockEntities;
import com.ibraheemrodrigues.heemsweeper.structures.Structures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.structure.StructurePieceType;

public class HeemSweeperMod implements ModInitializer {

	@Override
	public void onInitialize() {
		Blocks.init();
		BlockEntities.init();
		Structures.init();
	}
}
