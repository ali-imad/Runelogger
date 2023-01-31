package model.tile;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

import static com.googlecode.lanterna.TextColor.ANSI.BLACK;
import static com.googlecode.lanterna.TextColor.ANSI.WHITE;

public abstract class Tile {
    protected final char glyph;
    protected boolean walkable;
    private final TextColor fgColor;
    private final TextColor bgColor;

    protected Tile(char glyph, boolean walkable) {
        this(glyph, walkable, WHITE, BLACK);
    }

    protected Tile(char glyph, boolean walkable, TextColor fgColor) {
        this(glyph, walkable, fgColor, BLACK);
    }

    protected Tile(char glyph, boolean walkable, TextColor fgColor, TextColor bgColor) {
        this.glyph = glyph;
        this.walkable = walkable;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public char getGlyph() {
        return glyph;
    }

    public TextCharacter toTC() {
        return new TextCharacter(glyph, fgColor, bgColor);
    }
}
