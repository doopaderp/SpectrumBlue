package com.doopaderp.spectrumblue.client.gui;

import com.doopaderp.spectrumblue.handler.ConfigHandler;
import com.doopaderp.spectrumblue.helpers.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
public class GuiConfigClass extends GuiConfig
{
    public GuiConfigClass(GuiScreen guiScreen)
    {
        super(guiScreen,
                new ConfigElement(ConfigHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference._MODID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
    }
}
