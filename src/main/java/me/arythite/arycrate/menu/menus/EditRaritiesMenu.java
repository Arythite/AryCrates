package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditRaritiesMenu extends Menu {
    LootManager lootManager;
    String lT;

    public EditRaritiesMenu(PlayerMenuUtility playerMenuUtility, LootManager lootManager, String lT) {
        super(playerMenuUtility);
        this.lootManager = lootManager;
        this.lT = lT;
    }

    @Override
    public String getMenuName() {
        return "Edit Rarities";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        switch (e.getCurrentItem().getType()) {
            case ENDER_CHEST:
                new EditItemsMenu(playerMenuUtility, lootManager, lT, e.getCurrentItem().getItemMeta().getDisplayName()).open();
                break;
            case EMERALD:
                e.getWhoClicked().closeInventory();
                break;
            case BARRIER:
                break;
            case CHEST:
                lootManager.addRarity(lT);
                break;
        }

    }

    @Override
    public void handleClose(InventoryCloseEvent e) {

    }

    @Override
    public void setMenuItems() {

        ItemStack rarity = new ItemStack(Material.ENDER_CHEST);
        ItemMeta rarityMeta = rarity.getItemMeta();

        ItemStack add = new ItemStack(Material.CHEST);
        ItemMeta addMeta = add.getItemMeta();
        addMeta.setDisplayName("§aAdd Rarity");
        add.setItemMeta(addMeta);

        for (int i = 0; i < lootManager.getRarities(lT).size(); i++) {
            rarityMeta.setDisplayName(lootManager.getRarities(lT).get(i));
            rarity.setItemMeta(rarityMeta);
            inventory.setItem(i, rarity);
        }

        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.THIN_GLASS));
        }

        inventory.setItem(49, add);

    }
}
