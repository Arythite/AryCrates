package me.arythite.arycrate.managers;

import me.arythite.arycrate.config.configs.CrateConfig;
import me.arythite.arycrate.objects.Crate;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

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

    List<Crate> crates = new ArrayList<>();

    public void loadCrates() {
        if (config.get().getConfigurationSection("") == null || config == null) {
            System.out.println("Error loading up crates");
            return;
        }
        config.get().getConfigurationSection("").getKeys(false).forEach((crate) -> {
            config.get().getConfigurationSection(crate).getKeys(false).forEach((info) -> {
                final String path = crate + "." + info;
                switch (info) {
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
                }
            });
            crates.add(new Crate(crateName, lootTable, animation, keyName));
        });
        System.out.println("Done loading up crates");
    }

    public List<Crate> getCrates() {
        return crates;
    }

    public Crate getCrate(String crateName) {
        for (Crate crate : crates) {
            if (crate.getCrateName().toLowerCase().equals(crateName.toLowerCase()))
                return crate;
        }
        return null;
    }
}
