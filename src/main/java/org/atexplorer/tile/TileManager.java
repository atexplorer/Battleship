package org.atexplorer.tile;

import org.atexplorer.utils.GameConfig;
import org.atexplorer.utils.ImageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    private GameConfig gc;
    private Tile[] tiles;
    private int[][] board;

    public TileManager(GameConfig gc){
        this.gc = gc;
        tiles = new Tile[5];
        prepTiles();


    }

    private void prepTiles(){

        setup(4, "/tiles/WaterGrid.png");
    }

    private void setup(int index, String filePath){
        try{
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
            tiles[index] = new Tile(ImageUtility.scaledImage(image, gc.getTileSize(), gc.getTileSize(), false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        for(int row = 0; row <= gc.getScreenRowCount(); row++){
            for(int column = 0; column <= gc.getScreenColumnCount(); column++){
                g2.drawImage(tiles[4].getTile(), column * gc.getTileSize(), row*gc.getTileSize(), null);
            }
        }
    }

}
