package ui;

import model.Game;

import java.io.IOException;

public class Main {
    static final int SCREEN_WIDTH  = 140;
    static final int SCREEN_HEIGHT = 60;
    static final int MAP_WIDTH     = 90;
    static final int MAP_HEIGHT    = 50;  // arbitrary limit????

    public static void main(String[] args) throws IOException {
        Game game = new Game("Petunia", MAP_WIDTH, MAP_HEIGHT);
        game.init();
        GameWindow mainWindow = new GameWindow(SCREEN_WIDTH, SCREEN_HEIGHT, game);
        mainWindow.run();
    }
}
