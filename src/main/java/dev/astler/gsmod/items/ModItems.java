package dev.astler.gsmod.items;

import dev.astler.gsmod.blocks.ModBlocks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dev.astler.gsmod.GSMod.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> BOULDERS = ITEMS.register("boulders", () -> new Item(new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));

    public static final RegistryObject<Item> STONE_STICK = ITEMS.register("stone_stick", () -> new Item(new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE = ITEMS.register("fortified_stone", () -> new BlockItem(ModBlocks.FORTIFIED_STONE.get(), new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));

    public static final RegistryObject<Item> CAN = ITEMS.register("can", () -> new Item(new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> CANDY = ITEMS.register("candy", () -> new Item(new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB).food(ModFood.CANDY)));
    public static final RegistryObject<Item> CAN_WITH_FOOD = ITEMS.register("can_with_food", () -> new CanFoodItem(new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB).food(ModFood.CAN_WITH_FOOD).stacksTo(1)));

    public static final RegistryObject<Item> FORTIFIED_STONE_AXE = ITEMS.register("fortified_stone_axe", () -> new AxeItem(ModTiers.FORTIFIED_STONE, 6.0F, -3.1F, new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE_SHOVEL = ITEMS.register("fortified_stone_shovel", () -> new ShovelItem(ModTiers.FORTIFIED_STONE, 1.5F, -3.0F, new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE_SWORD = ITEMS.register("fortified_stone_sword", () -> new SwordItem(ModTiers.FORTIFIED_STONE, 3, -2.4F, new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE_PICKAXE = ITEMS.register("fortified_stone_pickaxe", () -> new PickaxeItem(ModTiers.FORTIFIED_STONE, 1, -2.8F, new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE_HOE = ITEMS.register("fortified_stone_hoe", () -> new HoeItem(ModTiers.FORTIFIED_STONE, -2, -1F, new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));

    public static final RegistryObject<Item> FORTIFIED_STONE_HELMET = ITEMS.register("fortified_stone_helmet",  () -> new ArmorItem(ModArmorMaterials.FORTIFIED_STONE, EquipmentSlot.HEAD, (new Item.Properties()).tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE_CHESTPLATE = ITEMS.register("fortified_stone_chestplate",  () -> new ArmorItem(ModArmorMaterials.FORTIFIED_STONE, EquipmentSlot.CHEST, (new Item.Properties()).tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE_LEGGINGS = ITEMS.register("fortified_stone_leggings",  () -> new ArmorItem(ModArmorMaterials.FORTIFIED_STONE, EquipmentSlot.LEGS, (new Item.Properties()).tab(ModCreativeTabs.GS_MOD_TAB)));
    public static final RegistryObject<Item> FORTIFIED_STONE_BOOTS = ITEMS.register("fortified_stone_boots",  () -> new ArmorItem(ModArmorMaterials.FORTIFIED_STONE, EquipmentSlot.FEET, (new Item.Properties()).tab(ModCreativeTabs.GS_MOD_TAB)));

    public static final RegistryObject<Item> PRESS_TABLE = ITEMS.register("press_table",  () ->  new BlockItem(ModBlocks.PRESS_TABLE.get(), new Item.Properties().tab(ModCreativeTabs.GS_MOD_TAB)));

}