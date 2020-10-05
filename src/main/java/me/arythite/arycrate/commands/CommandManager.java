package me.arythite.arycrate.commands;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.managers.CrateManager;
import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.menus.LootTableMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandManager implements CommandExecutor {

    Main main;
    LootManager lootManager;
    CrateManager crateManager;

    public CommandManager(Main main, LootManager lootManager, CrateManager crateManager) {
        this.main = main;
        this.lootManager = lootManager;
        this.crateManager = crateManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p;
        if (sender instanceof Player) {
            p = (Player) sender;
        } else {
            return true;
        }

        if (args.length >= 1) {
            if (args[0].equals("edit")) {
                if (args.length >= 2) {
                    if (args[1].equals("loot")) {
                        new LootTableMenu(main.getPlayerMenuUtility(p), lootManager).open();
                    }
                } else {
                    p.sendMessage("§ccrate edit <loot/crate>");
                }
            }
            if (args[0].equals("give")) {
                if (args.length >= 2) {
                    if (args.length >= 3) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            if (args.length >= 4) {
                                /// crate give Arythite def key
                                ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
                                ItemMeta keyMeta = key.getItemMeta();
                                if (crateManager.getCrate(args[2]) == null) {
                                    return true;
                                }
                                keyMeta.setDisplayName(crateManager.getCrate(args[2]).getKeyName());
                                key.setItemMeta(keyMeta);

                                ItemStack chest = new ItemStack(Material.CHEST);
                                ItemMeta crateMeta = chest.getItemMeta();
                                crateMeta.setDisplayName(crateManager.getCrate(args[2]).getDisplayName());
                                chest.setItemMeta(crateMeta);

                                if (keyMeta.getDisplayName() == null)
                                    return true;

                                if (args[3].equals("key")) {
                                    p.getInventory().addItem(key);
                                } else if (args[3].equals("crate")) {
                                    p.getInventory().addItem(chest);
                                } else {
                                    p.sendMessage("§ccrate give <player> <cratename> <crate/key>");
                                }

                            } else {
                                p.sendMessage("§ccrate give <player> <cratename> <crate/key>");
                            }
                        } else {
                            p.sendMessage("§cNot a valid player");
                        }
                    }
                } else {
                    p.sendMessage("§ccrate give <player> <cratename>");
                }
            }
        } else {
            p.sendMessage("§ccrate <edit/give>");
        }

        return true;
    }
}
