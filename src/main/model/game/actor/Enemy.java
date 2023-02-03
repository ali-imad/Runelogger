package model.game.actor;

import com.googlecode.lanterna.TextColor;

public abstract class Enemy extends Actor {
    private boolean following;

    public Enemy(char glyph, String name, int x, int y, TextColor fg, TextColor bg, int mHP, int mMP, int atk, int def) {
        super(glyph, name, x, y, fg, bg, mHP, mMP, atk, def);
    }

    public void flipFollowing() {
        this.following = !this.following;
    }
}
