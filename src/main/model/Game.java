package model;

import com.googlecode.lanterna.input.KeyType;

public class Game {
    private static boolean gameIsRunning;
    private final String title;
    private final Actor player;  // TODO: convert to Singleton Player class
    static private World world;

    public Game(String name, int mapWidth, int mapHeight) {
        this.title = name;
        this.player = new Actor('@', "Player", mapWidth / 2, mapHeight / 2);
        world = new World(this.player, mapWidth, mapHeight);
    }

    public static boolean isGameIsRunning() {
        return gameIsRunning;
    }

    public static void killGame() {
        gameIsRunning = false;
    }

    // MODIFIES: this
    // EFFECTS: reset the game object and set all necessary parameters to restart the game
    public void init() {
        int w = this.getWorld().getMap().getShape()[0];
        int h = this.getWorld().getMap().getShape()[1];
        world = new World(this.player, w, h);

        world.setBasicMap();
        gameIsRunning = true;
    }

    // REQUIRES: must be run in game loop. this.init() must be run before this.run()
    // MODIFIES: this
    // EFFECTS: Run the game loop and necessary game system logic to play the game
    public void run() {

    }

    public World getWorld() {
        return world;
    }

    public Actor getPlayer() {
        return this.player;
    }

    public String getTitle() {
        return title;
    }

    public void processInput(char key) {
        switch (key) {
            case ('h'):
                getWorld().moveActorAndCollide(getPlayer(), -1, 0);
                break;
            case ('j'):
                getWorld().moveActorAndCollide(getPlayer(), 0, 1);
                break;
            case ('k'):
                getWorld().moveActorAndCollide(getPlayer(), 0, -1);
                break;
            case ('l'):
                getWorld().moveActorAndCollide(getPlayer(), 1, 0);
                break;
            default:
                break;
        }
    }

    public void processInput(KeyType kt) {
        switch (kt) {
            case Escape:
                killGame();
                break;
            default:
                break;
        }
    }
}

