package model.game.actor;

import static com.googlecode.lanterna.TextColor.ANSI.BLACK;
import static com.googlecode.lanterna.TextColor.ANSI.RED;

public class Orc extends Enemy {
    private static int orcATK = 3;
    private static int orcDef = 1;

    public Orc(int x, int y, int mHP, int mMP) {
        super('o', "Orc", x, y, RED, BLACK, mHP, mMP, orcATK, orcDef);
    }
}
