package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LootTableMenu extends Menu {
    LootManager lootManager;

    public LootTableMenu(PlayerMenuUtility playerMenuUtility, LootManager lootManager) {
        super(playerMenuUtility);
        this.lootManager = lootManager;
    }

    @Override
    public String getMenuName() {
        return "Loot Tables";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        e.setCancelled(true);

        if (e.getCurrentItem().equals(new ItemStack(Material.EMERALD))) {
            e.getWhoClicked().closeInventory();
        } else if (e.getCurrentItem().equals(new ItemStack(Material.BARRIER))) {

        } else if (e.getCurrentItem().equals(new ItemStack(Material.CHEST))) {
            lootManager.addTable();
            this.setMenuItems();
        } else {
            new EditRaritiesMenu(playerMenuUtility, lootManager, e.getCurrentItem().getItemMeta().getDisplayName()).open();
        }
    }

    @Override
    public void handleClose(InventoryCloseEvent e) {

    }

    @Override
    public void setMenuItems() {
        lootManager.loadData();

        ItemStack table = new ItemStack(Material.STORAGE_MINECART);
        ItemMeta tableMeta = table.getItemMeta();

        ItemStack add = new ItemStack(Material.CHEST);
        ItemMeta addMeta = add.getItemMeta();
        addMeta.setDisplayName("§aAdd Loot Table");
        add.setItemMeta(addMeta);

        for (int k = 0; k < lootManager.getLootTables().size(); k++) {
            tableMeta.setDisplayName(lootManager.getLootTables().get(k));
            table.setItemMeta(tableMeta);
            inventory.setItem(k, table);
        }

        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.THIN_GLASS));
        }

        inventory.setItem(49, add);
    }
}
