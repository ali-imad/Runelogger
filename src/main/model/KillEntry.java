package model;

import model.persistence.Writable;
import org.json.JSONObject;

/*
    Read only struct that handles storing KillEntry values, and ties the KillEntry to a Boss in the BossLog
 */
public class KillEntry implements Writable {
    private final int time; // time in seconds
    //    public Map<Integer, Item> drops; // drops from the kill
    private final int value; // kill drop value
    private final Boss killed; // the boss this entry is tied to

    // EFFECTS: Generate a KillEntry tied to a Boss with that kills kill time (in seconds) and value (in gold)
    public KillEntry(Boss b, int time, int value) {
        this.killed = b;
        this.time = time;
        this.value = value;
    }

    // EFFECTS: Get the Boss this kill is tied to
    public Boss getBoss() {
        return this.killed;
    }

    // EFFECTS: Get the time of the kill, in seconds
    public int getTime() {
        return this.time;
    }

    // EFFECTS: Get the value of the kill, in gp
    public int getValue() {
        return this.value;
    }

    @Override
    // EFFECTS: Return a JSONObject representing the kill entry with a comparable reference to the boss
    public JSONObject toJson() {
        JSONObject entryAsJson = new JSONObject();
        entryAsJson.put("time", getTime());
        entryAsJson.put("value", getTime());
        entryAsJson.put("killed", getBoss().getName());
        return entryAsJson;
    }
}
