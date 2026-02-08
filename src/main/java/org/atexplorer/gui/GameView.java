package org.atexplorer.gui;

import org.atexplorer.Controller;
import org.atexplorer.piece.ShipTypes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ItemEvent;

public class GameView {

    private final int rows;
    private final int columns;

    private final Controller controller;

    private final JFrame jFrame;

    public GameView(Controller controller, int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.controller = controller;

        this.jFrame = new JFrame("Battleship");
        setupFrame();

        jFrame.add(createBoard(), BorderLayout.CENTER);

        jFrame.add(new ShipSelectPanel(), BorderLayout.SOUTH);

        jFrame.add(createTextPane(), BorderLayout.EAST);


        jFrame.setVisible(true);
    }

    private void setupFrame(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1000, 1000);
        jFrame.setLocationRelativeTo(null);

        //jFrame.setLayout(new GridBagLayout());
    }

    private JScrollPane createTextPane(){
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        return new JScrollPane(textArea);
    }

    private JPanel createBoard(){
        JPanel jPanel = new JPanel(new GridLayout(2, 0));

        jPanel.add(new BoardPanel(rows, columns, controller));
        jPanel.add(new BoardPanel(rows,columns, controller));


        return jPanel;
    }

    public JComboBox<String> createComboBox(){
        JComboBox<String> shipBox = new JComboBox<>();
        for(ShipTypes type : ShipTypes.values()){
            shipBox.addItem(type.getName());
        }

        shipBox.setSelectedIndex(0);
        shipBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                System.out.println(e.getItem());
            }
        });
        return shipBox;
    }
}
