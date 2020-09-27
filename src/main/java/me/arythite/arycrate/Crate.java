package me.arythite.arycrate;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Crate {

    String crateName;
    String displayName;
    String lootTable;
    String animation;
    String keyName;
    List<Location> locations;

    public Crate(String displayName, String crateName, String lootTable, String animation, String keyName, List<Location> locations) {
        this.crateName = crateName;
        this.displayName = displayName;
        this.lootTable = lootTable;
        this.animation = animation;
        this.keyName = keyName;
        this.locations = locations;
    }

    public String getCrateName() {
        return crateName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLootTable() {
        return lootTable;
    }

    public String getAnimation() {
        return animation;
    }

    public String getKeyName() {
        return keyName;
    }

    public List<Location> getLocation() {
        return locations;
    }
}
