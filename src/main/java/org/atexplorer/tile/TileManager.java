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
    private final Tile[] tiles;
    private String[][] board;

    public TileManager(GameConfig gc){
        this.gc = gc;
        tiles = new Tile[5];
        board = new String[gc.getScreenRowCount()][gc.getScreenColumnCount()];
        prepTiles();


    }

    private void prepTiles(){
        setup(0, "AttackGrid", true);
        setup(1, "AttackGrid_Hit2", false);
        setup(2, "AttackGrid_Miss2", false);
        setup(3, "tray", false);
        setup(4, "WaterGrid", false);
    }

    private void setup(int index, String filePath, boolean selectable){
        try{
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + filePath + ".png")));
            tiles[index] = new Tile(ImageUtility.scaledImage(image, gc.getTileSize(), gc.getTileSize(), false), selectable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Todo: this needs updated so that it isn't hard coded and allows for different board configurations
    public void draw(Graphics2D g2){
        int verticalHalf = gc.getScreenRowCount()/2;
        int horizontalHalf = gc.getScreenColumnCount()/2;
        for(int row = 0; row < gc.getScreenRowCount(); row++){
            for(int column = 0; column < gc.getScreenColumnCount(); column++){
                if(column < horizontalHalf - 5 || column >= horizontalHalf + 5
                        || row < verticalHalf-10 || row >= verticalHalf+10){
                    board[row][column] = "empty";
                    g2.drawImage(tiles[3].getTile(), column * gc.getTileSize(), row*gc.getTileSize(), null);
                } else if (row < verticalHalf) {
                    //My intent was to have these tiles be selectable. However, I am pretty certain that how I have this setup will result all the tiles flashing...
                    g2.drawImage(tiles[0].getTile(), column * gc.getTileSize(), row*gc.getTileSize(), null);
                    board[row][column] = "enemy";
                } else {
                    g2.drawImage(tiles[4].getTile(), column * gc.getTileSize(), row*gc.getTileSize(), null);
                    board[row][column] = "player";
                }

            }
        }
    }

    public boolean isPlayerLocation(int horizontalCord, int verticalCord){
        return board[verticalCord][horizontalCord].equals("player");
    }

}
