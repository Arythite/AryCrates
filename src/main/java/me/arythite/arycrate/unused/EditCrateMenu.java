package me.arythite.arycrate.unused;

import me.arythite.arycrate.managers.CrateManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditCrateMenu extends Menu {

    CrateManager crateManager;
    ItemStack border = new ItemStack(Material.THIN_GLASS);
    ItemStack confirm = new ItemStack(Material.STAINED_GLASS_PANE);
    ItemStack delete = new ItemStack(Material.BARRIER);
    ItemStack cancel = new ItemStack(Material.STAINED_GLASS_PANE);
    ItemStack lootTable = new ItemStack(Material.STORAGE_MINECART);
    ItemStack animation = new ItemStack(Material.SADDLE);
    ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
    ItemStack name = new ItemStack(Material.NAME_TAG);
    ItemStack type = new ItemStack(Material.POTION);

    public EditCrateMenu(PlayerMenuUtility playerMenuUtility, CrateManager crateManager) {
        super(playerMenuUtility);
        this.crateManager = crateManager;
    }

    @Override
    public String getMenuName() {
        return "Edit Crate";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || Material.THIN_GLASS == e.getCurrentItem().getType()) {
            return;
        }

        e.getWhoClicked().sendMessage(String.valueOf(e.getCurrentItem()));

        if (e.getCurrentItem().equals(name)) {
            e.getWhoClicked().sendMessage("FUCK U");
        } else if (confirm == e.getCurrentItem()) {

        } else if (delete.equals(e.getCurrentItem())) {

        } else if (key == e.getCurrentItem()) {

        } else if (animation.equals(e.getCurrentItem())) {

        } else if (cancel.equals(e.getCurrentItem())) {

        } else if (lootTable.equals(e.getCurrentItem())) {

        } else if (type.equals(e.getCurrentItem())) {

        }
        e.setCancelled(true);
    }

    @Override
    public void handleClose(InventoryCloseEvent e) {

    }

    @Override
    public void setMenuItems() {

        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(" ");
        border.setItemMeta(borderMeta);

        ItemMeta deleteMeta = delete.getItemMeta();
        deleteMeta.setDisplayName("§4§lDelete");
        delete.setItemMeta(deleteMeta);

        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName("§a§lConfirm");
        confirm.setDurability((short) 5);
        confirm.setItemMeta(confirmMeta);

        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName("§c§lCancel");
        cancel.setDurability((short) 14);
        cancel.setItemMeta(cancelMeta);

        ItemMeta nameMeta = name.getItemMeta();
        nameMeta.setDisplayName("§aName");
        name.setItemMeta(nameMeta);

        ItemMeta lootTableMeta = lootTable.getItemMeta();
        lootTableMeta.setDisplayName("§cLoot Table");
        lootTable.setItemMeta(lootTableMeta);

        ItemMeta animationMeta = animation.getItemMeta();
        animationMeta.setDisplayName("§bAnimation");
        animation.setItemMeta(animationMeta);

        ItemMeta typeMeta = type.getItemMeta();
        typeMeta.setDisplayName("§7Type");
        type.setItemMeta(typeMeta);

        ItemMeta keyMeta = key.getItemMeta();
        keyMeta.setDisplayName("§1Key Name");
        key.setItemMeta(keyMeta);

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, border);
            inventory.setItem(i + 18, border);
        }

        inventory.setItem(22, delete);
        inventory.setItem(9, confirm);
        inventory.setItem(11, name);
        inventory.setItem(12, type);
        inventory.setItem(13, animation);
        inventory.setItem(14, lootTable);
        inventory.setItem(15, key);
        inventory.setItem(17, cancel);
    }
}
