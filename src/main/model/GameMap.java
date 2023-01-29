package model;

import static java.lang.Math.*;  // we do a lot of math stuff here
import static model.TileKind.*;  // just to make it less verbose

public class GameMap {
    private final Tile[][] tiles;
    private final int[] shape;

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Set activeMap to be a blank map of all wall tiles
    public GameMap(int mapWidth, int mapHeight) {
        this.tiles = new Tile[mapWidth][mapHeight];
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                try {
                    this.tiles[i][j] = new Tile(WALL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.shape = new int[]{mapWidth, mapHeight};
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Return shape of the current map
    public int[] getShape() {
        return this.shape;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    // REQUIRES: this.shape[0] > x + w, this.shape[1] > y + h
    // MODIFIES: this.tiles[x][y]
    // EFFECTS: Set a rectangle into floor tiles
    void chiselRectangle(int x, int y, int w, int h) throws Exception {
        for (int i = x; i < w+x; i++) {
            for (int j = y; j < h+y; j++) {
                this.setTile(i,j, FLOOR);
            }
        }
    }

    void chiselCircle(int x, int y, int r) throws Exception {
        // based off equation of a circle:
        // r^2 = (x-a)^2 + (y-b)^2

        // clamps
        int minX = max(x-r, 0);
        int maxX = min(x+r, this.shape[0]);
        int minY = max(y-r, 0);
        int maxY = min(y+r, this.shape[1]);

        // essentially, we will iterate over an rxr square
        // we will then see if the area of the rect from that point to (x, y) is
        // less than r^2, and if so we will chisel the area out
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (pow(r, 2) >= pow(abs(i-x), 2) + pow(abs(j-y),2)) {
                    this.setTile(i,j,FLOOR);
                }
            }
        }

    }


    // REQUIRES: x < this.shape[0], y < this.shape[1]
    // MODIFIES: this.tiles[x][y]
    // EFFECTS: Set a single tile to a new tile. Pretty wrapper
    public void setTile(int x, int y, TileKind kind) throws Exception {
        this.tiles[x][y] = new Tile(kind);
    }
}
