package dev.astler.gsmod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFood {
    public static final FoodProperties CANDY = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1), 1f).alwaysEat().fast().build();
    public static final FoodProperties CAN_WITH_FOOD = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8F).meat().build();
}
