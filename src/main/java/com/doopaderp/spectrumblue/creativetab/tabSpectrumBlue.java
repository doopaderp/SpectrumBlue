package com.doopaderp.spectrumblue.creativetab;

import com.doopaderp.spectrumblue.initialize.iItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class tabSpectrumBlue
{
    public static final CreativeTabs tab = new CreativeTabs("tabSpectrumBlue") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return iItems.MagnoSeed;
        }
    };

}

