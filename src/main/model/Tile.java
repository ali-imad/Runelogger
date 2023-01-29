package model;

public class Tile {
    private final char glyph;
    private final boolean walkable;

    public Tile(TileKind kind) throws Exception {
        this.walkable = false;
        switch (kind) {
            case WALL:
                this.glyph = '#';
                break;
            case FLOOR:
                this.glyph = '.';
                break;
            default:
                throw new Exception("Tile not from TileKind enum");
        }
    }

    public boolean isWalkable() {
        return walkable;
    }

    public char getGlyph() {
        return glyph;
    }
}
