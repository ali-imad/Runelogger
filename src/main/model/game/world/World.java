package model.game.world;

import model.game.world.actor.Actor;
import model.game.world.actor.Orc;
import model.game.world.map.GameMap;
import model.game.world.map.tile.Tile;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class World {
    private ArrayList<Actor> actors;  // list of actors
    private GameMap map;  // presently active tiles
    private Random rng;

    public World(Actor player, int w, int h) {
        this.rng = new Random(34972595);
//        this.rng = new Random();
        this.actors = new ArrayList<>();
        this.actors.add(player);
        this.map = new GameMap(w, h);
    }

    // REQUIRES: actor is in actors
    public void moveActorAndCollide(Actor actor, int dx, int dy) {
        // TODO: move to Game?
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
                actor.attack(tileToReach);
            }
        }
    }

    // MODIFIES: this.actors
    // EFFECTS: parse the tilemap and repopulate this.actors
    public void updateActors() {
        // player is always at first index
        Actor player = this.actors.get(0);
        // init a blank list
        ArrayList<Actor> newActors = new ArrayList<>();
        for (int i = 0; i < this.getMap().getWidth(); i++) {
            for (int j = 0; j < this.getMap().getHeight(); j++) {
                Tile tile = this.getMap().getTile(i,j);

                // no actor? keep going
                if (Objects.isNull(tile.getStanding())) {
                    continue;
                }

                Actor standing = tile.getStanding();

                // we insert the player at the head manually
                if (standing == player) {
                    continue;
                }

                // add the standing actors
                newActors.add(standing);
            }
        }

        newActors.add(0, player);
        this.actors = newActors;
    }

    public Actor[] getActors() {
        return this.actors.toArray(new Actor[this.actors.size()]);
    }

    public void initBasicWorld() {
        Actor player = this.actors.get(0);
        this.actors = new ArrayList<>();
        this.actors.add(player);
//        this.getMap().setBasicMap(1, 1);
        int pillars = this.getMap().getWidth() * this.getMap().getHeight();
        pillars = Math.round(pillars * 0.07F);
        this.getMap().setBasicCave(1, 1, pillars);
        this.populate(PopulationKind.RANDOM);
        this.updateActors();
    }

    private void populate(PopulationKind pk) {
        switch (pk) {
            case RANDOM:
                populateRandomlyWithOrcs();
                break;
            default:
                throw new IllegalArgumentException(String.format("%s is not a PopulationKind", pk));
        }
    }

    private void populateRandomlyWithOrcs() {
        final int POP = 40;
        for (int i = 0; i < POP; i++) {
            int x = rng.nextInt(this.getMap().getWidth());
            int y = rng.nextInt(this.getMap().getHeight());

            Tile toSpawn = this.getMap().getTile(x, y);

            if (toSpawn.isWalkable() && Objects.isNull(toSpawn.getStanding())) {
                // TODO: Extract into method
                Orc newOrc = new Orc(x, y, 8, 2);
                this.getMap().getTile(x,y).setStanding(newOrc);
            }
        }
    }

    public GameMap getMap() {
        return this.map;
    }
}
