package com.doopaderp.spectrumblue.item;


import com.doopaderp.spectrumblue.creativetab.tabSpectrumBlue;
import com.doopaderp.spectrumblue.helpers.Reference;
import com.doopaderp.spectrumblue.helpers.utility.Logging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class baseSpectrumBlueItem extends Item
{


    private final String name  = getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    private int _ID;

    public baseSpectrumBlueItem(int ID)
    {
        super();
        this.maxStackSize = 64;
        this.setCreativeTab(tabSpectrumBlue.tab);
        _ID = ID;
    }
    @Override
    public String getUnlocalizedName()
    {
       return String.format("item.%s%s", Reference._PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference._PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));

    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }


    public String getName()
    {
        return name;
    }


    public boolean onDroppedByPlayer(ItemStack itemstack, EntityPlayer player)
    {
        Logging.info("Item Dropped");
        return true;
    }

    public int getOrdinalID(){
        return _ID;
    }
}
