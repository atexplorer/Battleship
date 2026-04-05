package org.atexplorer.entity;


import org.atexplorer.dto.MouseEvent;
import org.atexplorer.gui.InputObserver;
import org.atexplorer.utils.GameConfig;
import org.atexplorer.utils.ImageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class Box implements InputObserver {

    private BufferedImage activeImage;
    private final Queue<BufferedImage> images;
    private final GameConfig gc;

    private int screenX;
    private int screenY;

    private boolean moveable = true;

    private boolean selected = false;
    private int xDif;
    private int yDif;


    private String[] fileNames = new String[]{"RedSquare","GreenSquare"};

    public Box(GameConfig gc){
        this.gc = gc;

        //Todo: Each ship will need a default setter method, to set default screenX and screenY
        setDefaultLocation();

        this.images = new ArrayDeque<>();
        for(String fileName : fileNames){
            loadImage(fileName, gc.getTileSize(), gc.getTileSize());
        }
        activeImage = images.poll();
    }

    private void loadImage(String fileName, int height, int width){
        try{
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + fileName + ".png")));
            images.add(ImageUtility.scaledImage(bufferedImage, width, height, false));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(activeImage, screenX, screenY, null);
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
        images.add(activeImage);
        activeImage = images.poll();
    }

    private void shipHeld(int x, int y) {
        xDif = x - screenX;
        yDif = y -screenY;

        selected = true;
    }

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

    //Todo: this will need to get the default value from the ship it represents
    private void setDefaultLocation(){
        screenX = 25*gc.getTileSize();
        screenY = 12*gc.getTileSize();
    }

    private boolean inImage(int x, int y){
        return x >= screenX && x <= screenX + gc.getTileSize() && y >= screenY && y<= screenY + gc.getTileSize();
    }
}
