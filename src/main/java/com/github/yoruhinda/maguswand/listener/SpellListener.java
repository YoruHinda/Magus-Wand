package com.github.yoruhinda.maguswand.listener;

import com.github.yoruhinda.maguswand.MagusWand;
import com.github.yoruhinda.maguswand.mana.Mana;
import com.github.yoruhinda.maguswand.wand.WandItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpellListener implements Listener {
    private Mana mana;
    private MagusWand magusWand = MagusWand.getInstance();

    public SpellListener(Mana mana) {
        this.mana = mana;
    }

    @EventHandler
    public void spellEvent(PlayerInteractEvent event) {
        ItemStack wand = WandItem.getWandItem();
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().equals(wand)) {
            if (event.getAction() == Action.LEFT_CLICK_AIR && !(player.isSneaking())) {
                ProjectileSpell(player);
            }

            if (event.getAction() == Action.RIGHT_CLICK_AIR && !(player.isSneaking())) {
                explosionSpell(player);
            }

            if (event.getAction() == Action.RIGHT_CLICK_AIR && player.isSneaking()) {
                protegiumSpell(player);
            }

            if (event.getAction() == Action.LEFT_CLICK_AIR && player.isSneaking()) {
                illuminationSpell(player);
            }
        }
    }

    public void ProjectileSpell(Player player) {
        if (mana.getPlayerMana(player) >= 5) {
            mana.removeMana(player, 5);
            Location location = player.getLocation();
            Vector direction = location.getDirection().normalize();
            new BukkitRunnable() {
                double t = 0;

                @Override
                public void run() {
                    t = t + 0.5;
                    double x = direction.getX() * t;
                    double y = direction.getY() * t + 1.5;
                    double z = direction.getZ() * t;
                    location.add(x, y, z);
                    player.getWorld().spawnParticle(Particle.SPELL_WITCH, location, 0, 0, 0, 0, 1);
                    for (Entity entity : location.getChunk().getEntities()) {
                        if (entity.getLocation().distance(location) < 1.0) {
                            if (!(entity.equals(player))) {
                                if (entity instanceof LivingEntity) {
                                    LivingEntity livingEntity = (LivingEntity) entity;
                                    livingEntity.setHealth(livingEntity.getHealth() - 1);
                                }
                            }
                        }
                    }
                    location.subtract(x, y, z);
                    if (t > 30) {
                        this.cancel();
                    }
                }
            }.runTaskTimer(magusWand, 0, 1);
        } else if (mana.getPlayerMana(player) < 5) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You don't have mana to cast this spell!"));
        }
    }

    public void explosionSpell(Player player) {
        if (mana.getPlayerMana(player) >= 30) {
            mana.removeMana(player, 30);
            Location location = player.getLocation();
            Vector direction = location.getDirection().normalize();
            new BukkitRunnable() {
                double t = 0;

                @Override
                public void run() {
                    t = t + 0.5;
                    double x = direction.getX() * t;
                    double y = direction.getY() * t + 1.5;
                    double z = direction.getZ() * t;
                    location.add(x, y, z);
                    player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, location,0, 0, 0, 0, 1);
                    if (location.getBlock().getType().isSolid() && (!(location.getBlock().getType() == Material.AIR))) {
                        location.getWorld().createExplosion(location.getBlock().getLocation(), 2);
                    }
                    location.subtract(x, y, z);
                    if (t > 30) {
                        this.cancel();
                    }
                }
            }.runTaskTimer(magusWand, 0, 1);
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You don't have mana to cast this spell!"));
        }
    }

    public void protegiumSpell(Player player) {
        if (mana.getPlayerMana(player) >= 50) {
            mana.removeMana(player, 50);
            Location location = player.getLocation();
            new BukkitRunnable() {
                double phi = 0;

                @Override
                public void run() {
                    phi += Math.PI / 10;
                    for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 40) {
                        double r = 1.5;
                        double x = r * Math.cos(theta) * Math.sin(phi);
                        double y = r * Math.cos(phi) + 1.5;
                        double z = r * Math.sin(theta) * Math.sin(phi);
                        location.add(x, y, z);
                        player.getWorld().spawnParticle(Particle.PORTAL, location, 0, 0, 0, 0, 1);
                        location.subtract(x, y, z);
                    }
                    if (phi > Math.PI) {
                        this.cancel();
                    }
                }
            }.runTaskTimer(magusWand, 0, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1000, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000, 1));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You don't have mana to cast this spell!"));
        }
    }

    public void illuminationSpell(Player player) {
        if(mana.getPlayerMana(player) >= 20){
            mana.removeMana(player,20);
            Location location = player.getLocation();
            new BukkitRunnable(){
                double r = 2;
                double t = 0;
                @Override
                public void run() {
                    t = t + Math.PI/16;
                    double x = r*Math.cos(t);
                    double y = Math.sin(t);
                    double z = r*Math.sin(t);
                    location.add(x,y,z);
                    player.getWorld().spawnParticle(Particle.FLAME ,location,0,0,0,0,1);
                    location.subtract(x,y,z);
                    if(t > Math.PI*8){
                        this.cancel();
                    }
                }
            }.runTaskTimer(magusWand,0,1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000,1));
        }else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You don't have mana to cast this spell!"));
        }
    }

}
