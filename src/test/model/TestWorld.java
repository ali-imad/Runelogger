package model;

import model.game.world.World;
import model.game.world.actor.Player;
import model.game.world.map.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestWorld {
    private World testWorldSmall;
    private World testWorldBasic;

    @BeforeEach
    void runBefore() {
        Player testPlayer = new Player(0, 0);
        testWorldSmall = new World(testPlayer, 1, 1);
        // simplest map
        assertArrayEquals(new int[]{1, 1}, testWorldSmall.getMap().getShape());
        assertEquals('#', testWorldSmall.getMap().getTile(0, 0).getGlyph());

        testWorldBasic = new World(testPlayer, 8, 8);
        // simplest map
        assertArrayEquals(new int[]{8, 8}, testWorldBasic.getMap().getShape());
        for (int i = 0; i < 8; i++) {
            Stream<Tile> row = Arrays.stream(testWorldBasic.getMap().getTiles()[i]);
            row.forEach(tile -> assertEquals('#', tile.getGlyph()));
        }
    }

    @Test
    void testInitMap() {
    }
}