package org.atexplorer.gui;

import org.atexplorer.tile.TileManager;
import org.atexplorer.utils.GameConfig;
import org.atexplorer.entity.Box;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private final GameConfig gc;
    private Thread gameThread;

    private final Box box;
    private final TileManager tileManager;

    public GamePanel(){
        gc = new GameConfig();
        gc.loadConfig("board.properties");
        this.setPreferredSize(new Dimension(gc.getMaxScreenWidth(), gc.getMaxScreenHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        box = new Box(this, gc);

        this.tileManager = new TileManager(gc);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000/gc.getFps();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                //update();
                repaint();

                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount=0;
                timer=0;
            }
        }

    }

    private void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        box.draw(g2);

    }

    //TODO: This will also verify that no other ship occupies this space
    public boolean validPlayerShipLocation(int horizontalCord, int verticalCord){
        return tileManager.isPlayerLocation(horizontalCord, verticalCord);
    }


}
