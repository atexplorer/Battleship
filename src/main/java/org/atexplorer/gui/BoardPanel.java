package org.atexplorer.gui;

import org.atexplorer.Controller;
import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {

    private final GameView gameView;
    private final Player player;

    public BoardPanel(int rows, int columns, GameView gameView, Player player){
        super(new GridLayout(rows, columns));
        this.gameView = gameView;
        this.player = player;
        setGrid(rows, columns);
    }

    private void setGrid(int rows, int columns){
        for(int r = 0; r < rows; r++){
            char character = (char) (64 + r);
            for(int c = 0; c < columns; c++){
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
                    JButton button = setupJButton(character, c);
                    this.add(button, r*11 + c);
                }
            }
        }
    }

    private JButton setupJButton(char character, int c) {
        JButton button = new JButton();
        String positionName = String.valueOf(character) + c;
        button.setName(positionName);
        button.setText(positionName);
        if(player instanceof HumanPlayer){
            button.addActionListener(e -> button.setText(gameView.placeShip(player, button.getText())));
        }else{
            button.addActionListener(e -> gameView.guessLocation(button.getText()));
            button.setEnabled(false);
        }
        return button;
    }

    public void updateButton(String buttonName){
        String[] splitName = buttonName.split("");
        int y = Character.codePointOf(splitName[0]) - 64;
        int x = Integer.parseInt(splitName[0]);
        int buttonLocation = y * 11 + x;
        JButton button = (JButton) this.getComponent(buttonLocation);

        String boardValue = player.getBoardValue(y-1, x);
        button.setText(boardValue);
    }

    public void disableButtons(){
        for(Component c : this.getComponents()){
            if(c instanceof JButton button){
                button.setEnabled(false);
            }
        }
    }

    public void enableButtons(){
        for(Component c : this.getComponents()){
            if(c instanceof JButton button){
                button.setEnabled(true);
            }
        }
    }
}
