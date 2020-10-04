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

    public void setCrate(String crateName, String lootTable, String animation, String keyName) {
        setCrateName(crateName);
        setLootTable(lootTable);
        setAnimation(animation);
        setKeyName(keyName);
    }

    public void setLootTable(String lootTable) {
        this.lootTable = lootTable;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public void setCrateName(String crateName) {
        this.crateName = crateName;
    }
}
