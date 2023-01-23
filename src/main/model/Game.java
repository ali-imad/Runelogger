package model;

public class Game {
    private final String title;
    Player player;
    World world;
    final int MAP_WIDTH = 80;
    final int MAP_HEIGHT = 50;

    public Game(String name) {
        this.title = name;
    }

    // MODIFIES: this
    // EFFECTS: Initialize/reset the game object and set all necessary parameters to start the game
    public void init() {
    }

    // REQUIRES: this.init() must be run before this.run()
    // MODIFIES: this
    // EFFECTS: Run the game loop and necessary game system logic to play the game
    public void run() {

    }
}
