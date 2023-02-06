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
    }

    private void populate(PopulationKind pk) {
        switch (pk) {
            case RANDOM:
                populateRandomlyWithOrcs();
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
                actors.add(newOrc);
                this.getMap().getTile(x,y).setStanding(newOrc);
            }
        }
    }

    public GameMap getMap() {
        return this.map;
    }
}
