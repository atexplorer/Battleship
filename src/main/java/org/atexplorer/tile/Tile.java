package org.atexplorer.tile;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage tile;
    private final boolean selectable;

    public Tile(BufferedImage tile, boolean selectable){
        this.tile = tile;
        this.selectable = selectable;
    }

    public BufferedImage getTile() {
        return tile;
    }

    public void setTile(BufferedImage tile) {
        this.tile = tile;
    }

    public boolean isSelectable() {
        return selectable;
    }
}
