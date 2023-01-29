package model;

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
                    this.tiles[i][j] = new Tile(TileKind.WALL);
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
    // EFFECTS: Set a single tile to a new tile
    void chiselRectangle(int x, int y, int w, int h) throws Exception {
        for (int i = x; i < w+x; i++) {
            for (int j = y; j < h+y; j++) {
                this.tiles[i][j] = new Tile(TileKind.FLOOR);
            }
        }
    }

    // REQUIRES: x < this.shape[0], y < this.shape[1]
    // MODIFIES: this.tiles[x][y]
    // EFFECTS: Set a single tile to a new tile
    public void setTile(int x, int y, TileKind kind) throws Exception {
        this.tiles[x][y] = new Tile(kind);
    }
}
