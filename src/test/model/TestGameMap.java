package model;

import model.game.world.map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameMap {
    private static final int RECT_WIDTH = 80;
    private static final int RECT_HEIGHT = 60;
    private static final int SQUARE_LENGTH = 20;
    private GameMap rectMap;
    private GameMap squareMap;

    @BeforeEach
    void initMaps() {
        rectMap = new GameMap(RECT_WIDTH, RECT_HEIGHT);
        squareMap = new GameMap(SQUARE_LENGTH, SQUARE_LENGTH);
    }

    @Test
    void createRectRoom() {
        assertFalse(rectMap.getTile(3, 3).isWalkable());
        rectMap.chiselRectangle(3, 3, 1, 4);
        assertTrue(rectMap.getTile(3, 3).isWalkable());
        assertTrue(rectMap.getTile(3, 5).isWalkable());
        assertTrue(rectMap.getTile(3, 6).isWalkable());
        assertFalse(rectMap.getTile(4, 6).isWalkable());

        assertFalse(squareMap.getTile(3, 3).isWalkable());
        squareMap.chiselRectangle(1, 1, SQUARE_LENGTH - 2, SQUARE_LENGTH - 2);
        assertTrue(squareMap.getTile(3, 3).isWalkable());
        assertTrue(squareMap.getTile(7, 10).isWalkable());
    }

    @Test
    void createCircleRoom() {
        assertFalse(rectMap.getTile(8, 8).isWalkable());
        rectMap.chiselCircle(9, 9, 5);
        assertTrue(rectMap.getTile(8, 8).isWalkable());
        // corner is still wall
        assertFalse(rectMap.getTile(5, 5).isWalkable());
    }

    @Test
    void circleChiselClamps() {
        assertFalse(rectMap.getTile(0, 0).isWalkable());
        rectMap.chiselCircle(3, 2, 4);
        assertTrue(rectMap.getTile(0, 0).isWalkable());
        assertTrue(rectMap.getTile(0, 2).isWalkable());
        assertTrue(rectMap.getTile(6, 2).isWalkable());
    }

}
