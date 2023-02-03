package model.game.actor;

import static com.googlecode.lanterna.TextColor.ANSI.BLACK;
import static com.googlecode.lanterna.TextColor.ANSI.CYAN;


public class Player extends Actor {
    private static final int INITIAL_MAX_HP = 40;
    private static final int INITIAL_MAX_MP = 20;
    private static final int INITIAL_ATK = 8;
    private static final int INITIAL_DEF = 3;

    public Player(int x, int y) {
        super('@', "Player", x, y, CYAN, BLACK, INITIAL_MAX_HP, INITIAL_MAX_MP, INITIAL_ATK, INITIAL_DEF);
    }
}
