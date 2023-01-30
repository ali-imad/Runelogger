package ui;

import model.Game;

public class Main {
    static final int SCREEN_WIDTH  = 80;
    static final int SCREEN_HEIGHT = 60;
    public static void main(String[] args) {
        Game game = new Game("Petunia");

        game.init();
        game.run();

    }
}
