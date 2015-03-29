package com.doopaderp.spectrumblue.proxy;

import com.doopaderp.spectrumblue.initialize.bBlocks;
import com.doopaderp.spectrumblue.initialize.iItems;

public class ClientProxy extends CommonProxy {
    public ClientProxy()
    {

    }

    public static void render()
    {
        iItems.iRenders();
        bBlocks.bRenders();
    }
}
