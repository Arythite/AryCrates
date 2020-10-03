package me.arythite.arycrate.menu.menus;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.Menu;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import me.arythite.arycrate.objects.Crate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RewardMenu extends Menu {

    Random rand = new Random();
    int itemIndex = 0;

    LootManager lootManager;
    Crate crate;
    Main main;

    public RewardMenu(PlayerMenuUtility playerMenuUtility, LootManager lootManager, Crate crate, Main main) {
        super(playerMenuUtility);
        this.lootManager = lootManager;
        this.crate = crate;
        this.main = main;
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

    @Override
    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());
        this.setMenuItems();

        String[] rarities = lootManager.getRarityList(crate.getLootTable());
        List<ItemStack> itemList = new ArrayList<>();
        int startingIndex = rand.nextInt(rarities.length);

        double seconds = 5 + (3 * rand.nextDouble());
        System.out.println(seconds);

        for (int i = 0; i < lootManager.getTotalChance(crate.getLootTable()); i++) {
            itemList.add(lootManager.getRandomItem(crate.getLootTable()));
        }

        for (int index = 0; index < startingIndex; index++) {
            for (int is = 9; is < 18; is++) {
                inventory.setItem(is, itemList.get((is + itemIndex) % itemList.size()));
            }
            itemIndex++;
        }

        playerMenuUtility.getOwner().openInventory(inventory);

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean running = true;

            @Override
            public void run() {
                if (running = false)
                    return;
                ticks++;
                delay += 1 / (20 * seconds);
                if (ticks > delay * 20) {
                    ticks = 0;
                    for (int is = 9; is < 18; is++) {
                        inventory.setItem(is, itemList.get((is + itemIndex) % itemList.size()));
                    }
                    itemIndex++;
                    if (delay >= .9) {
                        running = false;
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                ItemStack reward = inventory.getItem(13);
                                Player p = playerMenuUtility.getOwner();
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 15);
                                p.getInventory().addItem(reward);
                                p.updateInventory();
                                p.closeInventory();
                                cancel();
                            }

                        }.runTaskLater(Main.getPlugin(Main.class), 30);
                        cancel();
                    }
                }
            }

        }.runTaskTimer(main, 0, 1);
    }
}
