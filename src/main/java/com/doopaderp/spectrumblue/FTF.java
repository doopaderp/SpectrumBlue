package com.doopaderp.spectrumblue;

import com.doopaderp.spectrumblue.handler.GenerationHandler;
import com.doopaderp.spectrumblue.handler.GuiHandler;
import com.doopaderp.spectrumblue.initialize.bBlocks;
import com.doopaderp.spectrumblue.initialize.iItems;
import com.doopaderp.spectrumblue.proxy.ClientProxy;
import com.doopaderp.spectrumblue.proxy.CommonProxy;
import com.doopaderp.spectrumblue.helpers.Reference;
import com.doopaderp.spectrumblue.handler.ConfigHandler;
import com.doopaderp.spectrumblue.helpers.utility.Logging;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = Reference._MODID,
        version = Reference._VERSION,
        name = Reference._NAME,
        guiFactory = Reference._GUIFACTORY
)
public class FTF {
    @Instance(Reference._MODID)
    public static FTF instance;

    @SidedProxy(
            clientSide = Reference._CLIENTPROXY,
            serverSide = Reference._SERVERPROXY
    )
    public static CommonProxy proxy;

    public FTF() {
    }


    @EventHandler
    public void PreMakeItHappen(FMLPreInitializationEvent event)
    {
        //Logging.info("PreInit");
       ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        //tabSpectrumBlue = new tabSpectrumBlue(CreativeTabs.getNextID(), "tabSpectrumBlue");
        iItems.iInitialize();
        bBlocks.bInitialize();
        GameRegistry.registerWorldGenerator(new GenerationHandler(), 2);

    }

    @EventHandler
    public void MakeItHappen(FMLInitializationEvent event) {
        Logging.info("Init");
        ClientProxy.render();
        proxy.registerTileEntities();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @EventHandler
    public void postMakeItHappen(FMLPostInitializationEvent event) {
        Logging.info("PostInit");
    }
}
