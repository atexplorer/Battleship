package org.atexplorer;

import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Npc;
import org.atexplorer.entity.Player;
import org.atexplorer.gui.*;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    private static final int ROWS = 11;
    private static final int COLUMNS = 11;
    private Player player1;
    private Player player2;

    private final GameView gameView;

    public Controller(){
        this.player1 = new Npc();
        this.player2 = new HumanPlayer();
        this.gameView = new GameView(this, ROWS, COLUMNS, player1, player2);
    }

    public String placePiece(Player player, String location, String shipText){
        //I don't particularly like this implementation for a ship, it feels to complicated
        Ship ship = new Ship(ShipTypes.valueOf(shipText.toUpperCase()));

        //if we create a specific class for handling setup, we can create an enum set that handles identifying which ships haven't been started yet
        ArrayList<Ship> shipList = player.getShips();

        if(shipList.contains(ship)){
            ship=shipList.get(shipList.indexOf(ship));

            if(isValidLocation(ship, location)){
                ship.setPositions(updatePositions(ship.getPositions(), location));
                return ship.getShipCharacter();
            }else{
                return location;
            }
        }else{
            ship.setPositions(updatePositions(ship.getPositions(), location));
            shipList.add(ship);
            return ship.getShipCharacter();
        }
    }

    private int getX(String location){
        String[] cords = location.split("");
        return Integer.parseInt(cords[1]);
    }

    private int getY(String location){
        String[] cords = location.split("");
        return cords[0].charAt(0) - 65;
    }

    private boolean isValidLocation(Ship ship, String location){
        String[] currentLocations = ship.getPositions();

        if(currentLocations.length == 1){
            return checkHorizontalPosition(currentLocations, location) || checkVerticalPosition(currentLocations, location);
        }else{
            //determine if the piece is being placed vertically or horizontally
            String[] location1 = currentLocations[0].split("");
            String[] location2 = currentLocations[1].split("");
            if(location1[0].equals(location2[0])){
                return checkHorizontalPosition(currentLocations, location);
            }else{
                return checkVerticalPosition(currentLocations, location);
            }
        }
    }

    private boolean checkVerticalPosition(String[] currentLocations, String location){
        int nextX = getX(location);
        int nextY = getY(location);

        int currentX;
        int currentY;

        for(String currentLocation : currentLocations){
            currentX = getX(currentLocation);
            currentY = getY(currentLocation);
            if(currentLocation.equals(location)){
                return false;
            } else if (((nextY == (currentY + 1) || nextY == (currentY - 1)) && currentX == nextX)) {
                return true;
            }

        }

        return false;
    }

    private boolean checkHorizontalPosition(String[] currentLocations, String location){
        int nextX = getX(location);
        int nextY = getY(location);

        int currentX;
        int currentY;

        for(String currentLocation : currentLocations){
            currentX = getX(currentLocation);
            currentY = getY(currentLocation);
            if(currentLocation.equals(location)){
                return false;
            } else if (((nextX == (currentX + 1) || nextX == (currentX - 1)) && currentY == nextY)) {
                return true;
            }

        }

        return false;
    }

    private String[] updatePositions(String[] positions, String location){
        String[] newPositions = Arrays.copyOf(positions, positions.length+1);
        newPositions[newPositions.length-1]=location;
        return newPositions;
    }



    public void parseInput(String s){
        System.out.println(s);
    }


}
