package org.atexplorer.tile;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage tile;

    public Tile(BufferedImage tile){
        this.tile = tile;
    }

    public BufferedImage getTile() {
        return tile;
    }

    public void setTile(BufferedImage tile) {
        this.tile = tile;
    }
}
