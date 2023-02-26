package model.encounters;

import model.event.ResultAction;
import model.item.Item;

import java.util.Random;

public class RewardEncounter extends Encounter {
    protected String name;  //
    protected String desc; // description of encounter
    private static Random rng = new Random();

    public RewardEncounter(String name, String description, Item[] itemRewards, int goldReward) {
        this.name = name;
        this.desc = description;
        this.results = new RewardAction(itemRewards, goldReward);
    }

    @Override
    public void runEncounter() {
        int fate = rng.nextInt(100);
        boolean isSuccess = fate < 70;
        boolean isTrap = fate < 76; // 6% chance of trap
        // otherwise nothing

        if (isSuccess) {
            results.setResult(1);
        } else if (isTrap) {
            results.setResult(2);
        } else {
            results.setResult(0);
        }
    }
}
