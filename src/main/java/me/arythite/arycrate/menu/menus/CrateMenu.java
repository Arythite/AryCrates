package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.managers.CrateManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CrateMenu extends Menu {

    CrateManager crateManager;

    public CrateMenu(PlayerMenuUtility playerMenuUtility, CrateManager crateManager) {
        super(playerMenuUtility);
        this.crateManager = crateManager;
    }

    @Override
    public String getMenuName() {
        return "Crates";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        e.setCancelled(true);

        switch ((e.getCurrentItem()).getType()) {
            case CHEST:
                crateManager.getCrate(e.getCurrentItem().getItemMeta().getDisplayName());
            case STAINED_GLASS:
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("CLOSE"))
                    e.getWhoClicked().closeInventory();
                break;
        }
    }

    @Override
    public void setMenuItems() {

        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta chestMeta = chest.getItemMeta();

        chestMeta.setDisplayName("Test");
        chest.setItemMeta(chestMeta);

        inventory.setItem(0, chest);
    }
}
