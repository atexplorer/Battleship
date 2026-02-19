package org.atexplorer.piece;

import java.util.HashMap;
import java.util.Map;

public enum ShipTypes {
    CRUISER("Cruiser", 2),
    SUBMARINE("Submarine", 3),
    BATTLESHIP("Battleship", 4),
    AIRCRAFT_CARRIER("Aircraft Carrier", 5);

    private final String name;
    private final int size;
    private final String character;

    private static final Map<String, ShipTypes> map = new HashMap<>();

    static {
        for(ShipTypes s : values()) map.put(s.name.toLowerCase(), s);
    }

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

    public static ShipTypes of(String name){
        return map.get(name.toLowerCase());
    }
}
