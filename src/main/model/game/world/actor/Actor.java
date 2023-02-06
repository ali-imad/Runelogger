package model.game.world.actor;

import com.googlecode.lanterna.TextColor;

public abstract class Actor {
    private char glyph;
    private final String label;
    private int[] pos;

    private TextColor fgColor;  // TODO: add overload constructors
    private TextColor bgColor;

    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;

    private int atk;
    private int def;

    // TODO: convert to builder
    public Actor(char glyph, String name, int x, int y, TextColor fgColor,
                 TextColor bgColor, int mhp, int mmp, int atk, int def) {
        this.glyph = glyph;
        this.atk = atk;
        this.def = def;
        this.pos = new int[]{x, y};
        this.label = name;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.maxHP = mhp;
        this.maxMP = mmp;
        this.hp = mhp;
        this.mp = mmp;
    }

    public TextColor getFgColor() {
        return fgColor;
    }

    public void setFgColor(TextColor fgColor) {
        this.fgColor = fgColor;
    }

    public TextColor getBgColor() {
        return bgColor;
    }

    public void setBgColor(TextColor bgColor) {
        this.bgColor = bgColor;
    }

    public int getHP() {
        return hp;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMP() {
        return mp;
    }

    public void setMP(int mp) {
        this.mp = mp;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
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

    public void attack(Actor toAttack) {
        toAttack.dmg(this.getAtk() - toAttack.getDef());
    }

    public void dmg(int dhp) {
        this.hp -= dhp;
        if (this.hp < 0) {
            this.glyph = '%';
        }
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
