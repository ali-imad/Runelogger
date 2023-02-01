package model.actor;

public class Actor {
    private final char glyph;
    private final String label;
    private int[] pos;

    public Actor(char glyph, String name, int x, int y) {
        this.glyph = glyph;
        this.pos = new int[]{x, y};
        this.label = name;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] newPos) {
        this.pos = newPos;
    }

    public void setPos(int x, int y) {
        this.pos = new int[]{x, y};
    }

    public int getX() {
        return this.pos[0];
    }

    public void setX(int x) {
        this.pos[0] = x;
    }

    public int getY() {
        return this.pos[1];
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
