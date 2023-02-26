package ui;

import model.Game;
import model.actor.Stats;
import model.item.Item;
import model.actor.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    Class responsible for generating the game view
 */
public class GameWindow {
    private static Game game;
    private static Scanner input;
    private static Player player;

    // EFFECTS: Create a GameWindow to render and input handle the Game
    public GameWindow(Game g) {
        game = g;
        input = new Scanner(System.in);
        input.useDelimiter("\n"); // parse input with enter
    }

    // EFFECTS: Start the game, initializing and running the game loop as necessary
    public void start() {

        // if they choose to play the game, we run the game loop and respond to events
        while (Game.isRunning()) {
            renderGameState();
        }
    }

    // EFFECTS: Render the game appropriately depending on what's going on in the game
    private void renderGameState() {
        switch (Game.getState()) {
            case INIT:
                // start the game, the client can only make a new game or quit
                displayStartMenu();
                return;
            case EXIT_GAME:
                endGame();
                return;
            case CAMPFIRE:
                displayCampfireMenu();
                return;
            case SELL:
                displayBackpack();
                displayGrinderMenu();
            default:
                return;
        }
    }

    // REQUIRES: player.items.capacity < 255
    private void displayGrinderMenu() {
        System.out.println("Which item would you like to grind for gold?");
        Item[] items = player.getBag().getItems();
        List<MenuOption> optionsList = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            optionsList.add(new MenuOption((char) (i + '0'), items[i].renderString()));
        }
        optionsList.add(new MenuOption('c', "Go back to the campfire"));
        char choice = parseAndDisplayOptions(optionsList.toArray(new MenuOption[0]));
        if (choice == 'c') {
            game.goToCampfire();
            return;
        }

        int choiceIdx = Character.getNumericValue(choice);
        Item itemChoice = items[choiceIdx];

        displaySellItemsMenu(itemChoice);
    }

    private void displaySellItemsMenu(Item choice) {
        System.out.printf("How many of %s would you like to sell? ($%d each, max %d)\t",
                choice.getName(),
                choice.getValue(),
                choice.getQuantity());
        int amount = parseQuantity(choice.getQuantity());
        game.sendSellAction(choice, amount);
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
        }
        while (true);
    }

    private void displayGameShop() {

    }

    private void displayBackpack() {
        System.out.printf("%s's backpack\t\t$%d%n================%n", player.getName(), player.getBag().getGold());
        for (Item i : player.getBag().getItems()) {
            System.out.println(i.renderString());
        }
    }
    private void displayEquippables() {
        System.out.printf("%s's stored equippables\t\t$%d%n================%n", player.getName(), player.getBag().getGold());
        for (Item i : player.getBag().getItems()) {
            if (i.isEquippable()) {
                System.out.println(i.renderString());
            }
        }
    }

    private void displayCampfireMenu() {

        System.out.println("Please select from the following options:");
        MenuOption[] options = {
                new MenuOption('a', "Go out on an adventure"),
                new MenuOption('b', "Switch out equipment"),
                new MenuOption('k', "View your stats"),
//                new MenuOption('s', "Go to the store"),
                new MenuOption('r', "Sleep until tomorrow"),
                new MenuOption('g', "Grind items for gold"),
                new MenuOption('q', "Quit game")
        };

        char choice = parseAndDisplayOptions(options);

        switch (choice) {
            case 'a':
                game.goOnAdventure();
                return;
            case 'b':
                displayBackpack();
                return;
            case 'r':
                game.goToBed();
                return;
            case 'k':
                displayPlayerStats();
                return;
//            case 's':
//                game.goToShop();
//                return;
            case 'g':
                game.goToGrinder();
                return;
            case 'q':
                endGame();
                break;
            default:
                System.out.println("That wasn't supposed to happen!");
                displayStartMenu();
        }
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
        System.out.printf("Welcome to %s, adventure awaits!%n", Game.getTitle());
        System.out.println("Please select from the following options:");
        MenuOption[] startOptions = {
                new MenuOption('n', "New Game"),
                new MenuOption('q', "Quit Game")
        };

        switch (parseAndDisplayOptions(startOptions)) {
            case 'n':
                initializeNewGame();
                return;
            case 'q':
                endGame();
                break;
            default:
                System.out.println("That wasn't supposed to happen!");
                displayStartMenu();
        }
    }

    private void initializeNewGame() {
        System.out.println("Let's generate a warrior for you to play!");
        game.createPlayer(parseStringFromField("What's your name?"));

        player = Game.getPlayer();
        game.goToCampfire();
    }

    private void displayPlayerStats() {
        System.out.printf("Hello %s!%n", player.getName());
        System.out.printf("You are a %d level warrior with %d exp left to level up.%n", player.getLevel(), 0);

        System.out.println("========= STATS =========");
        System.out.printf("|| HP  ||\t\t %d (%d)%n", player.getTotalStat(Stats.HP), player.getStats().getMhp());
        System.out.printf("|| MP  ||\t\t %d (%d)%n", player.getTotalStat(Stats.MP), player.getStats().getMmp());
        System.out.printf("|| ATK ||\t\t %d (%d)%n", player.getTotalStat(Stats.ATK), player.getStats().getAtk());
        System.out.printf("|| STR ||\t\t %d (%d)%n", player.getTotalStat(Stats.WIS), player.getStats().getAtk());
        System.out.printf("|| DEF ||\t\t %d (%d)%n", player.getTotalStat(Stats.DEF), player.getStats().getAtk());
        System.out.println("=========================");
    }

    private String parseStringFromField(String field) {
        String inputString;
        do {
            System.out.printf(field + ": \t");
            inputString = input.next();
        } while (!(inputString.length() > 0));
        return inputString;
    }

    private void endGame() {
        System.out.println("Goodbye!");
        game.kill();
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

}
