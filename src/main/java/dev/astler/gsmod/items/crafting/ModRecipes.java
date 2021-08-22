package dev.astler.gsmod.items.crafting;

import dev.astler.gsmod.GSMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static dev.astler.gsmod.GSMod.MODID;

public class ModRecipes {
    public static final ResourceLocation PRESS_TYPE_ID = new ResourceLocation(GSMod.MODID, "press");

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);
    public static final RecipeType<PressRecipe> PRESS_RECIPE = ModRecipeType.registerType(PRESS_TYPE_ID);

    public static final RegistryObject<RecipeSerializer<PressShapedRecipe>> PRESS_SHAPED_SERIALIZER = RECIPES_SERIALIZER.register("press_shaped", PressShapedRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<PressShapelessRecipe>> PRESS_SHAPELESS_SERIALIZER = RECIPES_SERIALIZER.register("press_shapeless", PressShapelessRecipe.Serializer::new);

    private static class ModRecipeType<T extends Recipe<?>> implements RecipeType<T> {

        @Override
        public String toString() {
            return Objects.requireNonNull(Registry.RECIPE_TYPE.getKey(this)).toString();
        }

        private static <T extends RecipeType> T registerType(ResourceLocation recipeTypeId) {
            return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new ModRecipeType<>());
        }
    }
}
