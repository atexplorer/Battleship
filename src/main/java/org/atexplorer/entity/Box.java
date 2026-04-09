package org.atexplorer.entity;


import org.atexplorer.dto.MouseEvent;
import org.atexplorer.gui.InputObserver;
import org.atexplorer.piece.Orientation;
import org.atexplorer.piece.Ships;
import org.atexplorer.utils.GameConfig;
import org.atexplorer.utils.ImageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Box implements InputObserver {


    private final Map<Integer, BufferedImage> activeImages;
    private final List<BufferedImage> destroyImage;

    private final Ships ship;
    private Orientation orientation;
    private final GameConfig gc;

    private boolean selected = false;
    private int screenX;
    private int screenY;
    private int xDif;
    private int yDif;

    private boolean moveable = true;

    public Box(GameConfig gc, Ships ship){
        this.gc = gc;
        this.ship = ship;

        setDefaultLocation();

        this.activeImages = new HashMap<>();
        for(String fileName : ship.getImageNames()){
            activeImages.put(activeImages.size(), loadImage(fileName, gc.getTileSize(), gc.getTileSize()));
        }

        this.destroyImage = new ArrayList<>();
        for(int i = 0; i < ship.getLength(); i++){
            destroyImage.add(loadImage("RedSquare", gc.getTileSize(), gc.getTileSize()));
        }


    }

    private BufferedImage loadImage(String fileName, int height, int width){
        BufferedImage bufferedImage = null;

        try{
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + fileName + ".png")));
            bufferedImage = ImageUtility.scaledImage(bufferedImage, width, height, true);
        }catch (IOException e){
            e.printStackTrace();
        }

        return bufferedImage;
    }

    public void draw(Graphics2D g2){
        for(Map.Entry<Integer, BufferedImage> imageEntry : activeImages.entrySet()) {
            int xCord = getImageX(imageEntry.getKey());
            int yCord = getImageY(imageEntry.getKey());
            g2.drawImage(imageEntry.getValue(), xCord, yCord, null);
        }
    }

    public void update(MouseEvent e){
        if(selected){
            switch (e.inputType()){
                case DRAGGED -> shipDragged(e.x(), e.y());
                case RELEASED -> placeShip();
            }
        }else if (inImage(e.x(), e.y())){
            switch (e.inputType()){
                case CLICKED -> shipClicked();
                case PRESSED -> shipHeld(e.x(), e.y());
            }
        }
    }

    private void shipClicked() {
        if(moveable){
            int nextOrientation = (Orientation.valueOf(orientation.toString()).ordinal() + 1) % Orientation.values().length;
            orientation = Orientation.values()[nextOrientation];
        }
    }

    private void shipHeld(int x, int y) {
        selected = true;
        xDif = x - screenX;
        yDif = y - screenY;
    }

    //todo: the boundary of the playable field should probably be handled by the tile manager
    private void shipDragged(int x, int y){
        screenX = x - xDif;
        screenY = y - yDif;

        if(screenX > gc.getMaxScreenWidth() - gc.getTileSize()){
            screenX = gc.getMaxScreenWidth() - gc.getTileSize();
        }else if(screenX < 0){
            screenX = 0;
        }

        if(screenY > gc.getMaxScreenHeight() - gc.getTileSize()){
            screenY = gc.getMaxScreenHeight() - gc.getTileSize();
        }else if (screenY < 0){
            screenY = 0;
        }
    }

    //todo: the snapping logic should probably be handled by some kind of service that validates the ships location on the board
    private void placeShip() {
        selected = false;

        int horizontalCord = screenX%gc.getTileSize() > (gc.getTileSize()/2) ? (screenX/gc.getTileSize() + 1) : screenX/gc.getTileSize();
        int verticalCord = screenY%gc.getTileSize() > (gc.getTileSize()/2) ? (screenY/gc.getTileSize() + 1) : screenY/gc.getTileSize();

        screenX = horizontalCord * gc.getTileSize();
        screenY = verticalCord * gc.getTileSize();

        /*if(gp.validPlayerShipLocation(horizontalCord, verticalCord)){
            screenX = horizontalCord * gc.getTileSize();
            screenY = verticalCord * gc.getTileSize();
        }else{
            setDefaultLocation();
        }*/
    }

    private void setDefaultLocation(){
        orientation = Orientation.DOWN;
        screenX = ship.getDefaultX()*gc.getTileSize();
        screenY = ship.getDefaultY()*gc.getTileSize();
    }

    private boolean inImage(int x, int y){
        for(Map.Entry<Integer, BufferedImage> imageEntry : activeImages.entrySet()) {
            int xCord = getImageX(imageEntry.getKey());
            int yCord = getImageY(imageEntry.getKey());
            if (x >= xCord && x <= xCord + gc.getTileSize() && y >= yCord && y<= yCord + gc.getTileSize()){
                return true;
            }
        }
        return false;
    }

    private int getImageX(int imagePos){
        return switch(orientation){
            case Orientation.RIGHT -> screenX + (gc.getTileSize()*imagePos);
            case Orientation.LEFT -> screenX - (gc.getTileSize()*imagePos);
            default -> screenX;
        };
    }

    private int getImageY(int imagePos){
        return switch(orientation){
            case Orientation.DOWN -> screenY + (gc.getTileSize()*imagePos);
            case Orientation.UP -> screenY - (gc.getTileSize()*imagePos);
            default -> screenY;
        };
    }
}
