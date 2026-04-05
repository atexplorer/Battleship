package org.atexplorer.gui;

import org.atexplorer.dto.MouseEvent;

public interface MouseSubject {

    void addObserver(InputObserver mo);
    void removeObserver(InputObserver mo);
    void notifyObservers(MouseEvent e);
}
