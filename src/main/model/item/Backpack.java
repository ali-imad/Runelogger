package model.item;

import java.util.ArrayList;

public class Backpack {
    private int gold;
    private ArrayList<Item> items;
    private int capacity;

    // EFFECTS: Create a new empty Backpack to store items,
    //          with a capacity of c and initial gold amount of g
    public Backpack(int g, int c) {
        this.capacity = c;
        this.gold = g;
        this.items = new ArrayList<>();
    }

    // MODIFIES: this.items
    // EFFECTS: add an item to the bag, returns true if item was successfully added and false if the bag is full
    public boolean add(Item i) {
        if (this.isFull()) {
            return false;
        }
        this.items.add(i);
        return true;
    }

    // MODIFIES: this.gold
    // EFFECTS: adds g amount of gold to the bag. returns false if g is <= 0
    public boolean add(int g) {
        if (g > 0) {
            this.gold += g;
            return true;
        }
        return false;
    }

    // MODIFIES: this.gold
    // EFFECTS: removes g amount of gold from the bag. returns false if g is > this.gold
    public boolean spend(int g) {
        if (g > this.gold) {
            return false;
        }
        this.gold -= g;
        return true;
    }

    // EFFECTS: get a read only list of the items, with tandem indexes to this.items
    public Item[] getItems() {
        return this.items.toArray(new Item[0]);
    }

    // REQUIRES: i < this.items.size()
    // MODIFIES: this.items
    // EFFECTS: Remove an item from the bag with a certain quantity.
    //          Return true if the item is removed from the bag
    public boolean drop(Item i, int n) {
        int idx = 0;
        for (Item x : this.items) {
            if (x == i && i.getQuantity() >= n) {
                x.setQuantity(x.getQuantity() - n);
                if (x.getQuantity() <= 0) {
                    this.items.remove(idx);
                    return true;
                }
                return false;
            }
            idx++;
        }
        throw new RuntimeException("No item found!");
    }

    public int getGold() {
        return this.gold;
    }

    public boolean isFull() {
        return this.items.size() == this.capacity;
    }
}
