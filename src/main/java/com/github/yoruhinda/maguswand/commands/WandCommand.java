package com.github.yoruhinda.maguswand.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 1) {
                player.sendMessage(helpList());
                return true;
            }
            if (args[0].equalsIgnoreCase("wand")) {
                ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
                ItemMeta itemMeta = wand.getItemMeta();
                itemMeta.addEnchant(Enchantment.DURABILITY, 100, true);
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Wand"));
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.translateAlternateColorCodes('&', "&9PowerFull Wand"));
                itemMeta.setLore(lore);
                wand.setItemMeta(itemMeta);
                if(player.getInventory().contains(wand)){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3You already have wand!"));
                }else {
                    player.getInventory().addItem(wand);
                }
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----- MagusWand -----"));
            return true;
        }
        return false;
    }

    private String helpList(){
        StringBuilder helpList = new StringBuilder();
        helpList.append(ChatColor.translateAlternateColorCodes('&', "&b----- MagusWand -----"));
        helpList.append(ChatColor.translateAlternateColorCodes('&', "&5/maguswand wand &7- &6Give Wand"));
        helpList.append(ChatColor.translateAlternateColorCodes('&', "&b---------------------"));
        return helpList.toString();
    }
}
