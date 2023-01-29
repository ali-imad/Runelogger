package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameMap {
    private GameMap rectMap;
    private GameMap squareMap;

    private static final int RECT_WIDTH = 80;
    private static final int RECT_HEIGHT = 60;
    private static final int SQUARE_LENGTH = 20;

    @BeforeEach
    void initMaps() {
        rectMap = new GameMap(RECT_WIDTH, RECT_HEIGHT);
        squareMap = new GameMap(SQUARE_LENGTH, SQUARE_LENGTH);
    }

    @Test
    void createRectRoom() throws Exception {
        assertFalse(rectMap.getTile(3, 3).isWalkable());
        rectMap.chiselRectangle(3,3,4,4);
        assertTrue(rectMap.getTile(3, 3).isWalkable());

        assertFalse(squareMap.getTile(3, 3).isWalkable());
        squareMap.chiselRectangle(1,1,SQUARE_LENGTH-2,SQUARE_LENGTH-2);
        assertTrue(squareMap.getTile(3, 3).isWalkable());
        assertTrue(squareMap.getTile(7, 10).isWalkable());
    }

    @Test
    void createCircleRoom() throws Exception {
        assertFalse(rectMap.getTile(8, 8).isWalkable());
        rectMap.chiselCircle(9,9,5);
        assertTrue(rectMap.getTile(8, 8).isWalkable());
        // corner is still wall
        assertFalse(rectMap.getTile(5, 5).isWalkable());
    }

    @Test
    void circleChiselClamps() throws Exception {
        assertFalse(rectMap.getTile(0, 0).isWalkable());
        rectMap.chiselCircle(3,2,4);
        assertTrue(rectMap.getTile(0, 0).isWalkable());
        assertTrue(rectMap.getTile(0, 2).isWalkable());
        assertTrue(rectMap.getTile(6, 2).isWalkable());
    }

}
