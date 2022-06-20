package com.arturoo404.minecraftplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Money {

    public ItemStack createCoin(){
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName("§6Coin");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§5Old coin created by dwarfs");
        lore.add("§5in far darkness mountain.");
        lore.add("§eCurrent value of this coin is: 1");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack createIngot(){
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§eHeavy Ingot");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§5In the old of history bandits");
        lore.add("§5robbed these ingot from");
        lore.add("§5traders transport.");
        lore.add("§eCurrent value of this ingot is: 10");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
