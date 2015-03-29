package com.doopaderp.spectrumblue.helpers;

import com.doopaderp.spectrumblue.helpers.utility.Logging;
import com.doopaderp.spectrumblue.item.MagnoDust;
import com.doopaderp.spectrumblue.item.MagnoferricIngot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MagneticOrganizerOutput
{
    private static ItemStack itemSend;
    private static MagnoferricIngot magnoferricingot = new MagnoferricIngot();
    private static MagnoDust magnodust = new MagnoDust();
    public static ItemStack getOutput(ItemStack item)
    {
        Logging.info(item.getUnlocalizedName());
        switch (item.getUnlocalizedName())
        {
            case "item.spectrumblue:MagnoDust": itemSend = new ItemStack(magnoferricingot);
            case "item.spectrumblue:MagnoSeed": itemSend = new ItemStack(magnodust.getThis());
            case "item.spectrumblue:MagnoferricIngot": itemSend = new ItemStack(Items.diamond, 2);
        }

        Logging.info(itemSend.getUnlocalizedName());
        return itemSend;
    }
}
