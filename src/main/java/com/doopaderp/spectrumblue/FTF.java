package com.doopaderp.spectrumblue;

import com.doopaderp.spectrumblue.reference.Reference;
import com.doopaderp.spectrumblue.handler.ConfigHandler;
import com.doopaderp.spectrumblue.proxy.IProxy;
import com.doopaderp.spectrumblue.utility.Logging;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
    public static IProxy proxy;

    public FTF() {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Logging.info("PreInit");
       ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Logging.info("Init");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //Logging.info("PostInit");
    }
}
