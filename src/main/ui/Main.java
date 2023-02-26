package ui;

import model.Game;

public class Main {
    public static void main(String[] args) {
        Game petunia = new Game("Petunia");
        GameWindow game = new GameWindow(petunia);
        game.start();
    }
}
