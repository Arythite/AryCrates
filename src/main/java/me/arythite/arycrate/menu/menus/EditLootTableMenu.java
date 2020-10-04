package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditLootTableMenu extends Menu {
    LootManager lootManager;
    String lT;

    public EditLootTableMenu(PlayerMenuUtility playerMenuUtility, LootManager lootManager, String lT) {
        super(playerMenuUtility);
        this.lootManager = lootManager;
        this.lT = lT;
    }

    @Override
    public String getMenuName() {
        return "Edit Loot Table";
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

        ItemStack rarity = new ItemStack(Material.ENDER_CHEST);
        ItemMeta rarityMeta = rarity.getItemMeta();

        for (int i = 0; i < lootManager.getRarities(lT).size(); i++) {
            rarityMeta.setDisplayName(lootManager.getRarities(lT).get(i));
            rarity.setItemMeta(rarityMeta);
            inventory.setItem(i, rarity);
        }

    }
}
