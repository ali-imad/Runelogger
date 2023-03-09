package model;

import model.persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/*
    The master BossLog that handles entry addition and removal, initializing and handling various Boss
    objects to store the KillEntry's in.
 */
public class BossLog implements Writable {
    private final ArrayList<Boss> bosses;  // bosses we are logging kills of
    private final ArrayList<KillEntry> kills;  // store all the entries generated by BossLog

    // EFFECTS: initialize the application with Vorkath, Giant Mole, and Zulrah available for logging
    public BossLog() {
        this.bosses = new ArrayList<>();
        this.kills = new ArrayList<>();
        // set up initial bosses to select from
        this.addNewBoss("Vorkath");
        this.addNewBoss("Giant Mole");
        this.addNewBoss("Zulrah");
    }

    // REQUIRES: this.kills.size() < i
    // MODIFIES: this.bosses.get(i), this.kills
    // EFFECTS: Generate and add a new entry to the BossLog, updating the appropriate boss by index in this.bosses
    public void addNewEntry(int bossIdx, int time, int value) {
        Boss boss = this.bosses.get(bossIdx);
        KillEntry newEntry = new KillEntry(boss, time, value);
        boss.add(newEntry);
        this.kills.add(newEntry);
    }

    // REQUIRES: this.boss.contains(bossName)
    // MODIFIES: this.bosses, this.kills
    // EFFECTS: Generate and add a new entry to the BossLog, updating the appropriate boss by name.
    //          If the boss doesn't exist, make it and add the entry!
    public void addNewEntryByName(String bossName, int time, int value) {
        Boss toFind = new Boss(bossName);
        for (Boss boss : this.bosses) {
            if (boss.equals(toFind)) {
                KillEntry newEntry = new KillEntry(boss, time, value);
                boss.add(newEntry);
                this.kills.add(newEntry);
                return;
            }
        }

        // we could not find the boss in the list, so make a new boss and add it to the logger
        this.bosses.add(toFind);
        int newBossIdx = this.bosses.size() - 1;
        addNewEntry(newBossIdx, time, value);
    }

    // EFFECTS: Returns a readonly array of the bosses to reference
    public Boss[] getBosses() {
        return this.bosses.toArray(new Boss[0]);
    }

    // REQUIRES: this.kills.size() < x
    // EFFECTS: Get an entry at a specific index
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

    // REQUIRES: this.kills.size() < x
    // EFFECTS: Return an entry from the end, where x is the index offset fro the end
    //          (2 would be 2 indexes away from the end)
    public KillEntry getFromEnd(int x) {
        return this.kills.get(this.kills.size() - 1 - x);
    }

    // EFFECTS: Returns the total number of logged kills
    public int getTotalKills() {
        return this.kills.size();
    }

    @Override
    // EFFECTS: Serializes the BossLog in its state by the kill entries in this.kills and returns it as a JSONObject
    //          Bosses are only serialized if they have a KillEntry
    public JSONObject toJson() {
        JSONObject logAsJson = new JSONObject();

        // convert each kill, in sequence, to a JSON Array
        JSONArray killsAsArray = new JSONArray();
        for (KillEntry k : kills) {
            killsAsArray.put(k.toJson());
        }

        logAsJson.put("kills", killsAsArray);
        return logAsJson;
    }

    // MODIFIES: this.bosses
    // EFFECTS: Add a new boss to the BossLog, if it doesn't already exist
    public void addNewBoss(String bossName) {
        Boss toAdd = new Boss(bossName);
        if (this.bosses.contains(toAdd)) {
            return;
        }
        this.bosses.add(new Boss(bossName));
    }
}
