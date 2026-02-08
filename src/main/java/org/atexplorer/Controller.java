package org.atexplorer;

import org.atexplorer.entity.Player;
import org.atexplorer.gui.*;
import org.atexplorer.piece.Board;
import org.atexplorer.piece.ShipTypes;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Controller {

    private static final int ROWS = 11;
    private static final int COLUMNS = 11;

    private final GameView gameView;
    public Controller(){
        this.gameView = new GameView(this, ROWS, COLUMNS);
    }

//    private final BattleshipFrame jFrame = new BattleshipFrame();
//    private JTextArea textArea = new JTextArea();
//
//    public void setupGameBoards(){
//
//
//        BoardPanel board1 = new BoardPanel(11,11,this);
//        BoardPanel board2 = new BoardPanel(11,11,this);
//
//        GridBagConstraints cBoard1 = new GridBagConstraints();
//
//        cBoard1.gridwidth = 2;
//        cBoard1.gridheight = 2;
//        cBoard1.gridx = 0;
//        cBoard1.gridy = 0;
//        jFrame.add(board1, cBoard1);
//
////        GridBagConstraints cBoard2 = new GridBagConstraints();
////
////        cBoard2.gridwidth = 2;
////        cBoard2.gridheight = 2;
////        cBoard2.gridx = 0;
////        cBoard2.gridy = 3;
////        jFrame.add(board2, cBoard2);
//
//        textArea = new JTextArea(ROWS*2, COLUMNS*2);
//        textArea.setLineWrap(true);
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//
//        GridBagConstraints cTextArea = new GridBagConstraints();
//
//        cTextArea.gridheight = 4;
//        cTextArea.gridwidth = 2;
//        cTextArea.gridx = 3;
//        cTextArea.gridy = 0;
//        jFrame.add(scrollPane, cTextArea);
//
//
//        GridBagConstraints cShipSelect = new GridBagConstraints();
//        cShipSelect.gridheight = 1;
//        cShipSelect.gridwidth = 2;
//        cShipSelect.gridx = 1;
//        cShipSelect.gridy = 6;
//
//        ShipSelectPanel shipSelectPanel = new ShipSelectPanel();
//        jFrame.add(shipSelectPanel, cShipSelect);
//        jFrame.validate();
//    }

    public void parseInput(String s){
        System.out.println(s);
    }


}
