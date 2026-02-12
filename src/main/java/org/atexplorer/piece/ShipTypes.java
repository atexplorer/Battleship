package org.atexplorer.piece;

public enum ShipTypes {
    CRUISER("Cruiser", 2),
    SUBMARINE("Submarine", 3),
    BATTLESHIP("Battleship", 4),
    CARRIER("Carrier", 5);

    private final String name;
    private final int size;
    private final String character;

    ShipTypes(String name, int size){
        this.name = name;
        this.size = size;
        this.character = name.substring(0,1);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getCharacter() {
        return character;
    }
}
