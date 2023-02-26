package model.encounters;

import model.Game;
import model.Renderable;
import model.event.ResultAction;
import model.item.Item;

import java.util.Random;

public class RewardAction extends ResultAction implements Renderable {
    private Item[] items;
    private int gold;
    private static Random rng = new Random();

    public RewardAction(Item[] itemRewards, int goldReward) {
        this.items = itemRewards;
        this.gold = goldReward;
    }

    @Override
    public void action() {
        switch (this.getResult()) {
            case 0:
                return;
            case 1:
                success();
                return;
            case 2:
                failure();
                return;
        }
    }

    @Override
    protected void success() {
        Game.getPlayer().getBag().add(this.gold);
        for (Item i : this.items) {
            if (!Game.getPlayer().getBag().add(i)) {
                return;
            }
        }
    }

    @Override
    protected void failure() {
        int dmg = rng.nextInt(5) + 3; // 3 min damage, 8 max
        Game.getPlayer().damage(dmg);
    }

    @Override
    public String renderString() {
        switch (this.getResult()) {
            case 0:
                return "Player leaves the area.";
            case 1:
                return "Player finds a reward!";
            case 2:
                return "Player was caught in a booby trap!";
        }
        throw new RuntimeException("No correct result given");
    }
}
