package dev.astler.gsmod.world;

import dev.astler.gsmod.blocks.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dev.astler.gsmod.GSMod.MODID;

@Mod.EventBusSubscriber
public class ModFeatures {
    public static ConfiguredFeature<?, ?> FORTIFIED_STONE_GEN;

    public static void registerFeatures() {
        FORTIFIED_STONE_GEN = Feature.ORE.configured(
                        new OreConfiguration(
                                OreConfiguration.Predicates.NATURAL_STONE,
                                ModBlocks.FORTIFIED_STONE.get().defaultBlockState(), 50))
                        .rangeUniform(VerticalAnchor.aboveBottom(10),
                                VerticalAnchor.belowTop(10))
                        .squared()
                        .count(30);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MODID, "fortified_stone_gen"), FORTIFIED_STONE_GEN);
    }

    @SubscribeEvent
    public static void generateFeatures(final BiomeLoadingEvent event) {
        event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> FORTIFIED_STONE_GEN);
    }
}
