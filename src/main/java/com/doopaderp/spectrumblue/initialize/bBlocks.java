package com.doopaderp.spectrumblue.initialize;

import com.doopaderp.spectrumblue.block.*;
import com.doopaderp.spectrumblue.block.baseSpectrumBlueBlock;
import com.doopaderp.spectrumblue.helpers.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference._MODID)
public class bBlocks
{
    public static final baseSpectrumBlueBlock MagnoferricBlock = new MagnoferricBlock();
    public static final containerSpectrumBlueBlock MagneticOrganizer = new MagneticOrganizer();
    public static final baseSpectrumBlueBlock MagnoSeedOre = new MagnoSeedOre();
    public static String name;

    public static void bInitialize()
    {
        GameRegistry.registerBlock(MagnoferricBlock, "MagnoferricBlock");
        GameRegistry.registerBlock(MagneticOrganizer, "MagneticOrganizer");
        GameRegistry.registerBlock(MagnoSeedOre, "MagnoSeedOre");
    }

    public static void bRenders()
    {
        renderBlock(MagnoferricBlock, 0);
        renderBlock(MagneticOrganizer, 0);
        renderBlock(MagnoSeedOre, 0);
    }

    protected static void renderBlock(Block block, int meta)
    {
        name = block.getUnlocalizedName();
        name = name.substring(name.indexOf(":") + 1, name.length());
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference._PREFIX + name, "inventory"));
    }

}