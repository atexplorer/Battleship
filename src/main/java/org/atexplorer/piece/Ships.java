package org.atexplorer.piece;

import java.util.Arrays;
import java.util.List;

public enum Ships {
    BATTLESHIP("Battleship", Arrays.asList("Bow", "Mid", "Stern"), 3, 23, 12);

    private final String name;
    private final List<String> imageNames;
    private final int length;
    private final int defaultX;
    private final int defaultY;

    Ships(String name, List<String> imagesName, int length, int defaultX, int defaultY){
        this.name=name;
        this.imageNames =imagesName;
        this.length=length;
        this.defaultX=defaultX;
        this.defaultY=defaultY;
    }

    public int getLength() {
        return length;
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public String getName() {
        return name;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public int getDefaultY() {
        return defaultY;
    }
}
