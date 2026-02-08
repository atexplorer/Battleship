package org.atexplorer.gui;

import org.atexplorer.piece.ShipTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class BattleshipFrame extends JFrame {

    public BattleshipFrame(){
        this.setTitle("Battleship");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);

        this.setLayout(new GridBagLayout());
        this.setVisible(true);
    }

    public void updateView(){
        GridBagConstraints cBoard1 = new GridBagConstraints();
        cBoard1.gridwidth = 2;
        cBoard1.gridheight = 2;
        cBoard1.gridx = 0;
        cBoard1.gridy = 0;

        this.add(testPanel1(), cBoard1);

        cBoard1 = new GridBagConstraints();
        cBoard1.gridwidth = 2;
        cBoard1.gridheight = 2;
        cBoard1.gridx = 2;
        cBoard1.gridy = 0;

        this.add(testPanel2(), cBoard1);
        this.validate();
    }

    private JPanel testPanel1(){
        JPanel panel = new JPanel();

        JButton button = new JButton("some button");
        button.addActionListener(e -> {
            System.out.println(button.getText());
        });

        panel.add(button);


        return panel;
    }

    private JPanel testPanel2(){
        JPanel panel = new JPanel();

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

        panel.add(shipBox);

        return panel;
    }




}
