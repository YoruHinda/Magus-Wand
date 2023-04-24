package com.github.yoruhinda.maguswand;

import com.github.yoruhinda.maguswand.commands.WandCommand;
import com.github.yoruhinda.maguswand.listener.ManaListener;
import com.github.yoruhinda.maguswand.listener.SpellListener;
import com.github.yoruhinda.maguswand.mana.Mana;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagusWand extends JavaPlugin {

    private static MagusWand INSTANCE;
    private final Mana mana = new Mana();

    @Override
    public void onEnable() {
        INSTANCE = this;
        getServer().getPluginCommand("maguswand").setExecutor(new WandCommand());
        getServer().getPluginManager().registerEvents(new SpellListener(mana),this);
        getServer().getPluginManager().registerEvents(new ManaListener(mana), this);
        mana.addManaPerSecond();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MagusWand getInstance(){
        return INSTANCE;
    }
}
