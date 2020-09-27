package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class CrateMenu extends Menu {

    public CrateMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
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

        switch(Objects.requireNonNull(e.getCurrentItem()).getType()) {
            case CHEST:
            case GLASS:
                e.setCancelled(true);
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
