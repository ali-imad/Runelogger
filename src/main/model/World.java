package model;

import java.util.ArrayList;

public class World {
    private GameMap map;  // presently active tiles
    private ArrayList<Actor> actors;  // list of actors

    public World(Actor player) {
        this.actors = new ArrayList<>();
        this.actors.add(player);
    }

    // REQUIRES:
    // MODIFIES: this.map
    // EFFECTS: Set the current World object to have a blank map of size wxh
    public void resetMap(int w, int h) {
        this.map = new GameMap(w, h);
    }

    public GameMap getMap() {
        return this.map;
    }

    // REQUIRES: actor is in actors
    public void moveActorInMap(Actor actor, int dx, int dy) {
        int[] newPos = actor.getPos();
        newPos[0] += dx;
        newPos[1] += dy;

        if (this.map.getTiles()[newPos[0]][newPos[1]].isWalkable()) {
            actor.setPos(newPos);
        }
    }
}
