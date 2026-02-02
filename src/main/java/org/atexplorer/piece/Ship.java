package org.atexplorer.piece;

public class Ship {

    private final ShipTypes shipType;
    private String[] positions;

    public Ship(ShipTypes shipType) {
        this.shipType = shipType;
        this.positions = new String[shipType.getSize()];
    }

    public String getShipName(){
        return this.shipType.getName();
    }

    public String getShipCharacter(){
        return this.shipType.getCharacter();
    }

    public void setPositions(String[] positions){
        this.positions = positions;
    }

    public String[] getPositions() {
        return positions;
    }
}
