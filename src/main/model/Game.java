package model;

import model.actor.Player;
import model.encounters.Encounter;
import model.event.GameAction;
import model.event.GameState;
import model.event.SellAction;
import model.item.Item;

import java.util.List;

public class Game {

    private static String title;
    private static Player player;
    private static GameState state;

    private static Shop shop;
    private static List<Encounter> encounters;

    public Game(String t) {
        title = t;
        state = GameState.INIT;
    }

    public static String getTitle() {
        return title;
    }

    public static Player getPlayer() {
        return player;
    }

    public static boolean isRunning() {
        return state != GameState.EXIT_GAME;
    }

    public static GameState getState() {
        return state;
    }

    // EFFECTS: Initialize the game, allowing it to be run for the first time.
    public void init() {

    }

    // EFFECTS: Initialize and set the player based on the given parameters
    public void createPlayer(String name) {
        if (state != GameState.INIT) {
            throw new RuntimeException("Player already exists!");
        }
        player = new Player(name);
        state = GameState.WAITING;
    }

    public void run(GameAction a) {
        switch (state) {
            case EXIT_GAME:
                System.exit(0);
            case WAITING:
                return;
            case SELL:
                a.action();
                goToGrinder();
                return;
            case REST:
                this.regenerate();
            case ENCOUNTER: // process result of encounter
                a.action();
                goToCampfire();
                return;
        }

    }

    // EFFECTS: generates new encounters and a new shop
    private void regenerate() {
    }

    public void kill() {
        state = GameState.EXIT_GAME;
    }

    public void goToCampfire() {
        state = GameState.CAMPFIRE;
    }

    public void goOnAdventure() {
        state = GameState.ENCOUNTER;
    }

    public void goToShop() {
        state = GameState.SHOP;
    }

    public void goToGrinder() {
        state = GameState.SELL;
    }

    public void sendSellAction(Item choice, int amount) {
        run(new SellAction(choice, amount));
    }

    public void goToBed() {
        state = GameState.REST;
    }
}
