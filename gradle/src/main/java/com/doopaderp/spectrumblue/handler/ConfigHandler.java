package main.java.com.doopaderp.spectrumblue.handler;

import com.doopaderp.spectrumblue.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler
{
    public static Configuration configuration;
    public static void init(File configFile)
    {
        if (configuration == null)
            {
                configuration = new Configuration(configFile);
                loadConfiguration();
            }
    }

    private static void loadConfiguration()
    {
        int testValue = configuration.getInt("Blue Seed Ore", "Ore Generation" ,1 ,0 ,9 , "");
        if (configuration.hasChanged())
        {
                configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference._MODID))
        {
           loadConfiguration();
        }
    }
}
