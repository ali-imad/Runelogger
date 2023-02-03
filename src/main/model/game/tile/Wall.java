package model.game.tile;

import com.googlecode.lanterna.TextColor;

public class Wall extends Tile {
    public Wall() {
        super('#', false);
    }

    public Wall(TextColor fg) {
        super('#', false, fg);
    }

    public Wall(TextColor fg, TextColor bg) {
        super('#', false, fg, bg);
    }
}
