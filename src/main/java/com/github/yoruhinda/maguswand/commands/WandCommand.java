package com.github.yoruhinda.maguswand.commands;

import com.github.yoruhinda.maguswand.wand.WandItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
                ItemStack wand = WandItem.getWandItem();
                if(player.getInventory().contains(wand)){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3You already have wand!"));
                }else {
                    player.getInventory().addItem(wand);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3You already have wand!"));
                }
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bMagusWand by YoruHinda"));
            return true;
        }
        return false;
    }

    private String helpList(){
        return ChatColor.translateAlternateColorCodes('&', "&b----- MagusWand -----\n") +
                ChatColor.translateAlternateColorCodes('&', "&5/maguswand wand &7- &6Give Wand\n") +
                ChatColor.translateAlternateColorCodes('&', "&b---------------------");
    }
}
