package com.brandan.newguns;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("hoe").setExecutor(new hoeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.hasItem()) {
            if (e.getItem().getType() == Material.DIAMOND_HOE) {
                player.launchProjectile(Snowball.class);
                player.sendMessage(Component.text("You have launched a snowball.", NamedTextColor.GREEN));
            }
            else if (e.getItem().getType() == Material.IRON_HOE) {
                player.launchProjectile(Egg.class);
                player.sendMessage(Component.text("You have launched an egg.", NamedTextColor.GREEN));
            }
            else if (e.getItem().getType() == Material.STONE_HOE) {
                player.launchProjectile(Trident.class);
                player.sendMessage(Component.text("You have launched a trident.", NamedTextColor.GREEN));
            }
        }
    }

}