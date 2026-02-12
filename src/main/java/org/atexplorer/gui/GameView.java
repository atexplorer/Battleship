package org.atexplorer.gui;

import org.atexplorer.Controller;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.ShipTypes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ItemEvent;

public class GameView {

    private final int rows;
    private final int columns;

    private final Controller controller;

    private Player player1;
    private Player player2;

    private final JFrame jFrame;
    private JPanel topBoard;
    private JPanel bottomBoard;
    private ShipSelectPanel shipSelect;
    private JTextArea textArea;

    public GameView(Controller controller, int rows, int columns, Player player1, Player player2){
        this.rows = rows;
        this.columns = columns;
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

    public String placeShip(Player player, String location){
        String ship = shipSelect.getCurrentShip();

        textArea.append("you chose to place " + ship + " at location: " + location);
        return controller.placePiece(player, location, ship);
    }

    public void guessLocation(String location){

    }
}
