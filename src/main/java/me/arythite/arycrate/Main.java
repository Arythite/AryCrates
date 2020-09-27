package me.arythite.arycrate;

import me.arythite.arycrate.commands.CommandManager;
import me.arythite.arycrate.config.configs.CrateConfig;
import me.arythite.arycrate.config.configs.RarityConfig;
import me.arythite.arycrate.listeners.MenuListener;
import me.arythite.arycrate.managers.CrateManager;
import me.arythite.arycrate.managers.LootManager;
import me.arythite.arycrate.menu.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    private static final Map<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    RarityConfig rConfig = new RarityConfig(this);
    CrateConfig cConfig = new CrateConfig(this);
    CrateManager cManager = new CrateManager(cConfig);

    LootManager lootManager = new LootManager(rConfig);

    @Override
    public void onEnable() {

        loadItems();
        getCommand("crate").setExecutor(new CommandManager(this));
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public void loadItems() {
        rConfig.createConfig();
        rConfig.get().options().copyDefaults(true);
        rConfig.save();

        cConfig.createConfig();
        cConfig.get().options().copyDefaults(true);
        cConfig.save();

        getConfig().options().copyDefaults(true);
        saveConfig();

        cManager.loadCrates();
        lootManager.loadData();
    }

    public PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;

        if (playerMenuUtilityMap.containsKey(p)) {
            return playerMenuUtilityMap.get(p);
        } else {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        }
    }
}
