package model;

import java.util.ArrayList;

public class Boss {
    private int averageKillValue;
    private ArrayList<KillEntry> kills;
    private int averageTimeToKill;

    public String getName() {
        return name;
    }

    private String name;

    public Boss(String name) {
        this.name = name;
        this.averageTimeToKill = -1;
        this.averageKillValue = -1;
        this.kills = new ArrayList<>();
    }

    public void add(KillEntry killEntry) {
        this.kills.add(killEntry);
        this.updateAverages();
    }

    public int getKillCount() {
        return this.kills.size();
    }

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

    public int getAvgTime() {
        return averageTimeToKill;
    }

    public int getAvgValue() {
        return averageKillValue;
    }
}
