package me.arythite.arycrate.managers;

import me.arythite.arycrate.Crate;
import me.arythite.arycrate.config.configs.CrateConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Chest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CrateManager {

    CrateConfig config;

    String displayName;
    String crateName;
    String lootTable;
    String animation;
    String keyName;

    List<Location> locations = new ArrayList<>();

    public CrateManager(CrateConfig config) {
        this.config = config;
    }

    public CrateManager() {

    };

    List<Crate> crates = new ArrayList<>();

    public void loadCrates() {
        if (config.get().getConfigurationSection("") == null || config == null) {
            System.out.println("Error loading up crates");
            return;
        }
        config.get().getConfigurationSection("").getKeys(false).forEach((crate) -> {
            config.get().getConfigurationSection(crate).getKeys(false).forEach((info) -> {
                final String path = crate.toString() + "." + info.toString();
                switch (info.toString()) {
                    case "display-name":
                        displayName = config.get().get(path).toString();
                        break;
                    case "crate-name":
                        crateName = config.get().get(path).toString();
                        break;
                    case "loot-table":
                        lootTable = config.get().get(path).toString();
                        break;
                    case "animation":
                        animation = config.get().get(path).toString();
                        break;
                    case "key-name":
                        keyName = config.get().get(path).toString();
                        break;
                    case "locations":
                        config.get().getList(path).stream()
                                .filter((location) -> (location instanceof String))
                                .forEach((location) -> locations.add(deserializeLocation((String) location)));
                        break;
                }
                crates.add(new Crate(crateName, displayName, lootTable, animation, keyName, locations));
            });
        });
        System.out.println("Done loading up crates");
    }

    public String serializeLocation(Location loc) {
        if (loc == null) {
            return null;
        }
        return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ();
    }

    public Location deserializeLocation(String s) {
        String[] parts = s.split(":");
        if (s.equals("")) {
            return null;
        } else {
            if (parts.length == 4) {
                World w = Bukkit.getServer().getWorld(parts[0]);
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double z = Double.parseDouble(parts[3]);
                return new Location(w, x, y, z);
            }
        }
        return null;
    }

}
