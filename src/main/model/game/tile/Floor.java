package model.game.tile;

import com.googlecode.lanterna.TextColor;

public class Floor extends Tile {
    private static final TextColor DEFAULT_COLOUR = new TextColor.RGB(60, 60, 60);

    public Floor() {
        super('.', true, DEFAULT_COLOUR);
    }

    public Floor(TextColor fg) {
        super('.', true, fg);
    }

    public Floor(TextColor fg, TextColor bg) {
        super('.', true, fg, bg);
    }
}
