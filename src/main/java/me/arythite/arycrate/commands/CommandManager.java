package me.arythite.arycrate.commands;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.managers.CrateManager;
import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.menus.LootTableMenu;
import me.arythite.arycrate.objects.Crate;
import me.arythite.arycrate.unused.EditCrateMenu;
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

        if (args.length == 0) {
            Crate newCrate = crateManager.getCrate("def");
            EditCrateMenu editCrateMenu = new EditCrateMenu(main.getPlayerMenuUtility(p), crateManager);
            editCrateMenu.open();
        }
        p.sendMessage(String.valueOf(args.length));

        if (args.length >= 1) {
            if (args[0].equals("edit")) {
                if (args.length >= 2) {
                    if (args[1].equals("loot")) {
                        new LootTableMenu(main.getPlayerMenuUtility(p), lootManager).open();
                    } else if (args[1].equals("crate")) {

                    }
                } else {
                    p.sendMessage("crate edit <loot/crate>");
                }
            }
            if (args[0].equals("give")) {
                if (args.length >= 2) {

                    Crate crate = crateManager.getCrate(args[1]);
                    ItemStack chest = new ItemStack(Material.CHEST);
                    ItemMeta crateMeta = chest.getItemMeta();
                    crateMeta.setDisplayName(crate.getDisplayName());
                    chest.setItemMeta(crateMeta);

                    p.getInventory().addItem(chest);
                } else {
                    p.sendMessage("crate give <cratename>");
                }
            }
        }

        return true;
    }
}
