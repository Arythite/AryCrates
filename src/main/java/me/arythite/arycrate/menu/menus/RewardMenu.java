package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.Crate;
import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class RewardMenu extends Menu {

    Random rand = new Random();

    LootManager lootManager;
    Crate crate;

    public RewardMenu(PlayerMenuUtility playerMenuUtility, LootManager lootManager, Crate crate) {
        super(playerMenuUtility);
        this.lootManager = lootManager;
        this.crate = crate;
    }

    @Override
    public String getMenuName() {
        return "Rewards";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void setMenuItems() {

        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(" ");
        border.setDurability((short) 15);
        borderMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        border.setItemMeta(borderMeta);

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, border);
            inventory.setItem(i + 18, border);
        }

        inventory.setItem(4, new ItemStack(Material.HOPPER));
    }

    public void spin() {
        String[] rarities = lootManager.getRarityList(crate.getLootTable());
        int startingIndex = rand.nextInt(rarities.length);

    }
}
