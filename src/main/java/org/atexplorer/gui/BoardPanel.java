package org.atexplorer.gui;

import org.atexplorer.Controller;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private final int ROWS;
    private final int COLUMNS;
    private final Controller controller;

    public BoardPanel(int rows, int columns, Controller controller){
        super(new GridLayout(rows, columns));
        this.controller = controller;
        this.ROWS = rows;
        this.COLUMNS = columns;
        setGrid();
        System.out.println(this.getSize());
    }

    private void setGrid(){
        for(int r = 0; r < ROWS; r++){
            char character = (char) (64 + r);
            for(int c = 0; c < COLUMNS; c++){
                if (r == 0 || c == 0) {
                    JLabel label = new JLabel();

                    if (r == 0 && c == 0) {
                        label.setText("Board");
                    } else if (r == 0) {
                        label.setText(String.valueOf(c));
                    } else {
                        label.setText(String.valueOf(character));
                    }

                    this.add(label);
                }else {
                    JButton button = new JButton();
                    String positionName = String.valueOf(character) + c;
                    button.setText(positionName);
                    button.addActionListener(e -> {
                        controller.parseInput("You choose position: " + button.getText() + "\n");
                    });
                    this.add(button);
                }
                //System.out.println((r * 11) + c);
            }
        }
    }
}
