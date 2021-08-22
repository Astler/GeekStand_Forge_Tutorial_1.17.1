package dev.astler.gsmod.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab GS_MOD_TAB = new CreativeModeTab("gs_tab") {
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.STONE_STICK.get());
        }
    };
}