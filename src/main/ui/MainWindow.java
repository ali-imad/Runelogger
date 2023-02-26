package ui;

import model.Boss;
import model.BossLog;
import model.KillEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.RenderState.MENU;

/*
    Class responsible for generating the game view
 */
public class MainWindow {
    private static BossLog log;
    private static Scanner input;
    private static boolean isRunning = true;
    private static RenderState state;

    // EFFECTS: Create a MainWindow to render and input handle the Game
    public MainWindow(BossLog l) {
        log = l;
        input = new Scanner(System.in);
        input.useDelimiter("\n"); // parse input with enter
        state = MENU;
    }

    // EFFECTS: Start the game, initializing and running the game loop as necessary
    public void start() {
        // if they choose to play the game, we run the game loop and respond to events
        while (isRunning) {
            renderGameState();
        }
        System.exit(0);
    }

    // EFFECTS: Render the game appropriately depending on what's going on in the game
    private void renderGameState() {
        switch (state) {
            case MENU:
                // start the log and send to other menus
                displayStartMenu();
                return;
            case EXIT:
                exitBossLog();
                return;
            case BOSS_VIEW:
                displayBosses();
                return;
            case RECENT_VIEW:
                displayLastXEntries(5);
                return;
            case NEW_ENTRY:
                displayNewEntryView();
            default:
        }
    }

    private void displayNewEntryView() {
        System.out.println("Lets log a new entry!");

        System.out.println("What boss did you kill?");
        Integer bossIdx = getBossChoice();
        if (bossIdx == null) {
            return;
        }

        System.out.println("How long was your kill? (seconds, max 999)");
        int timeToKill = parseQuantity(999);

        System.out.println("How much was the loot dropped? (gold, max 999999999)");
        int killValue = parseQuantity(999999999);

        log.addNewEntry(bossIdx, timeToKill, killValue);
        System.out.printf("Entry #%d logged!%n", log.getTotalKills());
    }

    private Integer getBossChoice() {
        List<MenuOption> optionsList = new ArrayList<>();
        for (int i = 0; i < log.getBosses().length; i++) {
            Boss b = log.getBosses()[i];
            optionsList.add(new MenuOption((char) (i + '0'), b.getName()));
        }
        optionsList.add(new MenuOption('b', "Go back to the menu"));
        char choice = parseAndDisplayOptions(optionsList.toArray(new MenuOption[0]));
        if (choice == 'b') {
            state = MENU;
            return null;
        }

        int bossIdx = Character.getNumericValue(choice);
        return bossIdx;
    }

    // REQUIRES: log.kills.size() > x
    private void displayLastXEntries(int x) {
        System.out.printf("Here are the last %d kills.%n", x);
        for (int i = 0; i < x; i++) {
            KillEntry entryToRender = log.getMostRecent(i);
            System.out.printf("Kill #%d: BOSS - %s TIME - %d VALUE - %d%n", log.getTotalKills() - i,
                    entryToRender.getBoss().getName(), entryToRender.getTime(), entryToRender.getValue());
        }
        state = MENU;
    }

    private void displayBosses() {
        System.out.println("Which boss would you like to view?");
        Integer bossIdx = getBossChoice();
        if (bossIdx == null) {
            return;
        }
        Boss toView = log.getBosses()[bossIdx];
        displayBoss(toView);
    }

    private void displayBoss(Boss toView) {
        System.out.printf("%s -- Kills: %d -- Avg kill time: %d -- Avg kill value $%d%n",
                toView.getName(), toView.getKillCount(), toView.getAvgTime(), toView.getAvgValue());
    }

    private int parseQuantity(int max) {
        String in;
        do {
            in = input.next();
            if (in.isEmpty()) {
                return 0;
            } else if (in.matches("[0-9]+")) {
                int inInt = Integer.parseInt(in);
                if (inInt <= max) {
                    return inInt;
                }
            }
            System.out.println("Please enter a valid number.");
        } while (true);

    }

    // EFFECTS: Returns a choice based on the passed menu options
    private char parseAndDisplayOptions(MenuOption[] startOptions) {
        List<Character> valid = new ArrayList<>();

        for (MenuOption m : startOptions) {
            System.out.printf("%s - %s\t", m.getButton(), m.getDescription());
            valid.add(m.getButton());
        }

        return parseInputFromChoice(valid);
    }

    private void displayStartMenu() {
        System.out.printf("Welcome to the OSRS Boss Log!%n");
        System.out.println("Please select from the following options:");
        MenuOption[] startOptions = {
                new MenuOption('n', "New entry"),
                new MenuOption('v', "View boss log"),
                new MenuOption('p', "View last 5 entries"),
                new MenuOption('q', "Quit Game")
        };

        switch (parseAndDisplayOptions(startOptions)) {
            case 'n':
                state = RenderState.NEW_ENTRY;
                return;
            case 'v':
                state = RenderState.BOSS_VIEW;
                return;
            case 'p':
                state = RenderState.RECENT_VIEW;
                return;
            case 'q':
                exitBossLog();
                return;
            default:
                System.out.println("That wasn't supposed to happen!");
                displayStartMenu();
        }
    }

    private void exitBossLog() {
        isRunning = false;
    }
//
//    private String parseStringFromField(String field) {
//        String inputString;
//        do {
//            System.out.printf(field + ": \t");
//            inputString = input.next();
//        } while (!(inputString.length() > 0));
//        return inputString;
//    }

    private char parseInputFromChoice(List<Character> valid) {
        System.out.println();
        boolean decided = false;
        String decision = input.next();
        while (!decided) {
            if (decision.length() != 1 || !valid.contains(decision.toCharArray()[0])) {
                System.out.println("You did not select a valid option.");
                decision = input.next();
            } else {
                decided = true;
            }
        }
        return decision.toCharArray()[0];
    }

}
