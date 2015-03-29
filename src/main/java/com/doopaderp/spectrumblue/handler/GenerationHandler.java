package com.doopaderp.spectrumblue.handler;

import com.doopaderp.spectrumblue.handler.ConfigHandler;
import com.doopaderp.spectrumblue.initialize.bBlocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GenerationHandler implements IWorldGenerator {

    // Which dimension to generate in by dimension ID (Nether -1, Overworld 0, End 1, etc)
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimensionId()) {
            case -1:
                break;
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                break;
        }
    }

    //Generation
    private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < 16; k++)
        {
            int firstBlockXCoord = chunkX + rand.nextInt(16);
            int firstBlockZCoord = chunkZ + rand.nextInt(16);
            int posY = rand.nextInt(20); //Spawn Level
            BlockPos Pos = new BlockPos(firstBlockXCoord, posY, firstBlockZCoord);
            if (ConfigHandler.enableGeneration)
                (new WorldGenMinable(bBlocks.MagnoSeedOre.getDefaultState(), 1)).generate(world, rand, Pos);
        }
    }
}