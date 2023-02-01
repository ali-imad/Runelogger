package model;

import model.actor.Actor;
import model.tile.Tile;

import java.util.ArrayList;

public class World {
    GameMap map;  // presently active tiles
    private final ArrayList<Actor> actors;  // list of actors

    public World(Actor player, int w, int h) {
        this.actors = new ArrayList<>();
        this.actors.add(player);
        this.map = new GameMap(w, h);
    }

    public GameMap getMap() {
        return this.map;
    }

    // REQUIRES: actor is in actors
    public void moveActorAndCollide(Actor actor, int dx, int dy) {
        int[] newPos = actor.getPos().clone();
        newPos[0] += dx;
        newPos[1] += dy;

        Tile tileToReach = this.map.getTile(newPos[0], newPos[1]);

        if (tileToReach.isWalkable()) {
            actor.setPos(newPos);
        } else {
            System.out.println("BLOCKED");
        }
    }

    public void setBasicMap() {
        int hollowW = getMap().getShape()[0] - 2;
        int hollowH = getMap().getShape()[1] - 2;
        this.getMap().chiselRectangle(1,1, hollowW, hollowH);
    }

    public Actor[] getActors() {
        return this.actors.toArray(new Actor[this.actors.size()]);
    }
}
