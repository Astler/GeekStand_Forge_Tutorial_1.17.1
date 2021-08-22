package dev.astler.gsmod.items.crafting;

import net.minecraft.core.Registry;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nonnull;
import java.util.Objects;

public interface PressRecipe extends Recipe<CraftingContainer> {
    @Nonnull
    @Override
    default RecipeType<?> getType() {
        return Objects.requireNonNull(Registry.RECIPE_TYPE.get(ModRecipes.PRESS_TYPE_ID));
    }
}
