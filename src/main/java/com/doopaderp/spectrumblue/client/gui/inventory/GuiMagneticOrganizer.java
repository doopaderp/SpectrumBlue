package com.doopaderp.spectrumblue.client.gui.inventory;

import com.doopaderp.spectrumblue.containers.containerMagneticOrganizer;
import com.doopaderp.spectrumblue.helpers.GuiHelper;
import com.doopaderp.spectrumblue.helpers.Reference;
import com.doopaderp.spectrumblue.tileentity.tileMachineMagneticOrganizer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiMagneticOrganizer extends GuiContainer
{

    private tileMachineMagneticOrganizer tile;
    private ResourceLocation backgroundimage = new ResourceLocation(Reference._PREFIX + "textures/gui/GuiMagneticOrganizer.png");

    public GuiMagneticOrganizer(InventoryPlayer inventory, tileMachineMagneticOrganizer tileEntity) {
        super(new containerMagneticOrganizer(inventory, tileEntity));
       this.tile = tileEntity;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRendererObj.drawString("Magnetic Organizer", xSize/2 - (fontRendererObj.getStringWidth("Magnetic Organizer")/2), 5, 5210752);
        //Logging.info(width + " " + xSize);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(backgroundimage);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        //RF Energy bar
        int heightRF = tile.getEnergyStored(null) * 52 / tile.getMaxEnergyStored(null);
        drawTexturedModalRect(x + 18, y + 16, 176, 69, 16, heightRF);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(x + 18, y + 68, 0, 0.6875F, 0.26953125F);
        worldrenderer.addVertexWithUV(x + 34, y + 68, 0, 0.75F, 0.26953125F);
        worldrenderer.addVertexWithUV(x + 34, y + 68 - heightRF, 0, 0.75F, (float) (69 - heightRF) / 256);
        worldrenderer.addVertexWithUV(x + 18, y + 68 - heightRF, 0, 0.6875F, (float) (69 - heightRF) / 256);
        tessellator.draw();

        //Progress Arrow
        int arrow = tile.currentProcessTime != 0 ? tile.currentProcessTime * 24 / 150 : 0;
        this.drawTexturedModalRect(x + 79, y + 34, 176, 0, arrow + 1, 16);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        super.drawScreen(mouseX, mouseY, par3);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        if (GuiHelper.isInBounds(mouseX, mouseY, x + 18, y + 16, x + 34, y + 68)) {
            List<String> toolTip = new ArrayList();
            toolTip.add(GuiHelper.GuiColor.YELLOW + "Energy");
            toolTip.add(tile.getEnergyStored(null) + "/" + tile.getMaxEnergyStored(null) + GuiHelper.GuiColor.RED + "RF");
            drawHoveringText(toolTip, mouseX, mouseY);
        }
    }
}
