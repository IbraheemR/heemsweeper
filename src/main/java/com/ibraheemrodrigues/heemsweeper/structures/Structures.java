package com.ibraheemrodrigues.heemsweeper.structures;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class Structures {
    public static final StructurePieceType DUNGEON_PIECE = HeemsweeperDungeonGenerator.Piece::new;
    public static final StructureFeature<DefaultFeatureConfig> DUNGEON_STRUCTURE = new HeemsweeperDungeonFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> DUNGEON_CONFIGURED = DUNGEON_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    public static void init() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier("heemsweeper", "dungeon"), DUNGEON_PIECE);
        FabricStructureBuilder.create(new Identifier("heemsweeper", "dungeon"), DUNGEON_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> config_dungeon = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, new Identifier("heemsweeper", "dungeon"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, config_dungeon.getValue(), DUNGEON_CONFIGURED);


        BiomeModifications.addStructure(BiomeSelectors.all(), config_dungeon);
    }
}
