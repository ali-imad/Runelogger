package model;

public class World {
    private Tile[][] activeMap;

    public World() {

    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Set activeMap to be a blank map of all wall tiles
    public void initMap(int mapWidth, int mapHeight) {
        this.activeMap = new Tile[mapWidth][mapHeight];
        for (int i = 0; i < mapWidth ; i++) {
            for (int j = 0; j < mapHeight ; j++) {
                this.activeMap[i][j] = Tile.WALL;
            }
        }
    }

    public Tile[][] getMap(){
        return this.activeMap;
    }
}
