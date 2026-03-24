package org.atexplorer.entity;

import org.atexplorer.utils.GameConfig;
import org.atexplorer.utils.ImageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Box implements MouseListener, MouseMotionListener {

    private BufferedImage box;
    private GameConfig gc;

    private int screenX;
    private int screenY;
    private int xDif;
    private int yDif;

    private boolean moveable = false;

    public Box(GameConfig gc){
        this.gc = gc;
        screenX = gc.getMaxScreenWidth()/2 - gc.getTileSize();
        screenY = gc.getMaxScreenHeight()/2 - gc.getTileSize();

        box = loadImage(gc.getTileSize(), gc.getTileSize());
    }

    private BufferedImage loadImage(int height, int width){
        BufferedImage bufferedImage = null;
        try{
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/RedSquare.png")));
            bufferedImage = ImageUtility.scaledImage(bufferedImage, width, height, false);
        }catch (IOException e){
            e.printStackTrace();
        }

        return bufferedImage;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(box, screenX, screenY, null);
    }

    public void update(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(inImage(e.getX(), e.getY())) {
            System.out.println("Why are you holding me");
            xDif = e.getX() - screenX;
            yDif = e.getY() -screenY;
            moveable = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(inImage(e.getX(), e.getY())) {
            System.out.println("Thank you for letting go");
            moveable = false;
            snapToLocation();
        }
    }

    private void snapToLocation(){
        screenX = screenX/gc.getTileSize() * gc.getTileSize();
        if(screenX > gc.getMaxScreenWidth()){
            screenX = gc.getMaxScreenWidth() - gc.getTileSize();
        }else if(screenX < 0){
            screenX = 0;
        }
        screenY = screenY/ gc.getTileSize() * gc.getTileSize();

        if(screenY > gc.getMaxScreenHeight()){
            screenY = gc.getMaxScreenHeight() - gc.getTileSize();
        }else if (screenY < 0){
            screenY = 0;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private boolean inImage(int x, int y){
        return x >= screenX && x <= screenX + gc.getTileSize() && y >= screenY && y<= screenY + gc.getTileSize();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(moveable){
            screenX = e.getX() - xDif;
            screenY = e.getY() - yDif;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
