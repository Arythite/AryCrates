package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EditItemsMenu extends Menu {
    LootManager lootManager;
    String lT;
    String rarity;

    public EditItemsMenu(PlayerMenuUtility playerMenuUtility, LootManager lootManager, String lT, String rarity) {
        super(playerMenuUtility);
        this.lootManager = lootManager;
        this.lT = lT;
        this.rarity = rarity;
    }

    @Override
    public String getMenuName() {
        return "Edit Items";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if (e.getClick().isLeftClick()) {
            return;
        } else {
            inventory.setItem(e.getSlot(), null);
        }
    }

    @Override
    public void handleClose(InventoryCloseEvent e) {
        List<ItemStack> contents = new ArrayList<>();
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                contents.add(item);
            }
        }
        lootManager.setItems(contents, lT, rarity);
    }

    @Override
    public void setMenuItems() {
        List<ItemStack> items = new ArrayList<>(lootManager.getItemsFromRarity(lT, rarity));

        if (items.isEmpty())
            return;

        for (int i = 0; i < items.size(); i++) {
            if (i < 54) {
                inventory.setItem(i, items.get(i));
            } else {
                playerMenuUtility.getOwner().sendMessage("Too many items in rarity, please create another rarity and halve the percentage " +
                        "of this and the other one so they add up to the same amount as this one");
            }
        }

    }
}
