package com.github.yoruhinda.maguswand.listener;

import com.github.yoruhinda.maguswand.mana.Mana;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ManaListener implements Listener {
    private Mana mana;

    public ManaListener(Mana mana) {
        this.mana = mana;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        mana.addPlayerMana(event.getPlayer());
        mana.addPlayerManaBar(event.getPlayer());
    }
}
