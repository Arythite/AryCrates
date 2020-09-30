package me.arythite.arycrate.commands;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.menu.menus.CrateMenu;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
            ItemStack chest = new ItemStack(Material.CHEST);
            ItemMeta chestMeta = chest.getItemMeta();
            chestMeta.setDisplayName("§4§lChesty");
            chest.setItemMeta(chestMeta);
            p.setItemInHand(chest);

            CrateMenu crateMenu = new CrateMenu(main.getPlayerMenuUtility(p));
            crateMenu.open();
        }

        return true;
    }
}
