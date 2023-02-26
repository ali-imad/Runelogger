package model.actor;

/*
    A struct class that holds an actors stats and applies bonuses in a clean manner
 */

public class StatBook {
    private int hp;
    private int mhp;
    private int mp;
    private int mmp;

    private int atk;
    private int wis;
    private int def;

    public StatBook(int mhp, int mmp, int atk, int wis, int def) {
        this.hp = mhp;
        this.mhp = mhp;
        this.mp = mmp;
        this.mmp = mmp;
        this.atk = atk;
        this.wis = wis;
        this.def = def;
    }

    public static StatBook makeEmptyStatbook() {
        return new StatBook(0,0,0,0,0);
    }

    public int getHp() {
        return hp;
    }

    public int getHp(Actor a) {
        return hp + a.getTotalStat(Stats.HP);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMhp() {
        return mhp;
    }

    public void setMhp(int mhp) {
        this.mhp = mhp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMmp() {
        return mmp;
    }

    public void setMmp(int mmp) {
        this.mmp = mmp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getStat(Stats stat) {
        switch (stat) {
            case HP:
                return this.getHp();
            case MP:
                return this.getMp();
            case ATK:
                return this.getAtk();
            case WIS:
                return this.getWis();
            case DEF:
                return this.getDef();
            default:
                return 0;
        }
    }
}
