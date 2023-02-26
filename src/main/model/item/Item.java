package model.item;

import model.Renderable;

public class Item implements Renderable {
    private final String name; // name of item
    private final int value; // sell value
    private int quantity; // amount of the Item in this object. effectively a scalar multiple

    public Item(String name, int value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }
    public Item(String name, int value) {
        this.name = name;
        this.value = value;
        this.quantity = 1;
    }

    @Override
    public String renderString() {
        return String.format("%s - $%d (x%d)", getName(), getValue(), getQuantity());
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEquippable() {
        return false;
    }
}
