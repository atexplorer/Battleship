package org.atexplorer.service;

import org.atexplorer.entity.Player;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SetupService {

    //Todo: Create a method that can be called to determine if we need to remove the Ship name from the drop down
    public String placePiece(Player player, String location, String shipName){
        //I don't particularly like this implementation for a ship, it feels to complicated
        Ship ship = new Ship(ShipTypes.valueOf(shipName.toUpperCase()));

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

    public void setupNpc(Player player){
        Random random = new Random();
        for(Ship ship : player.getShips()){

        }
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

    //The two methods below could probably be moved to a utility method
    private int getX(String location){
        String[] cords = location.split("");
        return Integer.parseInt(cords[1]);
    }

    private int getY(String location){
        String[] cords = location.split("");
        return cords[0].charAt(0) - 65;
    }
}
