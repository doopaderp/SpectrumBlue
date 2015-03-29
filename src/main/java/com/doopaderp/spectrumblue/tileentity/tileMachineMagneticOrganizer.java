package com.doopaderp.spectrumblue.tileentity;

import cofh.api.energy.EnergyStorage;
import com.doopaderp.spectrumblue.helpers.MagneticOrganizerOutput;
import com.doopaderp.spectrumblue.helpers.utility.Logging;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

public class tileMachineMagneticOrganizer extends tileMachineSpectrumBlue implements IUpdatePlayerListBox
{
    public static final String publicName = "tileMachineMagneticOrganizer";

    public int currentProcessTime;
    private ItemStack input;
    private ItemStack output;

    private static final int RF_TICK = 20;
    public static final int TOTAL_PROCESS_TIME = 150;
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;


    public tileMachineMagneticOrganizer()
    {
        super(publicName);
        energyRF = new EnergyStorage(1000000);
        currentProcessTime = 0;
        inventory = new tileInventory(2);

    }

    @Override
    public void update() {
        energyRF.setEnergyStored(1000000);
        if (!this.hasWorldObj()) return;
        World world = this.getWorld();
        if (world.isRemote) return;

        if (currentProcessTime > 0 || canWork()) {
            if (currentProcessTime == 0) {
                input = inventory.getStackInSlot(INPUT_SLOT);
                currentProcessTime = 1;
            }
            if (currentProcessTime > 0 && currentProcessTime < TOTAL_PROCESS_TIME) {
                if (inventory.getStackInSlot(INPUT_SLOT) == null || !inventory.getStackInSlot(INPUT_SLOT).isItemEqual(input)) {
                    doReset();
                    world.markBlockForUpdate(this.pos);
                    return;
                }
                if (energyRF.getEnergyStored() >= RF_TICK ) {
                    energyRF.modifyEnergyStored(-RF_TICK);
                    ++currentProcessTime;
                }
            }
            if (currentProcessTime >= TOTAL_PROCESS_TIME) {
                inventory.modifyStack(INPUT_SLOT, -1);
                if (inventory.getStackInSlot(OUTPUT_SLOT) == null)
                    inventory.setStackInSlot(output, OUTPUT_SLOT);
                else inventory.getStackInSlot(OUTPUT_SLOT).stackSize += output.stackSize;
                doReset();
            }
            world.markBlockForUpdate(this.pos);
        }
    }


    public boolean canWork()
    {
        if (inventory.getStackInSlot(INPUT_SLOT) == null) return false;
        output = MagneticOrganizerOutput.getOutput(inventory.getStackInSlot(INPUT_SLOT));
        Logging.info(output);
        return output != null && !(inventory.getStackInSlot(OUTPUT_SLOT) != null && !inventory.getStackInSlot(OUTPUT_SLOT).isItemEqual(output) && inventory.getStackInSlot(OUTPUT_SLOT).stackSize + output.stackSize < inventory.getStackInSlot(OUTPUT_SLOT).getMaxStackSize());
    }

    public void doReset() {
        currentProcessTime = 0;
        input = null;
        output = null;
    }

    /*******************************************************************************************************************
     ************************************** Energy Functions ***********************************************************
     *******************************************************************************************************************/

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        int actual = energyRF.receiveEnergy(maxReceive, simulate);
        if (actual > 0) this.getWorld().markBlockForUpdate(this.pos);
        return actual;
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        return 0;
    }

    /*******************************************************************************************************************
     ************************************** Inventory Functions ********************************************************
     *******************************************************************************************************************/

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[] {0,1};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return index == 0;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return index == 1;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return currentProcessTime;
            default:
                return 0;
        }

    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                currentProcessTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            inventory.setStackInSlot(null, i);
        }
    }

    /*******************************************************************************************************************
     **************************************** Tile Functions ***********************************************************
     *******************************************************************************************************************/

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energyRF.readFromNBT(tag);
        inventory.readFromNBT(tag, this);
        NBTTagList itemsTag = tag.getTagList("Stacks", 10);
        for (int i = 0; i < itemsTag.tagCount(); i++)
        {
            NBTTagCompound nbtTagCompound1 = itemsTag.getCompoundTagAt(i);
            NBTBase nbt = nbtTagCompound1.getTag("Stack");
            int j;
            if ((nbt instanceof NBTTagByte)) {
                j = nbtTagCompound1.getByte("Stack") & 0xFF;
            } else {
                j = nbtTagCompound1.getShort("Stack");
            }
            switch (j) {
                case 0:
                    input = ItemStack.loadItemStackFromNBT(nbtTagCompound1);
                    break;
                case 1:
                    output = ItemStack.loadItemStackFromNBT(nbtTagCompound1);
                    break;
            }
        }
        currentProcessTime = tag.getInteger("CurrentProcessTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        energyRF.writeToNBT(tag);
        inventory.writeToNBT(tag);
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < 2; i++) {
            NBTTagCompound nbtTagCompound1 = new NBTTagCompound();
            nbtTagCompound1.setShort("Stack", (short)i);
            switch (i)
            {
                case 0:
                    if (input != null)
                        input.writeToNBT(nbtTagCompound1);
                    break;
                case 1:
                    if (output != null)
                        output.writeToNBT(nbtTagCompound1);
                    break;
            }
            nbtTagList.appendTag(nbtTagCompound1);
        }
        tag.setTag("Stacks", nbtTagList);
        tag.setInteger("CurrentProcessTime", currentProcessTime);
    }
   /* public void updateEntity() {
        if(!worldObj.isRemote) {
            tick++;
            Logging.warn(tick);
            if(tick == 5) {

                Logging.warn("LOL");
                tick = 0;
            }
        }
    }*/
}
