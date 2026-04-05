package org.atexplorer.gui;

import org.atexplorer.dto.InputType;
import org.atexplorer.dto.MouseEvent;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class MouseHandler extends MouseAdapter implements MouseSubject{
    private final List<InputObserver> observers = new ArrayList<>();

    private boolean pressed;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e){
        notifyObservers(new MouseEvent(InputType.CLICKED, e.getX(), e.getY()));
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e){
        pressed = true;
        notifyObservers(new MouseEvent(InputType.PRESSED, e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e){
        pressed = false;
        notifyObservers(new MouseEvent(InputType.RELEASED, e.getX(), e.getY()));
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e){
        if(pressed){
            notifyObservers(new MouseEvent(InputType.DRAGGED, e.getX(), e.getY()));
        }
    }

    @Override
    public void addObserver(InputObserver mo) {
        observers.add(mo);
    }

    @Override
    public void removeObserver(InputObserver mo) {
        observers.remove(mo);
    }

    @Override
    public void notifyObservers(MouseEvent e) {
        for(InputObserver observer : observers){
            observer.update(e);
        }
    }


}
