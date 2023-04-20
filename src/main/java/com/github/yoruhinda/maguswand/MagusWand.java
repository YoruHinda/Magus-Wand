package com.github.yoruhinda.maguswand;

import com.github.yoruhinda.maguswand.commands.WandCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagusWand extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginCommand("maguswand").setExecutor(new WandCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
