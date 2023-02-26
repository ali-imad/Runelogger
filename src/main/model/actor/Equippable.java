package model.actor;

import model.item.Item;

import static model.actor.ActorSlots.*;

public class Equippable extends Item {
    private final StatBook effects;
    private final ActorSlots[] valid;

    public Equippable(String name, int value, ActorSlots[] valid) {
        super(name, value);
        this.effects = StatBook.makeEmptyStatbook();
        this.valid = valid;
    }

    public Equippable(String name, int value, StatBook effects, ActorSlots[] valid) {
        super(name, value);
        this.effects = effects;
        this.valid = valid;
    }

    public static Equippable makeNothing() {
        return new Equippable("Nothing", 0, new ActorSlots[]{CHEST,
                FEET,
                HEAD,
                LEGS,
                MAIN_HAND,
                OFF_HAND});
    }

    public ActorSlots[] getValid() {
        return valid;
    }

    public int getEffect(Stats stat) {
        return this.effects.getStat(stat);
    }

    @Override
    public boolean isEquippable() {
        return true;
    }

    @Override
    public String renderString() {
        StringBuilder validSlots = new StringBuilder();
        for (ActorSlots slot : this.valid) {
            validSlots.append(slot.toString() + ", ");
        }
        String validSlotsString = validSlots.toString();
        validSlotsString = validSlotsString.substring(0, validSlotsString.length() - 2);
        return String.format("%s - $%d [%s] (x%d)", getName(), getValue(), validSlotsString, getQuantity());
    }
}
