package me.arythite.arycrate.config.configs;

import me.arythite.arycrate.Main;
import me.arythite.arycrate.config.Config;

public class CrateConfig extends Config {

    public CrateConfig(Main main) {
        super(main);
    }

    @Override
    public String getFile() {
        return "crates.yml";
    }
}
