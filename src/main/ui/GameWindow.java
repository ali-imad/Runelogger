package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Actor;
import model.Game;
import model.GameMap;
import model.tile.Tile;

import java.io.IOException;

import static com.googlecode.lanterna.TextColor.*;

public class GameWindow {
    private static Game game;
    private final Screen screen;
    private static boolean gameIsRunning;
    private TerminalSize size;

    public GameWindow(int width, int height, Game unattached) throws IOException {
        game = unattached;
        this.screen = new DefaultTerminalFactory().createScreen();
    }

    public static void killGame() {
        gameIsRunning = false;
    }

    public void run() throws IOException {
        // start lanterna
        this.screen.startScreen();

        // hide the cursor
        this.screen.setCursorPosition(null);

        gameIsRunning = true;

        while (gameIsRunning) {
            // show new game state
            this.render();
            // get player input
            this.handleInput();
            // let the game respond to that and form a new state
            game.run();
        }

        // kill lanterna
        this.screen.stopScreen();
    }

    private void render() throws IOException {
        // clear the old screen
        screen.clear();
        // draw to screen
        this.drawTileMap();
        this.drawEntites();
        this.drawActors();

        // refresh the new screen
        screen.refresh();
    }

    private void drawActors() {
    }

    private void drawEntites() {
        for (Actor a : game.getWorld().getActors()) {
            TerminalPosition pos = new TerminalPosition(a.getPos()[0], a.getPos()[1]);
            TextCharacter tc = new TextCharacter(a.getGlyph(), ANSI.CYAN, ANSI.BLACK);
            screen.setCharacter(pos, tc);
        }
    }

    private void drawTileMap() {
        GameMap tilemap = game.getWorld().getMap();
        for (int i = 0; i < tilemap.getShape()[0]; i++) {
            for (int j = 0; j < tilemap.getShape()[1]; j++) {
                Tile tile = tilemap.getTile(i, j);
                char glyph = tile.getGlyph();
                TextCharacter tc = new TextCharacter(glyph, ANSI.WHITE, ANSI.BLACK);
                screen.setCharacter(new TerminalPosition(i, j), tc);
            }
        }

    }

    private void handleInput() throws IOException {
       KeyStroke key = screen.readInput();
        if (key == null) {
            return;
        }
        switch (key.getCharacter()) {
            case ('h'):
                game.getWorld().moveActorAndCollide(game.getPlayer(), -1, 0);
                break;
            case ('j'):
                game.getWorld().moveActorAndCollide(game.getPlayer(), 0, 1);
                break;
            case ('k'):
                game.getWorld().moveActorAndCollide(game.getPlayer(), 0, -1);
                break;
            case ('l'):
                game.getWorld().moveActorAndCollide(game.getPlayer(), 1, 0);
                break;
            default:
                break;
        }
    }
}
