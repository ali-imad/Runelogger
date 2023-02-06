package model.game.world.actor;

import com.googlecode.lanterna.TextColor;

public class Corpse extends Actor {

    public Corpse(String name, int x, int y,
                  TextColor fgColor, TextColor bgColor) {
        super('%', name, x, y, fgColor, bgColor, 0, 0, 0, 0);
    }

    public Corpse(Actor a) {
        super('%',
                a.getLabel(), a.getX(), a.getY(), a.getFgColor(), a.getBgColor(),
                0, 0, 0, 0);
    }
}
