package model.tile;

public abstract class Tile {
    protected final char glyph;
    protected boolean walkable;
    protected Tile(char glyph, boolean walkable) {
        this.glyph = glyph;
        this.walkable = walkable;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public char getGlyph() {
        return glyph;
    }
}
