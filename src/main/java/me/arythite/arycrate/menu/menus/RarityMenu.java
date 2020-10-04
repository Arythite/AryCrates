package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class RarityMenu extends Menu {

    public RarityMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Rarities";
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void handleClose(InventoryCloseEvent e) {

    }

    @Override
    public void setMenuItems() {

    }
}
