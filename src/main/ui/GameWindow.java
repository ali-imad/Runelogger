package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import model.Game;
import model.GameMap;
import model.actor.Actor;
import model.tile.Tile;

import java.awt.*;
import java.io.IOException;

import static com.googlecode.lanterna.TextColor.ANSI;
import static com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration.BoldMode.NOTHING;

public class GameWindow {
    private static Game game;
    private static SwingTerminalFontConfiguration tc;
    private final Screen screen;
    private final TerminalSize size;

    public GameWindow(int width, int height, Game unattached) throws IOException {
        game = unattached;
        tc = new SwingTerminalFontConfiguration(false, NOTHING,
                new Font(Font.MONOSPACED, Font.PLAIN, 14));
        this.size = new TerminalSize(width, height);
        this.screen = new DefaultTerminalFactory()
                .setInitialTerminalSize(this.size)
                .setTerminalEmulatorTitle(game.getTitle())
                .setTerminalEmulatorFontConfiguration(tc)
                .createScreen();
    }

    public void run() throws IOException {
        // start lanterna
        this.screen.startScreen();

        // hide the cursor
        this.screen.setCursorPosition(null);

        while (Game.isGameIsRunning()) {
            // show new game state
            this.render();
            // get player input
            this.handleInput();
            // let the game respond to that and form a new state
            game.run();
        }

        // kill lanterna
        this.screen.stopScreen();

        System.exit(0);
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
                screen.setCharacter(new TerminalPosition(i, j), tile.toTC());
            }
        }

    }

    private void handleInput() throws IOException {
        KeyStroke key = screen.readInput();
        if (key == null) {
            return;
        }
        if (key.getCharacter() != null) {
            game.processInput(key.getCharacter());
        } else if (key.getKeyType() != null) {
            game.processInput(key.getKeyType());
        }
    }
}
