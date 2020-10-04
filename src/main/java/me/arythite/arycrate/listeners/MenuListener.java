package me.arythite.arycrate.listeners;

import me.arythite.arycrate.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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

            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
                return;
            }

            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        InventoryHolder holder = e.getInventory().getHolder();

        if (holder instanceof Menu) {

            Menu menu = (Menu) holder;
            menu.handleClose(e);
        }
    }
}
