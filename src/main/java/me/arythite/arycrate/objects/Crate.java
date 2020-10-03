package me.arythite.arycrate.objects;

public class Crate {

    String crateName;
    String lootTable;
    String animation;
    String keyName;

    public Crate(String crateName, String lootTable, String animation, String keyName) {
        this.crateName = crateName;
        this.lootTable = lootTable;
        this.animation = animation;
        this.keyName = keyName;
    }

    public String getCrateName() {
        return crateName;
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

}
