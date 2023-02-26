package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class KillEntry {
    private final int time; // time in seconds
//    public Map<Integer, Item> drops; // drops from the kill
    private final int value; // kill drop value
    private final Boss killed;

//    public KillEntry(int time, Item[] drops) {
//        this.time = time;
//        this.drops = new HashMap<>();
//        for (Item i : drops) {
//            this.drops.put(i.getID(), i);
//        }
//    }

    public Boss getBoss() {
        return this.killed;
    }

    public KillEntry(Boss b, int time, int value) {
        this.killed = b;
        this.time = time;
        this.value = value;
    }

//    public void addDrop(Item i) {
//        if (Objects.isNull(this.drops.get(i.getID()))) {
//            this.drops.put(i.getID(), i);
//        } else {
//            this.drops.get(i.getID()).addQuantity(i.getQuantity()); // update quantity
//        }
//    }

    public int getTime() {
        return this.time;
    }

    public int getValue() {
        return this.value;
    }
}
