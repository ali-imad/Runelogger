package model.game.tile;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import model.game.actor.Actor;

import static com.googlecode.lanterna.TextColor.ANSI.BLACK;
import static com.googlecode.lanterna.TextColor.ANSI.WHITE;

public abstract class Tile {
    protected final char glyph;
    private final TextColor fgColor;
    private final TextColor bgColor;
    protected boolean walkable;
    private Actor standing;  // actor standing on tile

    protected Tile(char glyph, boolean walkable) {
        this(glyph, walkable, WHITE, BLACK);
    }

    protected Tile(char glyph, boolean walkable, TextColor fgColor, TextColor bgColor) {
        this.glyph = glyph;
        this.walkable = walkable;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
    }

    protected Tile(char glyph, boolean walkable, TextColor fgColor) {
        this(glyph, walkable, fgColor, BLACK);
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

    public void emptyStanding() {
        setStanding(null);
    }

    public Actor getStanding() {
        return this.standing;
    }

    public void setStanding(Actor standing) {
        this.standing = standing;
    }
}
