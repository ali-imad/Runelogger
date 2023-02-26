package ui;

import model.BossLog;

public class Main {
    public static void main(String[] args) {
        BossLog log = new BossLog();

        // TEST PREFILLS
        log.addNewEntry(0, 108, 42893);
        log.addNewEntry(2, 78, 18893);
        log.addNewEntry(1, 149, 52893);
        log.addNewEntry(1, 179, 60023);
        log.addNewEntry(2, 68, 30893);
        MainWindow main = new MainWindow(log);
        main.start();
    }
}
