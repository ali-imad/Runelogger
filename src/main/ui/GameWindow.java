package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import model.game.GameMap;
import model.game.actor.Actor;
import model.game.Game;
import model.game.tile.Tile;

import java.awt.*;
import java.io.IOException;

import static com.googlecode.lanterna.TextColor.ANSI;
import static com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration.BoldMode.NOTHING;
import static java.lang.Math.max;
import static java.lang.Math.min;

// TODO: split up GameWindow into more abstract classes

public class GameWindow {
    private static final int CONSOLE_PAD_X = 2;
    private static final int CONSOLE_PAD_Y = 1;
    private static final int MAP_PAD_X = 2;
    private static final int MAP_PAD_Y = 1;
    private static Game game;
    // the next two views are static, and thus we should never need to reconstruct them
    private static ScreenView consoleView;
    private static ScreenView statusView;
    private static StatusBar healthBar;
    private static StatusBar manaBar;
    private final Screen screen;
    private final TerminalSize size;
    // view for the viewport mapped to the game map
    private ScreenView gameView;

    public GameWindow(int gameW, int gameH, int viewW, int viewH, Game unattached) throws IOException {
        SwingTerminalFontConfiguration tc;
        game = unattached;
        tc = new SwingTerminalFontConfiguration(false, NOTHING,
//                new Font(Font.MONOSPACED, Font.PLAIN, 14));  // uncomment in prod
                new Font("Fixedsys Excelsior", Font.PLAIN, 16));
        this.size = new TerminalSize(gameW, gameH);
        this.screen = new DefaultTerminalFactory()
                .setInitialTerminalSize(this.size)
                .setTerminalEmulatorTitle(game.getTitle())
                .setTerminalEmulatorFontConfiguration(tc)
                .createScreen();
        this.gameView = new ScreenView(MAP_PAD_X, MAP_PAD_Y, viewW - MAP_PAD_X, viewH - MAP_PAD_Y);

        statusView = new ScreenView(viewW, 0, gameW - viewW, viewH);
        consoleView = new ScreenView(CONSOLE_PAD_X, viewH + CONSOLE_PAD_Y, gameW - CONSOLE_PAD_X, gameH - viewH - CONSOLE_PAD_Y);
        // *2 because padding is on both sides
        game.buildConsole(consoleView.height - CONSOLE_PAD_Y * 2, consoleView.width - CONSOLE_PAD_X * 2);

        healthBar = new StatusBar(game.getPlayer().getMaxHP(), ANSI.RED, "Health");
        manaBar = new StatusBar(game.getPlayer().getMaxMP(), ANSI.BLUE, "Mana");
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

        // set game viewport based on actor position
        this.clampViewToActor();

        // draw to screen
        this.renderGameMap();    // refresh the new screen
        this.renderLogWindow();  // bottom part of the screen
        this.renderStatusWindow();  // right side of the screen

        screen.refresh();
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

    private void clampViewToActor() {
        // clamp to the left
        int minViewX = max(0, game.getPlayer().getX() - this.gameView.width / 2);
        // clamp that result to the right (it will preserve the old one if its being used)
        int newViewX = min(game.getWorld().getMap().getWidth() - this.gameView.width, minViewX);

        // do the same thing to y
        // clamp to the top if needed
        int minViewY = max(0, game.getPlayer().getY() - this.gameView.height / 2);
        // clamp that result to the bottom
        int newViewY = min(game.getWorld().getMap().getHeight() - this.gameView.height, minViewY);
        this.gameView = new ScreenView(newViewX, newViewY, this.gameView.width, this.gameView.height);
    }

    private void renderGameMap() {
        this.drawTileMap();
        this.drawEntities();
        this.drawActors();
    }

    // REQUIRES: game.console to exist
    // MODIFIES: screen
    // EFFECTS: prints out the console messages to the screen
    private void renderLogWindow() {
        int startY = consoleView.getY1();
        String[] log = game.getConsole();
        int endY = min(startY + log.length, consoleView.getY2());
        int i = 0;
        for (int y = startY; y < endY; y++) {
            TextGraphics msg = screen.newTextGraphics();
            TerminalPosition pos = new TerminalPosition(consoleView.getX1(), y);
            msg.setForegroundColor(ANSI.CYAN);
            msg.putString(pos, log[i]);
            i++;
        }
    }

    private void renderStatusWindow() {
        this.updateStatusBars();
        final int WINDOW_PAD_Y = 2;
        final int WINDOW_PAD_X = 2;
        final int LABEL_PAD_Y = 2;
        final int LABEL_PAD_X = 0;
        final int Y_SPACING_BETWEEN = 4;
        final char BAR_CHAR = '#';

        int startX = statusView.getX1() + WINDOW_PAD_X;
        int MAX_WIDTH = statusView.getX2() - startX - WINDOW_PAD_X;
        int startY = statusView.getY1() + WINDOW_PAD_Y;
        int endY = statusView.getY2() - WINDOW_PAD_Y;

        StatusBar[] bars = new StatusBar[]{
                healthBar,
                manaBar,
        };

        int j = startY;
        for (StatusBar b : bars) {
            int x = startX;
            // render the label
            TextGraphics label = screen.newTextGraphics();
            TerminalPosition labelPos = new TerminalPosition(x, j);

            label.setForegroundColor(b.getColor());
//            label.putString(labelPos, b.getLabel());
            label.putString(labelPos, String.format("%s: %d / %d", b.getLabel(), b.getCurrentValue(), b.getMaxValue()));

            x += LABEL_PAD_X;
            j += LABEL_PAD_Y;

            float percent = Float.min(1.0F, ((float) b.getCurrentValue() / (float) b.getMaxValue()));

//            game.pushConsole(String.valueOf(endX - startX));
            game.pushConsole(String.valueOf(percent));
            int endX = Math.round((float) (startX + (MAX_WIDTH * percent)));
            game.pushConsole(String.valueOf(endX - startX));

            // render the bar
            for (int i = x; i < endX; i++) {
                TextCharacter tc = new TextCharacter(BAR_CHAR, b.getColor(), ANSI.BLACK);
                screen.setCharacter(new TerminalPosition(i, j), tc);
            }

            j += Y_SPACING_BETWEEN;
        }
    }

    private void drawTileMap() {
        clampViewToActor();

        GameMap tilemap = game.getWorld().getMap();
        for (int i = this.gameView.x; i < this.gameView.x + this.gameView.width; i++) {
            for (int j = this.gameView.y; j < this.gameView.y + this.gameView.height; j++) {
                int terminalX = i - this.gameView.x + MAP_PAD_X;
                int terminalY = j - this.gameView.y + MAP_PAD_Y;
                Tile tile = tilemap.getTile(i, j);
                screen.setCharacter(new TerminalPosition(terminalX, terminalY), tile.toTC());
            }
        }

    }

    private void drawEntities() {
    }

    private void drawActors() {
        for (Actor a : game.getWorld().getActors()) {
            TerminalPosition pos = new TerminalPosition(a.getX(), a.getY());
            int actorX = pos.getColumn();
            int actorY = pos.getRow();
            if (gameView.x <= actorX && actorX <= gameView.x + gameView.width) {
                if (gameView.y <= actorY && actorY <= gameView.y + gameView.height) {
                    int terminalX = actorX - this.gameView.x;
                    int terminalY = actorY - this.gameView.y;
                    TextCharacter tc = new TextCharacter(a.getGlyph(), ANSI.CYAN, ANSI.BLACK);
                    screen.setCharacter(new TerminalPosition(terminalX, terminalY), tc);
                }
            }
        }
    }

    // EFFECTS: Updates the status bars with appropriate values
    private void updateStatusBars() {
        // set health
        healthBar.setMaxValue(game.getPlayer().getMaxHP());
        healthBar.setCurrentValue(game.getPlayer().getHP());

        // set mana
        manaBar.setMaxValue(game.getPlayer().getMaxMP());
        manaBar.setCurrentValue(game.getPlayer().getMP());
    }
}
