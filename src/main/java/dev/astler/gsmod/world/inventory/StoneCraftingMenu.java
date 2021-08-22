package dev.astler.gsmod.world.inventory;

import dev.astler.gsmod.blocks.ModBlocks;
import dev.astler.gsmod.items.crafting.ModRecipes;
import dev.astler.gsmod.items.crafting.PressRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

import java.util.Objects;
import java.util.Optional;

public class StoneCraftingMenu extends RecipeBookMenu<CraftingContainer> {
    public static final int STONE_PRESS_SLOTS = 5;
    private final CraftingContainer craftSlots = new CraftingContainer(this, STONE_PRESS_SLOTS, 1);
    private final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;

    public StoneCraftingMenu(int p_39353_, Inventory p_39354_) {
        this(p_39353_, p_39354_, ContainerLevelAccess.NULL);
    }

    public StoneCraftingMenu(int p_39356_, Inventory p_39357_, ContainerLevelAccess p_39358_) {
        super(ModMenus.STONE_CRAFTING.get(), p_39356_);
        this.access = p_39358_;
        this.player = p_39357_.player;
        this.addSlot(new ResultSlot(p_39357_.player, this.craftSlots, this.resultSlots, 0, 80, 64));

        for (int j = 0; j < STONE_PRESS_SLOTS; ++j) {
            this.addSlot(new Slot(this.craftSlots, j, 44 + j * 18, 14));
        }

        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(p_39357_, i1 + k * 9 + 9, 8 + i1 * 18, 94 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(p_39357_, l, 8 + l * 18, 152));
        }
    }

    public StoneCraftingMenu(int windowId, Inventory playerInv, FriendlyByteBuf friendlyByteBuf) {
        this(windowId, playerInv, ContainerLevelAccess.NULL);
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu p_150547_, Level pLevel, Player p_150549_, CraftingContainer pCraftingContainer, ResultContainer pResultSlot) {
        if (!pLevel.isClientSide) {
//            List<PressRecipe> list = pLevel.getRecipeManager().getRecipesFor(ModRecipes.PRESS_SHAPED, pCraftingContainer, pLevel);
//
//            System.out.println("total recipes = " + list.size());
//
//            for(int i = 0; i < pCraftingContainer.getContainerSize(); i++) {
//                ItemStack is = pCraftingContainer.getItem(i);
//                System.out.println("is is = " + is.getItem().getRegistryName());
//            }
//
//            if (list.isEmpty()) {
//                pResultSlot.setItem(0, ItemStack.EMPTY);
//            }
//            else {
//                PressRecipe selectedRecipe = list.get(0);
//                ItemStack itemstack = selectedRecipe.assemble(pCraftingContainer);
//                pResultSlot.setRecipeUsed(selectedRecipe);
//                pResultSlot.setItem(0, itemstack);
//            }

            ServerPlayer serverplayer = (ServerPlayer) p_150549_;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<PressRecipe> optional = Objects.requireNonNull(pLevel.getServer()).getRecipeManager().getRecipeFor(ModRecipes.PRESS_RECIPE, pCraftingContainer, pLevel);

            if (optional.isPresent()) {

                PressRecipe craftingrecipe = optional.get();

                System.out.println("recipe " + craftingrecipe.getGroup());

                if (pResultSlot.setRecipeUsed(pLevel, serverplayer, craftingrecipe)) {
                    itemstack = craftingrecipe.assemble(pCraftingContainer);
                }
            } else {
                System.out.println("error?? ");
            }

            pResultSlot.setItem(0, itemstack);
            p_150547_.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(p_150547_.containerId, p_150547_.incrementStateId(), 0, itemstack));
        }
    }

    public void slotsChanged(Container p_39366_) {
        this.access.execute((p_39386_, p_39387_) -> {
            slotChangedCraftingGrid(this, p_39386_, this.player, this.craftSlots, this.resultSlots);
        });
    }

    public void fillCraftSlotsStackedContents(StackedContents p_39374_) {
        this.craftSlots.fillStackedContents(p_39374_);
    }

    public void clearCraftingContent() {
        this.craftSlots.clearContent();
        this.resultSlots.clearContent();
    }

    public boolean recipeMatches(Recipe<? super CraftingContainer> recipe) {
        System.out.println("recipe " + recipe.getGroup());
        return recipe.matches(this.craftSlots, this.player.level);
    }

    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.access.execute((p_39371_, p_39372_) -> {
            this.clearContainer(pPlayer, this.craftSlots);
        });
    }

    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, ModBlocks.PRESS_TABLE.get());
    }

    public ItemStack quickMoveStack(Player pPlayer, int p_39392_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_39392_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_39392_ == 0) {
                this.access.execute((p_39378_, p_39379_) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, pPlayer);
                });
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_39392_ >= 10 && p_39392_ < 46) {
                if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
                    if (p_39392_ < 37) {
                        if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
            if (p_39392_ == 0) {
                pPlayer.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    public boolean canTakeItemForPickAll(ItemStack p_39381_, Slot p_39382_) {
        return p_39382_.container != this.resultSlots && super.canTakeItemForPickAll(p_39381_, p_39382_);
    }

    public int getResultSlotIndex() {
        return 0;
    }

    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    public int getSize() {
        return 10;
    }

    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    public boolean shouldMoveToInventory(int p_150553_) {
        return p_150553_ != this.getResultSlotIndex();
    }
}