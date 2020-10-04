package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EditRarityMenu extends Menu {
    LootManager lootManager;
    String rarity;
    String lT;

    public EditRarityMenu(PlayerMenuUtility playerMenuUtility, LootManager lootManager, String rarity, String lT) {
        super(playerMenuUtility);
        this.lootManager = lootManager;
        this.rarity = rarity;
        this.lT = lT;
    }

    @Override
    public String getMenuName() {
        return "Edit Rarity";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void setMenuItems() {

    }
}
