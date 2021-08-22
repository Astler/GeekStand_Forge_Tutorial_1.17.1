package dev.astler.gsmod;

import dev.astler.gsmod.gui.screens.StoneCraftingScreen;
import dev.astler.gsmod.world.ModFeatures;
import dev.astler.gsmod.world.inventory.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static dev.astler.gsmod.GSMod.MODID;
import static dev.astler.gsmod.blocks.ModBlocks.BLOCKS;
import static dev.astler.gsmod.items.ModItems.ITEMS;
import static dev.astler.gsmod.items.crafting.ModRecipes.RECIPES_SERIALIZER;

@Mod(MODID)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class GSMod {
    public static final String MODID = "gsmod";

    public GSMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        ITEMS.register(bus);
        BLOCKS.register(bus);
        RECIPES_SERIALIZER.register(bus);

        ModMenus.registerMenus(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setup(final FMLCommonSetupEvent event) {
        ModFeatures.registerFeatures();
        MenuScreens.register(ModMenus.STONE_CRAFTING.get(), StoneCraftingScreen::new);
    }
}

