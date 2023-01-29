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
        this.world = new World();
        this.world.initMap(MAP_WIDTH, MAP_HEIGHT);
    }

    // REQUIRES: this.init() must be run before this.run()
    // MODIFIES: this
    // EFFECTS: Run the game loop and necessary game system logic to play the game
    public void run() {
    }

    // REQUIRES: this.init() must be run before this.run()
    // MODIFIES: Terminal
    // EFFECTS: Iterate through the world map and display all the world tiles (with entities on top, if they exist)
    public void clearConsole() {
        for (int i = 0; i < MAP_HEIGHT*2; i++) {
            System.out.println();
        }
    }

    // REQUIRES: this.init() must be run before this.run()
    // MODIFIES: Terminal
    // EFFECTS: Iterate through the world map and display all the world tiles (with entities on top, if they exist)
    public void renderActiveWorld() {
        System.out.printf("========= %s =========%n", this.title);
    }
}

