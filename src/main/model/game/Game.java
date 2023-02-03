package model.game;

import com.googlecode.lanterna.input.KeyType;
import model.game.world.World;
import model.game.actor.Actor;
import model.game.actor.Player;

import static model.game.GameEvent.*;

public class Game {
    private static boolean gameIsRunning;
    private static World world;
    private final String title;
    private final Player player;  // TODO: convert to Player class
    private ConsoleMessageQueue console;
    private GameEvent nextEvent;

    public Game(String name, int mapWidth, int mapHeight) {
        this.title = name;
//        this.player = new Player(  mapWidth / 2, mapHeight / 2);
        this.player = new Player(mapWidth / 10, mapHeight / 10);
        world = new World(this.player, mapWidth, mapHeight);
        this.nextEvent = REST;
    }

    public static boolean isGameIsRunning() {
        return gameIsRunning;
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

    public World getWorld() {
        return world;
    }

    // REQUIRES: must be run in game loop. this.init() must be run before this.run()
    // MODIFIES: this
    // EFFECTS: Run the game loop and necessary game system logic to play the game
    public void run() {
        switch (this.nextEvent) {
            case MOVE_RIGHT:
                getWorld().moveActorAndCollide(getPlayer(), 1, 0);
                break;
            case MOVE_DOWN:
                getWorld().moveActorAndCollide(getPlayer(), 0, 1);
                break;
            case MOVE_UP:
                getWorld().moveActorAndCollide(getPlayer(), 0, -1);
                break;
            case MOVE_LEFT:
                getWorld().moveActorAndCollide(getPlayer(), -1, 0);
                break;
            default:
        }
        this.nextEvent = REST;
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
                this.nextEvent = MOVE_LEFT;
                break;
            case ('j'):
                this.pushConsole("Move Down");
                this.nextEvent = MOVE_DOWN;
                break;
            case ('k'):
                this.pushConsole("Move Up");
                this.nextEvent = MOVE_UP;
                break;
            case ('l'):
                this.pushConsole("Move Right");
                this.nextEvent = MOVE_RIGHT;
                break;
            case ('v'):
                this.player.dmg(1);
                break;
            case ('c'):
                this.player.dmg(-1);
                break;
            default:
                break;
        }
    }

    public void pushConsole(String newLog) {
        console.add(newLog);
    }

    public void processInput(KeyType kt) {
        if (kt == KeyType.Escape) {
            killGame();
        }
    }

    public static void killGame() {
        gameIsRunning = false;
    }

    public void buildConsole(int lines, int lineWidth) {
        this.console = new ConsoleMessageQueue(lines, lineWidth);
    }

    public String[] getConsole() {
        return console.getMessages().toArray(new String[0]);
    }
}

