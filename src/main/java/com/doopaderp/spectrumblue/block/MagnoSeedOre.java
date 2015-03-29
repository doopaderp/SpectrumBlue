package com.doopaderp.spectrumblue.block;

import com.doopaderp.spectrumblue.initialize.iItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class MagnoSeedOre extends baseSpectrumBlueBlock
{
    public MagnoSeedOre()
    {
        super(Material.rock);
        this.setUnlocalizedName("MagnoSeedOre");
    }

    // Drops the item you return here when broken.
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return iItems.MagnoSeed;
    }

    // Drops the amount of items you get here.
    @Override
    public int quantityDropped(Random random) {
        return 1;
    }
}
