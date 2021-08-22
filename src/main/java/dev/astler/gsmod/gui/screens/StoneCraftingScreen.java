package dev.astler.gsmod.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.astler.gsmod.GSMod;
import dev.astler.gsmod.world.inventory.StoneCraftingMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StoneCraftingScreen extends AbstractContainerScreen<StoneCraftingMenu> {
    private static final ResourceLocation CRAFTING_TABLE_LOCATION = new ResourceLocation(GSMod.MODID, "textures/gui/container/press.png");

    public StoneCraftingScreen(StoneCraftingMenu p_98448_, Inventory p_98449_, Component p_98450_) {
        super(p_98448_, p_98449_, p_98450_);
        this.imageHeight = 175;
        this.titleLabelX = 10;
        this.titleLabelY = 40;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(PoseStack p_98807_, int p_98808_, int p_98809_, float p_98810_) {
        this.renderBackground(p_98807_);
        super.render(p_98807_, p_98808_, p_98809_, p_98810_);
        this.renderTooltip(p_98807_, p_98808_, p_98809_);
    }

    protected void renderBg(PoseStack p_98802_, float p_98803_, int p_98804_, int p_98805_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, CRAFTING_TABLE_LOCATION);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_98802_, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}