package dev.astler.gsmod.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CanFoodItem extends Item {
    public CanFoodItem(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pItemStack, Level pLevel, LivingEntity pEntity) {
        ItemStack itemstack = super.finishUsingItem(pItemStack, pLevel, pEntity);
        return pEntity instanceof Player && ((Player)pEntity).getAbilities().instabuild ? itemstack : new ItemStack(ModItems.CAN.get());
    }
}