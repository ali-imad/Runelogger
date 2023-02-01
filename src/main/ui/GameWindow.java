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
import static java.lang.Math.max;
import static java.lang.Math.min;

public class GameWindow {
    private static Game game;
    private final Screen screen;
    private Rectangle view;
    private final TerminalSize size;

    public GameWindow(int gameW, int gameH, int viewW, int viewH, Game unattached) throws IOException {
        SwingTerminalFontConfiguration tc;
        game = unattached;
        tc = new SwingTerminalFontConfiguration(false, NOTHING,
                new Font(Font.MONOSPACED, Font.PLAIN, 14));
        this.size = new TerminalSize(gameW, gameH);
        this.screen = new DefaultTerminalFactory()
                .setInitialTerminalSize(this.size)
                .setTerminalEmulatorTitle(game.getTitle())
                .setTerminalEmulatorFontConfiguration(tc)
                .createScreen();
        this.view = new Rectangle(0, 0, viewW, viewH);
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

        // set view based on actor position
        this.clampViewToActor();

        // draw to screen
        this.drawTileMap();
        this.drawEntites();
        this.drawActors();

        // refresh the new screen
        screen.refresh();
    }

    private void drawActors() {
        for (Actor a : game.getWorld().getActors()) {
            TerminalPosition pos = new TerminalPosition(a.getPos()[0], a.getPos()[1]);
            int actorX = pos.getColumn();
            int actorY = pos.getRow();
            if (view.x <= actorX && actorX <= view.x + view.width) {
                if (view.y <= actorY && actorY <= view.y + view.height) {
                    int terminalX = actorX - this.view.x;
                    int terminalY = actorY - this.view.y;
                    TextCharacter tc = new TextCharacter(a.getGlyph(), ANSI.CYAN, ANSI.BLACK);
                    screen.setCharacter(new TerminalPosition(terminalX, terminalY), tc);
                }
            }
        }
    }

    private void drawEntites() {
    }

    private void drawTileMap() {
        clampViewToActor();

        GameMap tilemap = game.getWorld().getMap();
        for (int i = this.view.x; i < this.view.x + this.view.width; i++) {
            for (int j = this.view.y; j < this.view.y + this.view.height; j++) {
//        for (int i = 0; i < tilemap.getWidth(); i++) {
//            for (int j = 0; j < tilemap.getHeight(); j++) {
                int terminalX = i - this.view.x;
                int terminalY = j - this.view.y;
                Tile tile = tilemap.getTile(i, j);
                screen.setCharacter(new TerminalPosition(terminalX, terminalY), tile.toTC());
            }
        }

    }

    private void clampViewToActor() {
        // clamp to the left
        int minViewX = max(0, game.getPlayer().getPos()[0] - this.view.width / 2);
        // clamp that result to the right (it will preserve the old one if its being used)
        int newViewX = min(game.getWorld().getMap().getWidth() - this.view.width, minViewX);

        // do the same thing to y
        // clamp to the left
        int minViewY = max(0, game.getPlayer().getPos()[1] - this.view.height / 2);
        // clamp that result to the right (it will preserve the old one if its being used)
        int newViewY = min(game.getWorld().getMap().getHeight() - this.view.height, minViewY);
        this.view = new Rectangle(newViewX, newViewY, this.view.width, this.view.height);
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
