package org.atexplorer.entity;

import org.atexplorer.gui.GamePanel;
import org.atexplorer.utils.GameConfig;
import org.atexplorer.utils.ImageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class Box implements MouseListener, MouseMotionListener {

    private BufferedImage activeImage;
    private final Queue<BufferedImage> images;
    private final GamePanel gp;
    private final GameConfig gc;

    private int screenX;
    private int screenY;
    private int xDif;
    private int yDif;

    private boolean moveable = false;

    private String[] fileNames = new String[]{"RedSquare","GreenSquare"};

    public Box(GamePanel gp, GameConfig gc){
        this.gc = gc;
        this.gp = gp;
        this.images = new ArrayDeque<>();

        screenX = gc.getMaxScreenWidth()/2 - gc.getTileSize();
        screenY = gc.getMaxScreenHeight()/2 - gc.getTileSize();

        gp.addMouseMotionListener(this);
        gp.addMouseListener(this);

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

    public void update(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
        images.add(activeImage);
        activeImage=images.poll();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(inImage(e.getX(), e.getY())) {
            xDif = e.getX() - screenX;
            yDif = e.getY() -screenY;
            moveable = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(inImage(e.getX(), e.getY())) {
            moveable = false;
            //This is where we would call the GamePanel to determine if this is a valid location or not.
            snapToLocation();
        }
    }

    private void snapToLocation(){


        if(screenX > gc.getMaxScreenWidth()){
            screenX = gc.getMaxScreenWidth() - gc.getTileSize();
        }else if(screenX < 0){
            screenX = 0;
        }else{
            if(screenX%gc.getTileSize() > (gc.getTileSize()/2) ){
                screenX = (screenX/gc.getTileSize() + 1) * gc.getTileSize();
            }else {
                screenX = screenX/gc.getTileSize() * gc.getTileSize();
            }
        }



        if(screenY > gc.getMaxScreenHeight()){
            screenY = gc.getMaxScreenHeight() - gc.getTileSize();
        }else if (screenY < 0){
            screenY = 0;
        }else {
            if(screenY%gc.getTileSize() > (gc.getTileSize()/2) ){
                screenY = (screenY/gc.getTileSize() + 1) * gc.getTileSize();
            }else {
                screenY = screenY/gc.getTileSize() * gc.getTileSize();
            }
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
