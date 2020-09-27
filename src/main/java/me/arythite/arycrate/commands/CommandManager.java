package me.arythite.arycrate.commands;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.menu.menus.CrateMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    Main main;
    // /crate give <player> {crate} <amount>
    public CommandManager(me.arythite.arycrate.Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p;
        if (sender instanceof Player) {
            p = (Player) sender;
        } else {
            return false;
        }

        if (args.length == 0) {
            CrateMenu crateMenu = new CrateMenu(main.getPlayerMenuUtility(p));
            crateMenu.open();
        }

        return true;
    }
}
