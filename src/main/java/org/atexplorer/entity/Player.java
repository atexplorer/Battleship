package org.atexplorer.entity;

import org.atexplorer.piece.Board;
import org.atexplorer.piece.Ship;

import java.util.List;

public abstract class Player {

    private List<String> correctGuesses;
    private List<String> incorrectGuesses;
    private List<Ship> ships;
    private Board board;

    public Player(){
        this.board = new Board();
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

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int shipsLeft(){
        return this.ships.size();
    }

    public boolean hasGuessed(String guess){
        return correctGuesses.contains(guess) && incorrectGuesses.contains(guess);
    }


}
