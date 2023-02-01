package model.game;

import com.googlecode.lanterna.input.KeyType;
import model.World;
import model.actor.Actor;

public class Game {
    private static boolean gameIsRunning;
    private final String title;
    private final Actor player;  // TODO: convert to Player class
    private static World world;

    private ConsoleMessageQueue console;

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
                this.pushConsole("Move Left");
                getWorld().moveActorAndCollide(getPlayer(), -1, 0);
                break;
            case ('j'):
                this.pushConsole("Move Down");
                getWorld().moveActorAndCollide(getPlayer(), 0, 1);
                break;
            case ('k'):
                this.pushConsole("Move Up");
                getWorld().moveActorAndCollide(getPlayer(), 0, -1);
                break;
            case ('l'):
                this.pushConsole("Move Right");
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

    public void buildConsole(int lines, int lineWidth) {
        this.console = new ConsoleMessageQueue(lines, lineWidth);
    }

    public String[] getConsole() {
        return console.getMessages().toArray(new String[0]);
    }

    public void pushConsole(String newLog) {
        console.add(newLog);
    }
}

