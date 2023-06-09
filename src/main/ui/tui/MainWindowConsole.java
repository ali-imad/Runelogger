package ui.tui;

import model.Boss;
import model.BossLog;
import model.KillEntry;
import ui.MainWindow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ui.RenderState.*;

/*
    Class responsible for generating the game view using cout
 */
public class MainWindowConsole extends MainWindow {
    // EFFECTS: Create a MainWindowConsole to render and input handle the BossLog
    public MainWindowConsole(BossLog l) {
        super(l);
    }

    // EFFECTS: Start the application, initializing and running the main loop as necessary
    @Override
    public void start() {
        while (isRunning) {
            renderLogState();
        }
        // exit once it is finished running
        System.exit(0);
    }

    // MODIFIES: System.out
    // EFFECTS: Render the application and generate an appropriate view depending on its state
    private void renderLogState() {
        switch (state) {
            case MENU:
                // start the log and send to other menus
                displayStartMenu();
                return;
            case EXIT:
                exitBossLog();
                return;
            case REMOVE:
                displayRemoveMenu();
                return;
            case BOSS_VIEW:
                displayBosses();
                return;
            case RECENT_VIEW:
                displayLastXEntries(5);
                return;
            case NEW_ENTRY:
                displayNewEntryView();
                return;
            default:
                throw new RuntimeException("No valid state provided");
        }
    }

    // MODIFIES: log, state
    // EFFECTS: Parse and remove an entry from the boss log
    private void displayRemoveMenu() {
        System.out.printf("Which entry did you want to remove? (max idx: %s)%n", log.getTotalKills() - 1);
        int entryIdx = parseQuantity(log.getTotalKills() - 1);
        log.removeEntry(entryIdx);
        state = MENU;
    }

    // MODIFIES: log, state
    // EFFECTS: Parse and add a new KillEntry to log
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

    // EFFECTS: Returns an integer corresponding to the index of the boss in log.bosses
    private Integer getBossChoice() {
        List<MenuOptionConsole> optionsList = new ArrayList<>();
        int lastIdx = 0;
        for (int i = 0; i < log.getBosses().length; i++) {
            Boss b = log.getBosses()[i];
            optionsList.add(new MenuOptionConsole((char) (i + '0'), b.getName()));
            lastIdx = i;
        }
        optionsList.add(new MenuOptionConsole('n', "New boss"));
        optionsList.add(new MenuOptionConsole('b', "Go back to the menu"));
        char choice = parseAndDisplayOptions(optionsList.toArray(new MenuOptionConsole[0]));
        if (choice == 'b') {
            state = MENU;
            return null;
        }

        if (choice == 'n') {
            addNewBoss();
            return ++lastIdx;
        }

        return Character.getNumericValue(choice);
    }

    // MODIFIES: log
    // EFFECTS: Add a new boss to the log
    private void addNewBoss() {
        String bossName = parseStringFromField("What is the name of the boss you would like to add?");
        log.addNewBoss(bossName);
    }

    // EFFECTS: Show the most recent x KillEntry's logged, or all if x > log.kills.size()
    private void displayLastXEntries(int x) {
        x = Math.min(log.getTotalKills(), x);
        System.out.printf("Here are the last %d kills.%n", x);
        for (int i = 0; i < x; i++) {
            KillEntry entryToRender = log.getFromEnd(i);
            System.out.printf("Kill #%d: BOSS - %s TIME - %d VALUE - %d%n", log.getTotalKills() - i,
                    entryToRender.getBoss().getName(), entryToRender.getTime(), entryToRender.getValue());
        }
        state = MENU;
    }

    // EFFECTS: Display all bosses and ask for input to show detailed boss information
    private void displayBosses() {
        System.out.println("Which boss would you like to view?");
        Integer bossIdx = getBossChoice();
        if (bossIdx == null) {
            return;
        }
        Boss toView = log.getBosses()[bossIdx];
        displayBoss(toView);
    }

    // EFFECTS: Parse and show detailed information about a specific boss in log
    private void displayBoss(Boss toView) {
        System.out.printf("%s -- Kills: %d -- Avg kill time: %d -- Avg kill value $%d%n",
                toView.getName(), toView.getKillCount(), toView.getAvgTime(), toView.getAvgValue());
    }

    // EFFECTS: Parse soem number input by the character, up to max. Will not return until the input is valid
    private int parseQuantity(int max) {
        String in = input.next();
        do {
            if (in.matches("[0-9]+")) {
                int inInt = Integer.parseInt(in);
                if (inInt <= max) {
                    return inInt;
                }
            }
            System.out.println("Please enter a valid number.");
            in = input.next();
        } while (true);

    }

    // EFFECTS: Returns a choice based on the passed menu options
    private char parseAndDisplayOptions(MenuOptionConsole[] startOptions) {
        List<Character> valid = new ArrayList<>();

        for (MenuOptionConsole m : startOptions) {
            System.out.printf("%s - %s\t", m.getButton(), m.getLabel());
            valid.add(m.getButton());
        }

        return parseInputFromChoice(valid);
    }

    // MODIFIES: state
    // EFFECTS: the main menu for the application. This sets state for future views
    private void displayStartMenu() {
        System.out.printf("Welcome to the OSRS Boss Log!%n");
        System.out.println("Please select from the following options:");
        MenuOptionConsole[] startOptions = {
                new MenuOptionConsole('n', "New entry"),
                new MenuOptionConsole('r', "Remove entry"),
                new MenuOptionConsole('v', "View boss log"),
                new MenuOptionConsole('p', "View last 5 entries"),
                new MenuOptionConsole('s', "Save the currently logged entries"),
                new MenuOptionConsole('l', "Load previously saved entries"),
                new MenuOptionConsole('q', "Quit logger")
        };

        char choice = parseAndDisplayOptions(startOptions);

        setNewStateFromMenu(choice);
    }

    // REQUIRES: state == MENU
    // EFFECTS: Change the state depending on what was chosen in the main menu screen
    @SuppressWarnings("methodlength")
    private void setNewStateFromMenu(char choice) {
        switch (choice) {
            case 'n':
                state = NEW_ENTRY;
                return;
            case 'v':
                state = BOSS_VIEW;
                return;
            case 'p':
                state = RECENT_VIEW;
                return;
            case 'q':
                exitBossLog();
                return;
            case 'r':
                state = REMOVE;
                return;
            case 'l':
                loadSavedLogFile();
                return;
            case 's':
                saveLogToFile();
                return;
            default:
                System.out.println("That wasn't supposed to happen!");
                displayStartMenu();
        }
    }

    // MODIFIES: client file system
    // EFFECTS: Tries to serialize the log and save it to a JSON file at saveLocation
    private void saveLogToFile() {
        try {
            saveLogToPath(defaultSaveLocation);
            System.out.println("File was successfully saved to " + defaultSaveLocation);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be saved to " + defaultSaveLocation);
        }
        state = MENU;
    }

    private void exitBossLog() {
        isRunning = false;
    }

    // EFFECTS: Print a field entry line and returns a string, parsing until something > 0 characters is entered
    private String parseStringFromField(String field) {
        String inputString;
        do {
            System.out.printf(field + ": \t");
            inputString = input.next();
        } while (!(inputString.length() > 0));
        return inputString;
    }

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

    // MODIFIES: MainWindowConsole.log, MainWindowConsole.state
    // EFFECTS: Load a saved session into the logger
    private void loadSavedLogFile() {
        try {
            loadLogFile();
            System.out.println("Loaded the saved session from " + defaultSaveLocation);
        } catch (IOException e) {
            System.out.println("You do not have any session saved!");
        }
        state = MENU;
    }
}
