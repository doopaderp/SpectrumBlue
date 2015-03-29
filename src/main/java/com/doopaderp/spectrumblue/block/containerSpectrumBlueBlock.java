package com.doopaderp.spectrumblue.block;

import com.doopaderp.spectrumblue.tileentity.tileMachineSpectrumBlue;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class containerSpectrumBlueBlock extends directionalSpectrumBlueBlock implements ITileEntityProvider
{

    protected tileMachineSpectrumBlue entity;

    public containerSpectrumBlueBlock(Material material) {
        super(material);
    }


    public boolean canInteractWith(EntityPlayer player) {
        return false;
    }

    public boolean hasTileEntity(int metadata) {

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }


    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
    {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }

}
