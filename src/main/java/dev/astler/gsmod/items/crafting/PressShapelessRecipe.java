package dev.astler.gsmod.items.crafting;

import com.google.gson.*;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class PressShapelessRecipe implements PressRecipe {

    final NonNullList<Ingredient> mRecipeIngredients;
    final ItemStack mResultItemStack;
    private final ResourceLocation id;
    final String group;
    private final boolean isSimple;

    public PressShapelessRecipe(ResourceLocation pResourceLocation, String pGroup, ItemStack pResultItem, NonNullList<Ingredient> pIngredients) {
        this.id = pResourceLocation;
        this.group = pGroup;
        this.mRecipeIngredients = pIngredients;
        this.mResultItemStack = pResultItem;
        this.isSimple = pIngredients.stream().allMatch(Ingredient::isSimple);
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PRESS_SHAPELESS_SERIALIZER.get();
    }

    public String getGroup() {
        return this.group;
    }

    public ItemStack getResultItem() {
        return this.mResultItemStack;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.mRecipeIngredients;
    }

    public boolean matches(CraftingContainer pCraftingContainer, Level pLevel) {
        StackedContents stackedcontents = new StackedContents();
        List<ItemStack> inputs = new ArrayList<>();

        int i = 0;

        for(int j = 0; j < pCraftingContainer.getContainerSize(); ++j) {
            ItemStack nItemStack = pCraftingContainer.getItem(j);

            if (!nItemStack.isEmpty()) {
                ++i;
                if (isSimple)
                    stackedcontents.accountStack(nItemStack, 1);
                else inputs.add(nItemStack);
            }
        }

        return i == this.mRecipeIngredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.mRecipeIngredients) != null);
    }

    public ItemStack assemble(CraftingContainer pCraftingContainer) {
        return this.mResultItemStack.copy();
    }

    public boolean canCraftInDimensions(int p_44252_, int p_44253_) {
        return p_44252_ * p_44253_ >= this.mRecipeIngredients.size();
    }

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<PressShapelessRecipe> {

        public PressShapelessRecipe fromJson(ResourceLocation pResourceLocation, JsonObject pJsonObject) {
            System.out.println(pJsonObject.toString());

            String nGroupString = GsonHelper.getAsString(pJsonObject, "group", "");

            NonNullList<Ingredient> nIngredientsList = itemsFromJson(GsonHelper.getAsJsonArray(pJsonObject, "ingredients"));
            if (nIngredientsList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nIngredientsList.size() > PressShapedRecipe.MAX_WIDTH * PressShapedRecipe.MAX_HEIGHT) {
                throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + (PressShapedRecipe.MAX_WIDTH * PressShapedRecipe.MAX_HEIGHT));
            } else {
                ItemStack nResultItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJsonObject, "result"));
                return new PressShapelessRecipe(pResourceLocation, nGroupString, nResultItem, nIngredientsList);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray p_44276_) {
            NonNullList<Ingredient> nIngredientsList = NonNullList.create();

            for(int i = 0; i < p_44276_.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(p_44276_.get(i));
                if (!ingredient.isEmpty()) {
                    nIngredientsList.add(ingredient);
                }
            }

            return nIngredientsList;
        }

        public PressShapelessRecipe fromNetwork(ResourceLocation pResourceLocation, FriendlyByteBuf p_44294_) {
            String s = p_44294_.readUtf();
            int i = p_44294_.readVarInt();
            NonNullList<Ingredient> nIngredientsList = NonNullList.withSize(i, Ingredient.EMPTY);

            for(int j = 0; j < nIngredientsList.size(); ++j) {
                nIngredientsList.set(j, Ingredient.fromNetwork(p_44294_));
            }

            ItemStack itemstack = p_44294_.readItem();
            return new PressShapelessRecipe(pResourceLocation, s, itemstack, nIngredientsList);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pByteBuffer, PressShapelessRecipe pShapelessRecipe) {
            pByteBuffer.writeUtf(pShapelessRecipe.group);
            pByteBuffer.writeVarInt(pShapelessRecipe.mRecipeIngredients.size());

            for(Ingredient ingredient : pShapelessRecipe.mRecipeIngredients) {
                ingredient.toNetwork(pByteBuffer);
            }

            pByteBuffer.writeItem(pShapelessRecipe.mResultItemStack);
        }

    }
}
