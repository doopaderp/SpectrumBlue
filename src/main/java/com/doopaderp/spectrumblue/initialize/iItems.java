package com.doopaderp.spectrumblue.initialize;

import com.doopaderp.spectrumblue.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.doopaderp.spectrumblue.helpers.Reference;

@GameRegistry.ObjectHolder(Reference._MODID)
public class iItems
{
    public static final baseSpectrumBlueItem MagnoDust = new MagnoDust();
    public static final baseSpectrumBlueItem MagnoferricIngot = new MagnoferricIngot();
    public static final baseSpectrumBlueItem MagnoSeed = new MagnoSeed();
    public static String name;

    public static void iInitialize()
    {
        GameRegistry.registerItem(MagnoDust, "MagnoDust");
        GameRegistry.registerItem(MagnoferricIngot, "MagnoferricIngot");
        GameRegistry.registerItem(MagnoSeed, "MagnoSeed");
    }

    public static void iRenders()
    {
        renderItem(MagnoDust, 0);
        renderItem(MagnoferricIngot, 0);
        renderItem(MagnoSeed, 0);
    }

    protected static void renderItem(Item item, int meta)
    {
        name = item.getUnlocalizedName();
        name = name.substring(name.indexOf(":") + 1, name.length());
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(item, meta, new ModelResourceLocation(Reference._PREFIX + name, "inventory"));
    }

}


