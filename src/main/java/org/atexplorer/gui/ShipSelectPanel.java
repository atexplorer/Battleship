package org.atexplorer.gui;

import org.atexplorer.piece.ShipTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ShipSelectPanel extends JPanel {

    JComboBox<String> shipSelectBox;

    public ShipSelectPanel(){
        super(new GridLayout(1, 2, 0, 10));
        this.shipSelectBox = createComboBox();
        this.add(shipSelectBox);

    }

    private JComboBox<String> createComboBox(){
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

    public String getCurrentShip(){
        return  String.valueOf(shipSelectBox.getSelectedItem());
    }
}
