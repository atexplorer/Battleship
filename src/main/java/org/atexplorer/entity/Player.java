package org.atexplorer.entity;

import org.atexplorer.piece.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String name;
    private List<String> correctGuesses;
    private List<String> incorrectGuesses;
    private ArrayList<Ship> ships;
    private final String[][] board;

    public Player(String name){
        this.name = name;
        this.board = new String[10][10];
        correctGuesses = new ArrayList<>();
        incorrectGuesses = new ArrayList<>();
        ships = new ArrayList<>();
    }

    public List<String> getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(List<String> correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public List<String> getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public void setIncorrectGuesses(List<String> incorrectGuesses) {
        this.incorrectGuesses = incorrectGuesses;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public String[][] getBoard() {
        return board;
    }

    public int shipsLeft(){
        return this.ships.size();
    }


    //TODO: these two methods need to be removed and placed in a service method.
    public boolean hasGuessed(String guess){
        return correctGuesses.contains(guess) && incorrectGuesses.contains(guess);
    }

    public String getBoardValue(int y, int x){
        return board[y][x];
    }


}
