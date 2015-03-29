package com.doopaderp.spectrumblue.containers;

import com.doopaderp.spectrumblue.tileentity.tileMachineMagneticOrganizer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

/**
 * Created by Nate on 3/19/2015.
 */
public class containerMagneticOrganizer extends baseContainer
{
    public containerMagneticOrganizer(InventoryPlayer inventory, tileMachineMagneticOrganizer tileEntity) {

        addSlotToContainer(new Slot(tileEntity, tileMachineMagneticOrganizer.INPUT_SLOT, 57, 34));
        addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, tileMachineMagneticOrganizer.OUTPUT_SLOT, 117, 34));
        bindPlayerInventory(inventory, 8, 84);
    }
}
