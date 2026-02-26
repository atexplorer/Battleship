package org.atexplorer.gui;

import org.atexplorer.Controller;
import org.atexplorer.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GameView {

    private final Controller controller;

    private Player player1;
    private Player player2;

    private final JFrame jFrame;
    private JPanel topBoard;
    private JPanel bottomBoard;
    private final ShipSelectPanel shipSelect;
    private final JTextArea textArea;

    public GameView(Controller controller, int rows, int columns, Player player1, Player player2){
        this.controller = controller;
        this.player1 = player1;
        this.player2 = player2;

        this.jFrame = new JFrame("Battleship");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1000, 1000);
        jFrame.setLocationRelativeTo(null);

        topBoard = new BoardPanel(rows, columns, this, player1);
        bottomBoard = new BoardPanel(rows,columns, this, player2);

        JPanel jPanel = new JPanel(new GridLayout(2, 0));
        jPanel.add(topBoard);
        jPanel.add(bottomBoard);
        jFrame.add(jPanel, BorderLayout.CENTER);

        this.shipSelect = new ShipSelectPanel();
        jFrame.add(shipSelect, BorderLayout.SOUTH);

        textArea = new JTextArea(rows, columns);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        jFrame.add(new JScrollPane(textArea), BorderLayout.EAST);

        jFrame.setVisible(true);
    }

    /*public String placeShip(Player player, String location){
        String ship = shipSelect.getCurrentShip();

        String response = controller.processBoardAction(player, location, ship);
        String output;

        //This should probably be in the controller method... and we should have a method that adds text to the textArea
        if(response.equals(location)){
            output = location + " is an invalid location for ship " + ship;
        } else if (response.equals("X")) {
            output = "That's a hit!";
        } else if (response.equals("O")) {
            output = "That's a miss...";
        }else{
            output = "You placed " + ship + " at location: " + location;
        }
        textArea.append(output + "\n");
        return response;
    }*/

    public String placeShip(Player player, String location){
        System.out.println(player.toString() + " " + location);
        return "foo";
    }

    public void guessLocation(String location){

    }
}
