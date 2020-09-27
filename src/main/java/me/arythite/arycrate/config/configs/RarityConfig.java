package me.arythite.arycrate.config.configs;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.config.Config;

public class RarityConfig extends Config {

    public RarityConfig(Main main) {
        super(main);
    }

    @Override
    public String getFile() {
        return "rarityLootTables.yml";
    }
}
