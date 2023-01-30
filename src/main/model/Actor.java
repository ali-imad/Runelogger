package model;

public class Actor {
    private int[] pos;
    private final char glyph;
    private final String label;

    public Actor(char glyph, String name) {
        this.glyph = glyph;
        this.pos = new int[]{0, 0};
        this.label = name;
    }

    public Actor(char glyph, String name, int x, int y) {
        this.glyph = glyph;
        this.pos = new int[]{x, y};
        this.label = name;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        this.pos = new int[]{x, y};
    }

    public void setPos(int[] newPos) {
        this.pos = newPos;
    }

    public void setX(int x) {
        this.pos[0] = x;
    }

    public void setY(int y) {
        this.pos[1] = y;
    }

    public char getGlyph() {
        return glyph;
    }

    public String getLabel() {
        return label;
    }
}
