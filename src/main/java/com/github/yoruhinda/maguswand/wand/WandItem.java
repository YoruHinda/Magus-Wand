package com.github.yoruhinda.maguswand.wand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WandItem {

    public static ItemStack getWandItem(){
        ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta itemMeta = wand.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Wand"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9PowerFull Wand"));
        itemMeta.setLore(lore);
        wand.setItemMeta(itemMeta);
        return wand;
    }
}
