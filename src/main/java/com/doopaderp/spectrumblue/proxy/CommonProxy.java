package com.doopaderp.spectrumblue.proxy;

import com.doopaderp.spectrumblue.tileentity.tileMachineMagneticOrganizer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(tileMachineMagneticOrganizer.class, tileMachineMagneticOrganizer.publicName);
    }
}
