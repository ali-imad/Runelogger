package model.actor;

import model.item.Backpack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static model.actor.ActorSlots.*;

public abstract class Actor {
    private final String name;
    protected Backpack bag;
    private int level;

    public StatBook getStats() {
        return stats;
    }

    private StatBook stats;
    private final Map<ActorSlots, Equippable> equipment;

    public Actor(String name, StatBook stats, Map<ActorSlots, Equippable> equipment, int gold) {
        this.level = 1;
        this.name = name;
        this.bag = new Backpack(gold, 20);
        this.equipment = equipment;
        this.stats = stats;
    }

    public Actor(String name, StatBook stats) {
        this.level = 1;
        this.name = name;
        this.bag = new Backpack(0, 20);
        this.equipment = new HashMap<>();
        this.stats = stats;
//        this.equipment.put(HEAD, Equippable.makeNothing());
//        this.equipment.put(LEGS, Equippable.makeNothing());
//        this.equipment.put(CHEST, Equippable.makeNothing());
//        this.equipment.put(MAIN_HAND, Equippable.makeNothing());
//        this.equipment.put(OFF_HAND, Equippable.makeNothing());
//        this.equipment.put(FEET, Equippable.makeNothing());
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    // EFFECTS: handles the level up logic for any actor
    public void levelUp() {
        this.level++;
    }

    public Backpack getBag() {
        return this.bag;
    }

    // EFFECTS: Handles equipping an equippable to slot.
    //          Returns true if the slot was empty.
    public boolean equip(ActorSlots slot, Equippable e) {
        if (Objects.isNull(this.equipment.get(slot))) {
            this.equipment.put(slot, e);
            return true;
        }
        return false;
    }

    // EFFECTS: Handles unequipping an equipped equippable.
    //          Returns true if the slot is unequipped, false if your bag is full
    public boolean unequip(ActorSlots slot) {
        if (this.bag.isFull()) {
            return false;
        }
        this.bag.add(this.equipment.get(slot));
        this.equipment.put(slot, null);
        return true;
    }

    // EFFECTS: gets the stat with equipment bonuses
    public int getTotalStat(Stats stat) {
        int total = this.stats.getStat(stat);
        for (Equippable e : this.equipment.values()) {
            if (Objects.isNull(e)) {
                continue;
            }
            total += e.getEffect(stat);
        }
        return total;
    }

    // EFFECTS: heal the actor to full hp
    public void healToFull() {
        this.stats.setHp(this.getTotalStat(Stats.HP));
    }

    public void damage(int dmg) {
        this.stats.setHp(this.stats.getHp() - dmg);
    }

    public boolean isAlive() {
        return this.stats.getHp() > 0;
    }
}
