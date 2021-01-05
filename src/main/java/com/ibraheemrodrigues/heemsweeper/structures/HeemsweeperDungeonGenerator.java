package com.ibraheemrodrigues.heemsweeper.structures;


import com.ibraheemrodrigues.heemsweeper.HeemSweeperMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

import java.util.List;
import java.util.Random;

public class HeemsweeperDungeonGenerator {
    private static final Identifier GAME_DUNGEON = new Identifier("heemsweeper", "game/game9x9");

    public static void addPieces(StructureManager manager, BlockPos pos, List<StructurePiece> pieces) {
        pieces.add(new Piece(manager, pos, GAME_DUNGEON));
    }


    public static class Piece extends SimpleStructurePiece {
        private final Identifier template;

        public Piece(StructureManager structureManager, CompoundTag compoundTag) {
            super(Structures.DUNGEON_PIECE, compoundTag);
            this.template = new Identifier(compoundTag.getString("Template"));
            this.initializeStructureData(structureManager);
        }

        public Piece(StructureManager structureManager, BlockPos pos, Identifier template) {
            super(Structures.DUNGEON_PIECE, 0);
            this.pos = pos;
            this.template = template;

            this.initializeStructureData(structureManager);
        }

        private void initializeStructureData(StructureManager structureManager) {
            Structure structure = structureManager.getStructureOrBlank(this.template);
            StructurePlacementData placementData = (new StructurePlacementData())
                    .setRotation(BlockRotation.NONE)
                    .setMirror(BlockMirror.NONE)
                    .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(structure, this.pos, placementData);
        }

        protected void toNbt(CompoundTag tag) {
            super.toNbt(tag);
            tag.putString("Template", this.template.toString());
            tag.putString("Rot", BlockRotation.NONE.name());
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random,
                                      BlockBox boundingBox) {
            // TODO - polpulate gameboard
        }
    }
}
