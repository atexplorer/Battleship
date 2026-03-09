package org.atexplorer.piece;

import java.util.Arrays;

public class Ship {

    private final ShipTypes shipType;
    private String[] hitLocations;
    private String[] positions;
    private boolean isSunk;

    public Ship(ShipTypes shipType) {
        this.shipType = shipType;
        this.hitLocations = new String[0];
        this.positions = new String[0];
        isSunk = false;
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

    public boolean isSunk() {
        return isSunk;
    }

    public boolean locationCollision(String inputLocation){
        for(String location : positions){
            if(location.equals(inputLocation)){
                return true;
            }
        }
        return false;
    }

    public void registerHit(String location){
        removeLocation(location);
        addHitLocation(location);
    }

    private void removeLocation(String location){
        positions = Arrays.stream(positions).filter(s -> !s.equals(location)).toArray(String[]::new);
        if(positions.length == 0){
            isSunk=true;
        }
    }

    private void addHitLocation(String location){
        hitLocations = Arrays.copyOf(hitLocations, hitLocations.length+1);
        hitLocations[hitLocations.length-1] = location;
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
