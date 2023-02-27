package model;

import java.util.ArrayList;

public class BossLog {
    private final ArrayList<Boss> bosses;
    private final ArrayList<KillEntry> kills;  // store all the entries generated by BossLog

    // EFFECTS: initialize the application with Bosses
    public BossLog() {
        this.bosses = new ArrayList<>();
        this.kills = new ArrayList<>();
        Boss vorkath = new Boss("Vorkath");
        Boss giantMole = new Boss("Giant Mole");
        Boss zulrah = new Boss("Zulrah");
        this.bosses.add(vorkath);
        this.bosses.add(giantMole);
        this.bosses.add(zulrah);
    }

//    public void addNewEntry(int bossIdx, int time, Item[] drops) {
//        Boss boss = this.bosses.get(bossIdx);
//        KillEntry newEntry = new KillEntry(time, drops);
//        boss.add(newEntry);
//        this.kills.add(newEntry);
//    }

    // REQUIRES: this.kills.size() < i
    // MODIFIES: this.boss.get(i), this.kills
    // EFFECTS: Generate and add a new entry to the BossLog, updating the appropriate boss
    public void addNewEntry(int bossIdx, int time, int value) {
        Boss boss = this.bosses.get(bossIdx);
        KillEntry newEntry = new KillEntry(boss, time, value);
        boss.add(newEntry);
        this.kills.add(newEntry);
    }

    public Boss[] getBosses() {
        return this.bosses.toArray(new Boss[0]);
    }

    public KillEntry getEntry(int x) {
        return this.kills.get(x);
    }

    // REQUIRES: this.kills.size() < i
    // MODIFIES: this.boss.get(i), this.kills
    // EFFECTS: Remove an entry from the BossLog
    public void removeEntry(int i) {
        KillEntry entry = getEntry(i);
        entry.getBoss().removeEntry(entry);
        this.kills.remove(entry);
    }

    public KillEntry getFromEnd(int x) {
        return this.kills.get(this.kills.size() - 1 - x);
    }

    // EFFECTS: Returns the total number of logged kills
    public int getTotalKills() {
        return this.kills.size();
    }
}
