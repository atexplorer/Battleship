package org.atexplorer.entity;

import org.atexplorer.piece.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private final String name;
    private final List<String> hitLocations;
    private final List<String> missLocations;
    private final ArrayList<Ship> ships;
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

    public void addHitLocation(String hitLocation){
        this.hitLocations.add(hitLocation);
    }

    public List<String> getMissLocations() {
        return missLocations;
    }

    public void addMissLocation(String missLocation){
        missLocations.add(missLocation);
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship){this.ships.add(ship);}

    public String[][] getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public int shipsLeft(){
        return this.ships.size();
    }

    public void setBoardValue(String val, int y, int x){
        board[y][x] = val;
    }

    public String getBoardValue(int y, int x){
        return board[y][x];
    }


}
