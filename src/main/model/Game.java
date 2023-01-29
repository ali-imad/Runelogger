package model;

public class Game {
    static final int MAP_WIDTH = 80;
    static final int MAP_HEIGHT = 50;
    private final String title;
    Actor player;
    World world;

    public Game(String name) {
        this.title = name;
    }

    // MODIFIES: this
    // EFFECTS: Initialize/reset the game object and set all necessary parameters to start the game
    public void init() {
        this.player = new Actor('@', "Player", MAP_WIDTH / 2, MAP_HEIGHT / 2);
        this.world = new World(player);
        this.world.resetMap(MAP_WIDTH, MAP_HEIGHT);
    }

    // REQUIRES: this.init() must be run before this.run()
    // MODIFIES: this
    // EFFECTS: Run the game loop and necessary game system logic to play the game
    public void run() {
    }

}

