package me.arythite.arycrate.listeners;

import me.arythite.arycrate.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        InventoryHolder holder;
        if (e.getClickedInventory() == null) {
            return;
        } else if (e.getClickedInventory().getHolder() != null) {
            holder = e.getClickedInventory().getHolder();
        } else {
            return;
        }

        if (holder instanceof Menu) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null) {
                return;
            }

            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }
    }
}
