package org.atexplorer.entity;

import org.atexplorer.piece.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String name;
    private List<String> hitLocations;
    private List<String> missLocations;
    private ArrayList<Ship> ships;
    private final String[][] board;

    public Player(String name){
        this.name = name;
        this.board = new String[10][10];
        hitLocations = new ArrayList<>();
        missLocations = new ArrayList<>();
        ships = new ArrayList<>();
    }

    public List<String> getHitLocations() {
        return hitLocations;
    }

    public void setHitLocations(List<String> correctGuesses) {
        this.hitLocations = correctGuesses;
    }

    public void addHitLocation(String hitLocation){
        this.hitLocations.add(hitLocation);
    }

    public List<String> getMissLocations() {
        return missLocations;
    }

    public void setMissLocations(List<String> missLocations) {
        this.missLocations = missLocations;
    }

    public void addMissLocation(String missLocation){
        missLocations.add(missLocation);
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public void addShip(Ship ship){this.ships.add(ship);}

    public String[][] getBoard() {
        return board;
    }

    public int shipsLeft(){
        return this.ships.size();
    }


    //TODO: these two methods need to be removed and placed in a service method.
    public boolean hasGuessed(String guess){
        return hitLocations.contains(guess) && missLocations.contains(guess);
    }

    public String getBoardValue(int y, int x){
        return board[y][x];
    }


}
