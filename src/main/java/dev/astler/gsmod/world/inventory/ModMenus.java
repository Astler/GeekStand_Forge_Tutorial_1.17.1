package dev.astler.gsmod.world.inventory;

import dev.astler.gsmod.gui.screens.StoneCraftingScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dev.astler.gsmod.GSMod.MODID;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static final RegistryObject<MenuType<StoneCraftingMenu>> STONE_CRAFTING = CONTAINERS.register("stone_crafting", () -> IForgeContainerType.create(StoneCraftingMenu::new));

    public static void registerMenus(IEventBus bus) {
        CONTAINERS.register(bus);
    }
}