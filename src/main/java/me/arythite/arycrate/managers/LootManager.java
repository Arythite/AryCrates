package me.arythite.arycrate.managers;

import me.arythite.arycrate.config.configs.RarityConfig;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static org.apache.commons.lang.math.RandomUtils.nextInt;

public class LootManager {

    RarityConfig config;

    public LootManager(RarityConfig config) {
        this.config = config;
    }

    // Rarities in each loot table
    Map<String, List<String>> rarities = new HashMap<>();

    // Each loot tables total percentage and
    Map<String, String> chances = new HashMap<>();
    Map<String, Integer> totalChance = new HashMap<>();
    Map<String, List<ItemStack>> lootTables = new HashMap<>();

    // Rarity instances depending on chance
    List<String> instances = new ArrayList<>();
    Map<String, List<String>> rarityInstances = new HashMap<>();

    // Items rarities and lootTables from the whole config file
    Map<String, Map<String, List<ItemStack>>> masterTable = new HashMap<>();

    // Used for creating lootTables and rarities
    int i = nextInt(10000000);
    int r = nextInt(10000000);

    @SuppressWarnings({"SuspiciousToArrayCall"})
    public void loadData() {
        clear();

        for (String lootTable : config.get().getConfigurationSection("").getKeys(false)) {

            lootTables = new HashMap<>();
            rarities.put(lootTable, Arrays.asList(config.get().getConfigurationSection(lootTable + ".rarity").getKeys(false).toArray(new String[0])));
            if (!rarities.isEmpty()) {
                for (String r : rarities.get(lootTable)) {
                    if (config.get().get(lootTable + ".rarity." + r + ".chance") != null) {
                        chances.put(r, (config.get().get(lootTable + ".rarity." + r + ".chance")).toString());
                    }
                    if (config.get().getList(lootTable + ".rarity." + r + ".items") != null) {
                        lootTables.put(r, (List<ItemStack>) config.get().getList(lootTable + ".rarity." + r + ".items"));
                    } else {
                        lootTables.put(r, Arrays.asList(new ItemStack(Material.ICE)));
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

            rarityInstances.put(lootTable, instances);
            totalChance.put(lootTable, t);
            masterTable.put(lootTable, lootTables);
        }
    }

    public void clear() {
        rarities.clear();
        chances.clear();
        totalChance.clear();
        lootTables.clear();
        instances.clear();
        rarityInstances.clear();
        masterTable.clear();
    }

    public void addItem(String lT, String rarity, ItemStack item) {
        List<String> list = config.get().getStringList(lT + ".rarity." + rarity + ".items");
        list.add(item.toString());
        masterTable.get(lT).get(rarity);
        config.get().set(lT + ".rarity." + rarity + ".items", list);
        config.save();
        this.loadData();
    }

    public void addTable() {
        String rarities = "common:epic:legendary";
        List<String> list = new ArrayList<>(Arrays.asList(rarities.split(":")));
        String lT = "lootTable" + i;
        i++;

        list.forEach((rarity) -> {
            config.get().set(lT + ".rarity." + rarity + ".chance", 1);
            config.get().set(lT + ".rarity." + rarity + ".items", new ArrayList<>());
        });

        config.save();
        this.loadData();
    }

    public void addRarity(String lT) {
        config.get().set(lT + ".rarity." + "rare" + r, new ArrayList<>(Arrays.asList(new ItemStack(Material.ICE))));

        config.save();
        this.loadData();
    }

    public ItemStack getRandomItem(String lT) {

        Map<String, List<ItemStack>> table = masterTable.get(lT);

        if (table == null) {
            System.out.println("Loot table is empty");
            return null;
        }

        String rarity = rarityInstances.get(lT).get(nextInt(totalChance.get(lT)));
        return table.get(rarity).get(nextInt(table.get(rarity).size()));
    }

    public ItemStack getItemFromRarity(String lT, String rarity) {

        Map<String, List<ItemStack>> table = masterTable.get(lT);

        if (table == null) {
            System.out.println("Loot table is empty");
            return null;
        }

        return table.get(rarity).get(nextInt(table.get(rarity).size()));
    }

    public List<ItemStack> getItemsFromRarity(String lT, String rarity) {
        return masterTable.get(lT).get(rarity);
    }

    public void removeItem(ItemStack is, String lT, String rarity) {
        config.get().getList(lT + ".rarity." + rarity + ".items").remove(is.toString());
        config.save();
        this.loadData();
    }

    public void setItems(List<ItemStack> is, String lT, String rarity) {
        config.get().set(lT + ".rarity." + rarity + ".items", is);
        config.save();
        this.loadData();
    }

    public List<ItemStack> getRandomItems(String lT) {

        int itemCount = 0;
        Map<String, List<ItemStack>> table = masterTable.get(lT);
        List<ItemStack> itemList = new ArrayList<>();

        if (table == null) {
            System.out.println("Loot table is empty");
            return null;
        }

        for (String key : table.keySet()) {
            itemCount += table.get(key).size();
        }

        return itemList;
    }

    public List<String> getRarities(String lT) {
        if (masterTable.isEmpty())
            return null;
        else if (masterTable.get(lT) == null)
            return null;
        return new ArrayList<>(masterTable.get(lT).keySet());
    }

    public List<String> getRarityList(String lT) {

        List<String> instances = new ArrayList<>();
        for (String r : rarityInstances.keySet()) {
            instances.addAll(rarityInstances.get(r));
        }

        return instances;
    }


    public int getTotalChance(String lT) {
        return totalChance.get(lT);
    }

    public List<String> getLootTables() {
        return new ArrayList<>(masterTable.keySet());
    }

    public String getLootTable(String lT) {
        for (String key : masterTable.keySet()) {
            if (key.equals(lT)) {
                return key;
            }
        }

        return null;
    }
}
