package me.arythite.arycrate.commands;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.managers.CrateManager;
import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.menus.RewardMenu;
import me.arythite.arycrate.objects.Crate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            RewardMenu rewardMenu = new RewardMenu(main.getPlayerMenuUtility(p), lootManager, newCrate, main);
            rewardMenu.open();
        }

        return true;
    }
}
