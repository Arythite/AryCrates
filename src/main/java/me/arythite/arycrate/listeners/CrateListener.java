package me.arythite.arycrate.listeners;

import me.arythite.arycrate.Crate;
import me.arythite.arycrate.managers.CrateManager;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class CrateListener implements Listener {

    CrateManager crateManager = new CrateManager();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        if (e.getClickedBlock().getType() == Material.CHEST) {
            Chest chest = (Chest) e.getClickedBlock().getState();
            Inventory chestInv = (Inventory) chest.getBlockInventory();
        } else {
            return;
        }

    }

}
