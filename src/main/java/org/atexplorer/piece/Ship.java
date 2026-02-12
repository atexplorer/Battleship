package org.atexplorer.piece;

public class Ship {

    private final ShipTypes shipType;
    private String[] positions;

    public Ship(ShipTypes shipType) {
        this.shipType = shipType;
        this.positions = new String[0];
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

    public ShipTypes getShipType() {
        return shipType;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }

        if(obj == null){
            return false;
        }

        if(!(obj instanceof Ship)){
            return false;
        }

        return this.shipType.equals(((Ship) obj).getShipType());
    }
}
