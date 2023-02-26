package model.actor;

import model.item.Item;

public class Player extends Actor {
    public Player(String name) {
        super(name, new StatBook(40, 20, 6, 6, 2));

        // tests
        Item dropOne = new Item("Orc guts", 21, 4);
        Item dropTwo = new Item("Spider leg", 11);
        Item dropThree = new Item("Tea bags", 88, 1);
        this.bag.add(dropThree);
        this.bag.add(dropOne);
        this.bag.add(dropTwo);

        Equippable longSword = new Equippable("Longsword", 150,
                new StatBook(0, 0, 5, 3, 1),
                new ActorSlots[]{ActorSlots.MAIN_HAND});

        this.equip(ActorSlots.MAIN_HAND, longSword);
        this.unequip(ActorSlots.MAIN_HAND);
    }
}
