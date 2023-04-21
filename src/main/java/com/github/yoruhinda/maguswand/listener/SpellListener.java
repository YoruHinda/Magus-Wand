package com.github.yoruhinda.maguswand.listener;

import com.github.yoruhinda.maguswand.mana.Mana;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpellListener implements Listener {
    private Mana mana;

    public SpellListener(Mana mana){
        this.mana = mana;
    }

    @EventHandler
    public void spellEvent(PlayerInteractEvent event){

    }
}
