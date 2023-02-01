package model;

import model.actor.Actor;
import model.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestWorld {
    private World testWorld;
    static final int testWidth = 40;
    static final int testHeight = 30;

    @BeforeEach
    void runBefore() {
        Actor testActor = new Actor('@', "test actor", 0, 0);
        testWorld = new World(testActor, testWidth, testHeight);
    }

    @Test
    void testInitMap() {
        // simplest map
        testWorld.resetMap(1, 1);
        assertArrayEquals(new int[]{1, 1}, testWorld.getMap().getShape());
        assertEquals('#', testWorld.getMap().getTile(0, 0).getGlyph());

        // square map
        testWorld.resetMap(8, 8);
        assertArrayEquals(new int[]{8, 8}, testWorld.getMap().getShape());
        for (int i = 0; i < 8; i++) {
            Stream<Tile> row = Arrays.stream(testWorld.getMap().getTiles()[i]);
            row.forEach(tile -> assertEquals('#', tile.getGlyph()));
        }
    }

}