package org.atexplorer.entity;

import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract sealed class Player permits Npc, HumanPlayer{

    private final String name;
    private final Set<String> guessedLocations;
    private final List<Ship> ships;

    public Player(String name){
        this.name = name;
        guessedLocations = new HashSet<>();
        ships = new ArrayList<>();
    }

    public List<Ship> getShips() {
        return List.copyOf(ships);
    }

    public void addShip(Ship ship){this.ships.add(ship);}

    public String getName() {
        return name;
    }

    public int shipsLeft(){
        return this.ships.size();
    }

    public boolean registerGuess(String location){
        boolean hit = false;
        for(Ship ship : ships){
            if(ship.locationCollision(location)){
                ship.registerHit(location);
                hit = true;
                break;
            }
        }
        guessedLocations.add(location);
        return hit;
    }

    public boolean allShipsSunk(){
        for(Ship ship : ships){
            if(!ship.isSunk()){
                return false;
            }
        }
        return true;
    }

    public boolean alreadyGuessed(String location){
        return guessedLocations.contains(location);
    }

    public boolean shipTypeCreated(ShipTypes shipType){
        for(Ship ship: ships){
            if (ship.getShipType().equals(shipType)){
                return true;
            }
        }
        return false;
    }

    public boolean isLocationOccupied(String location){
        for(Ship ship : ships){
            if(ship.locationCollision(location)){
                return true;
            }
        }
        return false;
    }


}
