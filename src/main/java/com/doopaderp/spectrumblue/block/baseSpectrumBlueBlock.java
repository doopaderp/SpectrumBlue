package com.doopaderp.spectrumblue.block;

import com.doopaderp.spectrumblue.creativetab.tabSpectrumBlue;
import com.doopaderp.spectrumblue.helpers.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class baseSpectrumBlueBlock extends Block
{
    private final String name  = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

    public baseSpectrumBlueBlock(Material material)
    {
        super(material);
        this.setCreativeTab(tabSpectrumBlue.tab);
        setHardness(2);
        setStepSound(soundTypeStone);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference._PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public String getName()
    {
        return name;
    }

}
