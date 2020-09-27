package me.arythite.arycrate.managers;

import me.arythite.arycrate.config.configs.RarityConfig;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.math.RandomUtils.nextInt;

public class LootManager {

    RarityConfig config;

    public LootManager(RarityConfig config) {
        this.config = config;
    }

    // List of each rarity and lootTable
    Map<String, String[]> rarities = new HashMap<>();

    // LootTable with ItemStacks + The chance of each rarity
    Map<String, ItemStack[]> lootTables = new HashMap<>();
    Map<String, String> chances = new HashMap<>();
    Map<String, Integer> totalChance = new HashMap<>();

    // Items rarities and lootTables from the whole config file
    Map<String, Map<String, ItemStack[]>> masterTable = new HashMap<>();

    // Rarity instances depending on chance
    Map<String, String[]> rarityInstances = new HashMap<>();
    List<String> instances = new ArrayList<>();

    @SuppressWarnings({"ConstantConditions", "SuspiciousToArrayCall"})
    public void loadData() {

        for (String lT : config.get().getConfigurationSection("").getKeys(false)) {
            // Do this for every lootTable
            if (config.get().getConfigurationSection(lT).isSet(lT + ".rarity")) {
                rarities.put(lT, config.get().getConfigurationSection(lT + ".rarity").getKeys(false).toArray(new String[0]));
            }

            if (!rarities.isEmpty()) {
                // Do this if lootTable contains rarities
                for (String r : rarities.get(lT)) {
                    // Do this for every rarity in every lootTable
                    if (config.get().get(lT + ".rarity." + r + ".chance") != null) {
                        chances.put(r, (config.get().get(lT + ".rarity." + r + ".chance")).toString());
                    }
                    if (config.get().get(lT + ".rarity." + r + ".items") != null) {
                        lootTables.put(r, config.get().getList(lT + ".rarity." + r + ".items").toArray(new ItemStack[0]));
                    } else {
                        System.out.println("No items found in " + r + " rarity in " + lT + " loot table");
                    }
                }
            }
            int t = 0;
            // Setting up selecting random items
            for (String i : chances.keySet()) {
                for (int j = 0; j < Integer.parseInt(chances.get(i)); j++) {
                    instances.add(i);
                    t++;
                }
            }
            chances.clear();

            rarityInstances.put(lT, instances.toArray(new String[0]));
            totalChance.put(lT, t);

            masterTable.put(lT, lootTables);
        }

        System.out.println("Done loading up loot tables");
    }

    public ItemStack getRandomItem(String lT) {

        Map<String, ItemStack[]> table = masterTable.get(lT);

        String rarity = rarityInstances.get(lT)[nextInt(totalChance.get(lT))];

        return table.get(rarity)[nextInt(table.get(rarity).length)];

    }

}
