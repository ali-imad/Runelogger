package ui;

import model.BossLog;
import model.Item;

public class Main {
    // TEST
    private static Item[] testLoot = new Item[]{new Item(0, 42031), new Item(1, 12378)};

    public static void main(String[] args) {
        BossLog log = new BossLog();
        log.addNewEntry(0, 108, 42893);
        log.addNewEntry(2, 78, 18893);
        log.addNewEntry(1, 149, 52893);
        log.addNewEntry(1, 179, 60023);
        log.addNewEntry(2, 68, 30893);
        MainWindow main = new MainWindow(log);
        main.start();
    }
}
