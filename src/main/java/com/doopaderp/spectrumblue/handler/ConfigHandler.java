package com.doopaderp.spectrumblue.handler;

import com.doopaderp.spectrumblue.helpers.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler
{
    public static Configuration configuration;
    public static String generation = "Generation";
    public static boolean enableGeneration;
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
        configuration.addCustomCategoryComment(generation, "Everything ore generation related.");
        enableGeneration = configuration.get(generation, "enableGeneration", true, "Enable MagnoSeed Ore generation").getBoolean(enableGeneration);
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
