package com.arturoo404.minecraftplugin.listener;

import com.arturoo404.minecraftplugin.structures.BattleTower;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Random;

public class ChunkLoad implements Listener {

    @EventHandler
    public void chunkLoad(ChunkLoadEvent event){
        if (event.isNewChunk()){
            //&& event.getChunk().getChunkSnapshot().getBiome(8, 62, 8).equals(Biome.PLAINS)
            int random = new Random()
                    .ints(0, 30)
                    .findFirst()
                    .getAsInt();
            if (random == 5){
                System.out.println("WORK");
                Chunk chunk = event.getChunk();
                Block block = chunk.getBlock(0, 80, 0);
                BattleTower battleTower = new BattleTower(block.getX(), block.getY(), block.getZ());
                battleTower.generateBattleTower();
            }
        }
    }
}
