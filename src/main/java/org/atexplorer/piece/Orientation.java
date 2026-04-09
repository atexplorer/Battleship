package org.atexplorer.piece;

public enum Orientation {
    DOWN (10),
    LEFT (-1),
    UP (-10),
    RIGHT (1);


    private final int interval;

    Orientation(int interval){
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }
}
