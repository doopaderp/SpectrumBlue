package com.doopaderp.spectrumblue.block;

import com.doopaderp.spectrumblue.FTF;
import com.doopaderp.spectrumblue.eumerations.GUIs;
import com.doopaderp.spectrumblue.tileentity.tileMachineMagneticOrganizer;
import com.doopaderp.spectrumblue.helpers.utility.Logging;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class MagneticOrganizer extends containerSpectrumBlueBlock
{

    private static boolean invKeep = false;

    public MagneticOrganizer()
    {
        super(Material.rock);
        this.setUnlocalizedName("MagneticOrganizer");
        setStepSound(soundTypeAnvil);
        setBlockBounds(0,0,0,1,1,1);
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {return new tileMachineMagneticOrganizer();}

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            tileMachineMagneticOrganizer tileentity = (tileMachineMagneticOrganizer)worldIn.getTileEntity(pos);

            if (tileentity instanceof tileMachineMagneticOrganizer)
            {
                //tileentity.updateEntity();
                Logging.info("FIRE!");
                playerIn.openGui(FTF.instance, GUIs.MagneticOrganizer.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
                //return new tileEntityMagneticOrganizer(playerIn.inventory, );
            }

            return true;
        }
    }


    /*public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
        if(world.isRemote) {
                Logging.info("FIRE!");
                return true;
        }
        return true;
    }*/


    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!invKeep)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityFurnace)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnace) tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);

            }
        }

        super.breakBlock(worldIn, pos, state);
    }


}
