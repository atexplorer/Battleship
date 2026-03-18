package org.atexplorer;

import org.atexplorer.gui.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //Controller controller = new Controller();
        //controller.setupGameBoards();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Battleship");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}