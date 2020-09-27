package me.arythite.arycrate.config;

import me.arythite.arycrate.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    public Config(Main main) {
        this.main = main;
    }

    protected Main main;

    protected File customConfigFile;

    protected FileConfiguration customConfig;

    public String getFile() {
        return "";
    }

    public void createConfig() {
        customConfigFile = new File(main.getDataFolder(), getFile());
        if(!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            main.saveResource(getFile(), false);
        }
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
//        if (customConfig.getKeys(true).size() == 0 ){
//            System.out.println("deleting and creating custom rarity config file");
//            customConfigFile.delete();
//            createConfig();
//        }
    }

    public FileConfiguration get() {
        return this.customConfig;
    }

    public void save() {
        try {
            customConfig.save(this.customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        this.customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
    }
}
