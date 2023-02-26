package model;

import model.item.Backpack;

public class Shop {
    private final Backpack inventory;
    private final String shopkeeper;

    public Shop(Backpack inventory, String shopkeeper) {
        this.inventory = inventory;
        this.shopkeeper = shopkeeper;
    }

    public Backpack getInventory() {
        return inventory;
    }

    public String getShopkeeper() {
        return shopkeeper;
    }
}
