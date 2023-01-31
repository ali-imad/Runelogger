package model;

public class Game {
    private final String title;
    private final Actor player;  // TODO: convert to Singleton Player class
    static private World world;

    public Game(String name, int mapWidth, int mapHeight) {
        this.title = name;
        this.player = new Actor('@', "Player", mapWidth / 2, mapHeight / 2);
        world = new World(this.player, mapWidth, mapHeight);
    }

    // MODIFIES: this
    // EFFECTS: reset the game object and set all necessary parameters to restart the game
    public void init() {
        int w = this.getWorld().getMap().getShape()[0];
        int h = this.getWorld().getMap().getShape()[1];
        world = new World(this.player, w, h);

        world.setBasicMap();
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
}

