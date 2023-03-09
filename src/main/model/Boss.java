package model;

import java.util.ArrayList;
import java.util.Objects;

/*
    A Boss Object that serves as the base categorization for our BossLog. KillEntries are tied to Boss's and each Boss
    stores references to all of their logged kills with appropriate stats.
 */
public class Boss {
    private final ArrayList<KillEntry> kills;  // KillEntry's corresponding to this boss
    private final String name; // name of the boss, must be unique
    private int averageKillValue;  // average value of loot dropped by killing this boss
    private int averageTimeToKill;  //  average time to kill, in seconds

    // EFFECTS: Generate a Boss object to store KillEntry's and appopriate stats.
    //          Fields are initialized to 0 indicating no entries.
    public Boss(String name) {
        this.name = name;
        this.averageTimeToKill = 0;
        this.averageKillValue = 0;
        this.kills = new ArrayList<>();
    }

    @Override
    // EFFECTS: Hash Boss objects based on their name
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    // EFFECTS: Compare Boss objects by determining if they have the same name
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }

        Boss boss = (Boss) o;
        return getName().equals(boss.getName());
    }

    // EFFECTS: Return the name of the boss as a String
    public String getName() {
        return name;
    }

    // MODIFIES: this.averageTimeToKill, this.averageKillValue
    // EFFECTS: Add a new kill entry and update boss average values.
    public void add(KillEntry killEntry) {
        this.kills.add(killEntry);
        this.updateAverages();
    }

    // MODIFIES: this.averageTimeToKill, this.averageKillValue
    // EFFECTS: Update fields containing the average values by reiterating through all entries for the Boss
    //          Fields are updated with integer division
    // TODO: change from integer to float division
    private void updateAverages() {
        int totalKillTime = 0;
        int totalKillValue = 0;
        for (KillEntry k : this.kills) {
            totalKillTime += k.getTime();
            totalKillValue += k.getValue();
        }
        this.averageTimeToKill = totalKillTime / getKillCount();
        this.averageKillValue = totalKillValue / getKillCount();
    }

    // EFFECTS: Return the amount of kills logged for this boss
    public int getKillCount() {
        return this.kills.size();
    }

    // EFFECTS: Return the average time to kill this boss
    public int getAvgTime() {
        return averageTimeToKill;
    }

    // EFFECTS: Return the average value of a kill with this boss
    public int getAvgValue() {
        return averageKillValue;
    }

    // REQUIRES: this.kills.contain(entry)
    // EFFECTS: Return the average value of a kill with this boss. Throws if no such entry exists
    public void removeEntry(KillEntry entry) {
        // TODO: Remove REQUIRES and test the throw
//        if (!this.kills.contains(entry)) {
//            throw new RuntimeException("No such Entry for this boss!");
//        }
        this.kills.remove(entry);
    }
}
