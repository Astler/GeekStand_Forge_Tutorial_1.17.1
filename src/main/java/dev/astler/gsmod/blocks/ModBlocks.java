package dev.astler.gsmod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dev.astler.gsmod.GSMod.MODID;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> FORTIFIED_STONE = BLOCKS.register("fortified_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(2F, 8.0F)));

    public static final RegistryObject<Block> PRESS_TABLE = BLOCKS.register("press_table", () -> new PressTableBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(2F, 8.0F)));
}