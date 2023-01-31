package ui;

import model.Game;

import java.io.IOException;

public class Main {
    static final int SCREEN_WIDTH  = 50;
    static final int SCREEN_HEIGHT = 30;
    static final int MAP_WIDTH     = 40;
    static final int MAP_HEIGHT    = 20;  // arbitrary limit????

    public static void main(String[] args) throws IOException {
        Game game = new Game("Petunia", MAP_WIDTH, MAP_HEIGHT);
        game.init();
        GameWindow mainWindow = new GameWindow(SCREEN_WIDTH, SCREEN_HEIGHT, game);
        mainWindow.run();
    }
}
