package model.game.world;

import model.game.GameMap;
import model.game.actor.Actor;
import model.game.tile.Tile;

import java.util.ArrayList;
import java.util.Objects;

public class World {
    private final ArrayList<Actor> actors;  // list of actors
    private GameMap map;  // presently active tiles

    public World(Actor player, int w, int h) {
        this.actors = new ArrayList<>();
        this.actors.add(player);
        this.map = new GameMap(w, h);
    }

    // REQUIRES: actor is in actors
    public void moveActorAndCollide(Actor actor, int dx, int dy) {
        int[] newPos = actor.getPos().clone();
        newPos[0] += dx;
        newPos[1] += dy;

        Tile tileToReach = this.map.getTile(newPos[0], newPos[1]);

        if (tileToReach.isWalkable()) {
            if (Objects.isNull(tileToReach.getStanding())) {
                this.map.getTile(actor.getX(), actor.getY()).emptyStanding();
                actor.setPos(newPos);
                tileToReach.setStanding(actor);
            } else {
                Actor toAttack = tileToReach.getStanding();
                actor.attack(toAttack);
            }
        }
    }

    public void setBasicMap() {
        int hollowW = getMap().getShape()[0] - 2;
        int hollowH = getMap().getShape()[1] - 2;
        this.getMap().chiselRectangle(1, 1, hollowW, hollowH);
    }

    public GameMap getMap() {
        return this.map;
    }

    public Actor[] getActors() {
        return this.actors.toArray(new Actor[this.actors.size()]);
    }
}
