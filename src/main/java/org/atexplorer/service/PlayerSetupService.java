package org.atexplorer.service;

import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.dto.PlayerInitAction;
import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Npc;
import org.atexplorer.entity.NpcDifficulty;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.utils.LocationUtility;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerSetupService implements SetupService{

    public Player setupPlayer(PlayerInitAction initPlayer){
        if("NPC".equalsIgnoreCase(initPlayer.playerType())){
            NpcDifficulty difficulty;
            switch (initPlayer.param().toLowerCase()){
                case "easy" -> difficulty = NpcDifficulty.EASY;
                case "medium" -> difficulty = NpcDifficulty.MEDIUM;
                case "hard" -> difficulty = NpcDifficulty.HARD;
                default -> {
                    return null;
                }
            }
            return new Npc(difficulty);
        } else if ("Player".equalsIgnoreCase(initPlayer.playerType())) {
            return new HumanPlayer(initPlayer.param());
        }
        return null;
    }

    public String placePiece(PlaceShipAction psa){
        Player player = psa.player();
        String location = psa.location();
        Ship ship = new Ship(ShipTypes.valueOf(psa.shipName().toUpperCase()));

        ArrayList<Ship> shipList = player.getShips();

        if(shipList.contains(ship)){
            ship=shipList.get(shipList.indexOf(ship));

            if(isInlineLocation(ship, location)){
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


    private boolean isInlineLocation(Ship ship, String location){
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
        int nextX = LocationUtility.getX(location);
        int nextY = LocationUtility.getY(location);

        int currentX;
        int currentY;

        for(String currentLocation : currentLocations){
            currentX = LocationUtility.getX(currentLocation);
            currentY = LocationUtility.getY(currentLocation);
            if(currentLocation.equals(location)){
                return false;
            } else if (((nextY == (currentY + 1) || nextY == (currentY - 1)) && currentX == nextX)) {
                return true;
            }

        }

        return false;
    }

    private boolean checkHorizontalPosition(String[] currentLocations, String location){
        int nextX = LocationUtility.getX(location);
        int nextY = LocationUtility.getY(location);

        int currentX;
        int currentY;

        for(String currentLocation : currentLocations){
            currentX = LocationUtility.getX(currentLocation);
            currentY = LocationUtility.getY(currentLocation);
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

}
