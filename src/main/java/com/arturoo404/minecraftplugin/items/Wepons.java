package com.arturoo404.minecraftplugin.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Wepons {

    public ItemStack magicStick(){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName("§6Magic Stick of Pain");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§5Right click to");
        lore.add("§5generate pain path");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        itemStack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);

        return itemStack;
    }
}
