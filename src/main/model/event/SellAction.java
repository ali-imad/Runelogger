package model.event;

import model.Game;
import model.item.Item;
import model.actor.Player;

public class SellAction implements GameAction {
    private Item sold;
    private int amount;
    private static Player player;

    public SellAction(Item i, int n) {
        this.sold = i;
        this.amount = n;
        player = Game.getPlayer();
    }

    @Override
    public void action() {
        player.getBag().drop(this.sold, this.amount);
        player.getBag().add(this.amount * this.sold.getValue());
    }
}
