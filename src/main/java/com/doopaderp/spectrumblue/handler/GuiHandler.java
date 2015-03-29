package com.doopaderp.spectrumblue.handler;

import com.doopaderp.spectrumblue.client.gui.inventory.GuiMagneticOrganizer;
import com.doopaderp.spectrumblue.containers.containerMagneticOrganizer;
import com.doopaderp.spectrumblue.tileentity.tileMachineMagneticOrganizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int MAGNETIC_ORGANIZER_GUI_ID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID) {
            case MAGNETIC_ORGANIZER_GUI_ID:
                return new containerMagneticOrganizer(player.inventory, (tileMachineMagneticOrganizer) world.getTileEntity(new BlockPos(x, y, z)));//containerMagneticOrganizer(player.inventory, (tileMachineMagneticOrganizer) world.getTileEntity(new BlockPos(x, y, z)));

        }
        return null;
    }


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case MAGNETIC_ORGANIZER_GUI_ID:
                return new GuiMagneticOrganizer(player.inventory, (tileMachineMagneticOrganizer) world.getTileEntity(new BlockPos(x, y, z)));

        }
        return null;
    }

}