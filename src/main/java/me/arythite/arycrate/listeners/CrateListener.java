package me.arythite.arycrate.listeners;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.managers.CrateManager;
import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.menus.RewardMenu;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class CrateListener implements Listener {

    CrateManager crateManager;
    Main main;
    LootManager lootManager;

    public CrateListener(CrateManager crateManager, LootManager lootManager, Main main) {
        this.crateManager = crateManager;
        this.main = main;
        this.lootManager = lootManager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock() == null)
            return;
        if (e.getClickedBlock().getType() == Material.CHEST) {
            Chest chest = (Chest) e.getClickedBlock().getState();
            Inventory chestInv = chest.getBlockInventory();
            System.out.println("Clicked Chest Name: " + chest.getInventory().getName().toLowerCase());
            if (chest.getInventory().getTitle() == null)
                return;
            if (crateManager.getCrates() != null) {
                crateManager.getCrates().forEach((crate) -> {
                    if (crate.getDisplayName().toLowerCase().equals(chest.getInventory().getTitle().toLowerCase())) {
                        e.setCancelled(true);
                        new RewardMenu(main.getPlayerMenuUtility(p), lootManager, crate, main).open();
                        System.out.println("Opening rewards menu");
                    }
                });
            }

        }
    }

}
