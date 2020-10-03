package me.arythite.arycrate.managers;

import me.arythite.arycrate.config.configs.RarityConfig;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static org.apache.commons.lang.math.RandomUtils.nextInt;

public class LootManager {

    RarityConfig config;

    public LootManager(RarityConfig config) {
        this.config = config;
    }

    // Rarities in each loot table
    Map<String, String[]> rarities = new HashMap<>();

    // Each loot tables total percentage and
    Map<String, String> chances = new HashMap<>();
    Map<String, Integer> totalChance = new HashMap<>();
    Map<String, ItemStack[]> lootTables = new HashMap<>();

    // Rarity instances depending on chance
    List<String> instances = new ArrayList<>();
    Map<String, String[]> rarityInstances = new HashMap<>();

    // Items rarities and lootTables from the whole config file
    Map<String, Map<String, ItemStack[]>> masterTable = new HashMap<>();

    @SuppressWarnings({"SuspiciousToArrayCall"})
    public void loadData() {

        config.get().getConfigurationSection("").getKeys(false).forEach((lootTable) -> {
            rarities.put(lootTable, config.get().getConfigurationSection(lootTable + ".rarity").getKeys(false).toArray(new String[0]));
            if (!rarities.isEmpty()) {
                for (String r : rarities.get(lootTable)) {
                    if (config.get().get(lootTable + ".rarity." + r + ".chance") != null) {
                        chances.put(r, (config.get().get(lootTable + ".rarity." + r + ".chance")).toString());
                    }
                    if (config.get().get(lootTable + ".rarity." + r + ".items") != null) {
                        lootTables.put(r, config.get().getList(lootTable + ".rarity." + r + ".items").toArray(new ItemStack[0]));
                    } else {
                        System.out.println("No items found in " + r + " rarity in " + lootTable + " loot table");
                    }
                }
            }
            int t = 0;
            for (String i : chances.keySet()) {
                for (int j = 0; j < Integer.parseInt(chances.get(i)); j++) {
                    instances.add(i);
                    t++;
                }
            }
            chances.clear();

            rarityInstances.put(lootTable, instances.toArray(new String[0]));
            totalChance.put(lootTable, t);
            masterTable.put(lootTable, lootTables);
        });
        System.out.println("Done loading up loot tables");
    }

    public ItemStack getRandomItem(String lT) {

        Map<String, ItemStack[]> table = masterTable.get(lT);

        if (table == null) {
            System.out.println("Loot table is empty");
            return null;
        }

        String rarity = rarityInstances.get(lT)[nextInt(totalChance.get(lT))];
        return table.get(rarity)[nextInt(table.get(rarity).length)];
    }

    public ItemStack getItemFromRarity(String lT, String rarity) {

        Map<String, ItemStack[]> table = masterTable.get(lT);

        if (table == null) {
            System.out.println("Loot table is empty");
            return null;
        }

        return table.get(rarity)[nextInt(table.get(rarity).length)];
    }

    public List<ItemStack> getRandomItems(String lT) {

        int itemCount = 0;
        Map<String, ItemStack[]> table = masterTable.get(lT);
        List<ItemStack> itemList = new ArrayList<>();

        if (table == null) {
            System.out.println("Loot table is empty");
            return null;
        }

        for (String key : table.keySet()) {
            itemCount += table.get(key).length;
        }


        return itemList;
    }

    public String[] getRarityList(String lT) {

        List<String> instances = new ArrayList<>();
        for (String r : rarityInstances.keySet()) {
            instances.addAll(Arrays.asList(rarityInstances.get(r)));
        }

        return instances.toArray(new String[0]);
    }

    public int getTotalChance(String lT) {
        return totalChance.get(lT);
    }
}
