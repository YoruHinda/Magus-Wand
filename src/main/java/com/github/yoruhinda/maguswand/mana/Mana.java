package com.github.yoruhinda.maguswand.mana;

import com.github.yoruhinda.maguswand.MagusWand;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Mana {
    private HashMap<Player, Double> mana = new HashMap<>();
    private HashMap<Player, BossBar> manaBar = new HashMap<>();
    private final double MAX_MANA = 100;

    public double getPlayerMana(Player player) {
        return mana.get(player);
    }

    public boolean hasMaxMana(Player player) {
        return getPlayerMana(player) == MAX_MANA;
    }

    public void addMana(Player player, double amount) {
        Double mana = this.mana.get(player);
        if (amount >= MAX_MANA || mana + amount >= MAX_MANA) {
            return;
        }
        this.mana.put(player, mana + amount);
        updateManaBar(player, mana + amount);
    }

    public void removeMana(Player player, double amount) {
        Double mana = this.mana.get(player);
        if (amount < MAX_MANA || mana - amount < MAX_MANA) {
            return;
        }
        this.mana.put(player, mana - amount);
        updateManaBar(player, mana - amount);
    }

    public void addManaPerSecond() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (hasMaxMana(onlinePlayer)) {
                        continue;
                    }
                    addMana(onlinePlayer, 1);
                    updateManaBar(onlinePlayer, 1D);
                }
            }
        }.runTaskTimerAsynchronously(MagusWand.getInstance(), 0, 20L);
    }

    public void addPlayerMana(Player player) {
        if (mana.containsKey(player)) {
            Double actualMana = mana.get(player);
            mana.put(player, actualMana);
        } else {
            mana.put(player, MAX_MANA);
        }
    }

    public void addPlayerManaBar(Player player) {
        BossBar mana;
        if (manaBar.containsKey(player)) {
            mana = manaBar.get(player);
        } else {
            mana = Bukkit.createBossBar("Mana", BarColor.BLUE, BarStyle.SOLID);
        }
        mana.setProgress(this.mana.get(player) / 100);
        mana.setTitle("Mana: " + this.mana.get(player).intValue() + "/100");
        mana.setVisible(true);
        mana.addPlayer(player);
        manaBar.put(player, mana);
    }

    public void updateManaBar(Player player, Double amount){
        BossBar mana = manaBar.get(player);
        mana.setProgress(amount / 100);
        mana.setTitle("Mana: " + amount.intValue() + "/100");
        mana.setVisible(true);
        mana.addPlayer(player);
    }
}
