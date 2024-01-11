package com.brandan.newguns;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HoeCommand implements CommandExecutor, TabCompleter {

    private final Map<String, ItemStack> hoeItem = createHoes();

    private Map<String, ItemStack> createHoes() {
        Map<String, ItemStack> hoes = new HashMap<>();
        ItemStack diamond = hoesMeta("diamond");
        ItemStack iron = hoesMeta("iron");
        ItemStack stone = hoesMeta("stone");

        hoes.put("diamond", diamond);
        hoes.put("iron", iron);
        hoes.put("stone", stone);

        return hoes;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1 && command.getName().equalsIgnoreCase("Hoe") || (args.length == 0 && command.getName().equalsIgnoreCase("Hoe"))) {
                if (command.getName().equalsIgnoreCase("Hoe")) {
                    sender.sendMessage(Component.text("Usage: /hoe {Player} {Arguments}", NamedTextColor.RED));
                    return true;
                }
            }

            if (args.length == 2 && command.getName().equalsIgnoreCase("Hoe")) {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (target.getName().equalsIgnoreCase(args[0])) {
                        ItemStack hoe = hoeItem.get(args[1]);
                        if (hoe != null) {
                            target.getInventory().addItem(hoe);
                            target.sendMessage(Component.text("Received a " + args[1] + " Hoe", NamedTextColor.GREEN));
                            sender.sendMessage(Component.text(target.getName() + " received a " + args[1] + " Hoe", NamedTextColor.GREEN));
                            return true;
                        } else {
                            sender.sendMessage(Component.text("Error: Invalid hoe type.", NamedTextColor.RED));
                            return false;
                        }
                    }
                }
                sender.sendMessage(Component.text("Error: Player not found.", NamedTextColor.RED));
            }
        }
        return false;
    }

    public ItemStack hoesMeta(String material) {



        if (material.equalsIgnoreCase("diamond")) {
            ItemStack diamond = new ItemStack(Material.DIAMOND_HOE);
            ItemMeta meta = diamond.getItemMeta();
            List<Component> lore = Collections.singletonList(Component.text("Click to launch snowballs", NamedTextColor.BLUE));
            meta.lore(lore);
            meta.displayName(Component.text("Snowball Launcher", NamedTextColor.WHITE));
            diamond.setItemMeta(meta);
            return diamond;
        }
        if (material.equalsIgnoreCase("iron")) {
            ItemStack iron = new ItemStack(Material.IRON_HOE);
            ItemMeta meta = iron.getItemMeta();
            List<Component> lore = Collections.singletonList(Component.text("Click to launch eggs", NamedTextColor.YELLOW));
            meta.lore(lore);
            meta.displayName(Component.text("Egg Launcher", NamedTextColor.YELLOW));
            iron.setItemMeta(meta);
            return iron;
        }
        if (material.equalsIgnoreCase("stone")) {
            ItemStack stone = new ItemStack(Material.STONE_HOE);
            ItemMeta meta = stone.getItemMeta();
            List<Component> lore = Collections.singletonList(Component.text("Click to launch tridents", NamedTextColor.BLUE));
            meta.lore(lore);
            meta.displayName(Component.text("Trident Launcher", NamedTextColor.WHITE));
            stone.setItemMeta(meta);
            return stone;
        }
        return null;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("Hoe")) {
            if (args.length == 1) {
                // If the player is typing the first argument (player name), return online player names
                List<String> playerNames = new ArrayList<>();
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    playerNames.add(onlinePlayer.getName());
                }
                return playerNames;
            } else if (args.length == 2) {
                // If the player is typing the second argument, return valid hoe types
                List<String> list = new ArrayList<>();
                list.add("diamond");
                list.add("iron");
                list.add("stone");

                return list;
            }
        }
        return null;
    }
}
