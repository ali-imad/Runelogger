package ui;

import model.BossLog;
import ui.gui.MainWindowSwing;

public class Main {
    // EFFECTS: Initialize and start rendering the boss log application, adding in some sample entries
    public static void main(String[] args) {
        BossLog log = new BossLog();

        // TEST PREFILLS
//        log.addNewBoss("Vorkath");
//        log.addNewBoss("Giant Mole");
//        log.addNewBoss("Zulrah");
//        log.addNewEntry(0, 108, 42893);
//        log.addNewEntry(2, 78, 18893);
//        log.addNewEntry(1, 149, 52893);
//        log.addNewEntry(1, 179, 60023);
//        log.addNewEntry(2, 68, 30893);
//        log.addNewEntryByName("Vorkath", 108, 42893);
//        log.addNewEntryByName("Zulrah", 78, 18893);
//        log.addNewEntryByName("Vorkath", 128,72893);
//        log.addNewEntryByName("Giant Mole", 149, 52893);
//        log.addNewEntryByName("Bandos", 56, 18229);
//        log.addNewEntryByName("Giant Mole", 124, 60023);
//        log.addNewEntryByName("Zulrah", 68, 30893);
//        MainWindow main = new MainWindowConsole(log);
        MainWindow main = new MainWindowSwing(log);
        main.start();
    }
}
