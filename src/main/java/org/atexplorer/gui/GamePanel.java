package org.atexplorer.gui;

import org.atexplorer.utils.GameConfig;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private final GameConfig gc;
    private Thread gameThread;

    public GamePanel(){
        gc = new GameConfig();
        gc.loadConfig("board.properties");
        this.setPreferredSize(new Dimension(gc.getScreenWidth(), gc.getScreenHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
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

//        while(gameThread != null){
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
//            lastTime = currentTime;
//
//            if (delta >= 1){
//                update();
//                repaint();
//
//                delta--;
//                drawCount++;
//            }
//
//            if(timer >= 1000000000){
//                System.out.println("FPS: " + drawCount);
//                drawCount=0;
//                timer=0;
//            }
//        }

    }


}
