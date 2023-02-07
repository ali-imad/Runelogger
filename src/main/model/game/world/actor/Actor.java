package model.game.world.actor;

import com.googlecode.lanterna.TextColor;
import model.game.world.World;
import model.game.world.map.tile.Tile;

import static model.game.world.actor.AttackOutcome.DAMAGE;
import static model.game.world.actor.AttackOutcome.KILL;

public abstract class Actor {
    private final String label;
    private char glyph;
    private int[] pos;
    private World world;

    private TextColor fgColor;  // TODO: add overload constructors
    private TextColor bgColor;

    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;

    private int atk;
    private int def;
    private boolean blocking; // blocks movement
    private Actor under; // actor under actor, if they exist

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
        this.blocking = true;  // should be explictly disabled by changeBlocking
    }

    public Actor getUnder() {
        return under;
    }

    public void setUnder(Actor under) {
        this.under = under;
    }

    public boolean isBlocking() {
        return blocking;
    }

    protected void setBlocking(boolean b) {
        this.blocking = b;
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

    // EFFECTS: Attack whatever is standing on the tile
    public void attack(Tile t) {
        Actor toAttack = t.getStanding();
        AttackOutcome outcome = toAttack.dmg(this.getAtk() - toAttack.getDef());
        if (outcome == KILL) {
            // TODO: extract corpse generation to static method
            t.setStanding(new Corpse(toAttack));
        }
    }

    public AttackOutcome dmg(int dhp) {
        this.hp -= dhp;
        if (this.hp < 0) {
            return KILL;
        }
        return DAMAGE;
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

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
