package org.atexplorer.gui;

import org.atexplorer.piece.ShipTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShipComboBox extends JComboBox<String> implements ActionListener {

    public ShipComboBox(ShipTypes[] shipTypes){
        for(ShipTypes type : ShipTypes.values()){
            this.addItem(type.getName());
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

    }
}
