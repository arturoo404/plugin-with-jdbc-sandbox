package com.arturoo404.minecraftplugin.listener;

import com.arturoo404.minecraftplugin.items.Money;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class BlockEvent implements Listener {

    @EventHandler
    public void blockDropMoney(BlockBreakEvent event){
        Block block = event.getBlock();
        if (block.getType().equals(Material.DIAMOND_ORE)){
            int random = new Random()
                    .ints(0, 10)
                    .findFirst()
                    .getAsInt();
            if (random == 5){
                block.getWorld().dropItem(block.getLocation(), new Money().createIngot());
            }
            block.getWorld().dropItem(block.getLocation(), new Money().createCoin());
        }
    }
}
