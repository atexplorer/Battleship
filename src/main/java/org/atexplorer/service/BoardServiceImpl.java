package org.atexplorer.service;

import org.atexplorer.Controller;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Orientation;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.utils.LocationUtility;

import java.util.Arrays;

public class BoardServiceImpl implements BoardService {

    @Override
    public boolean setPiece(PlaceShipAction psa){
        Player defendingPlayer = psa.player();
        String anchorLocation= psa.location();
        Orientation orientation = psa.orientation();
        ShipTypes shipType = psa.shipType();

        if(!maxLocationOnBoard(anchorLocation, orientation, shipType.getSize()) || defendingPlayer.shipTypeCreated(shipType) || defendingPlayer.isLocationOccupied(anchorLocation)){
            return false;
        }

        String[] shipLocationArray = generateLocationsArray(anchorLocation, orientation, shipType.getSize());

        for(String location : shipLocationArray){
            if(defendingPlayer.isLocationOccupied(location)){
                return false;
            }
        }

        Ship ship = new Ship(shipType);
        ship.setPositions(shipLocationArray);
        defendingPlayer.addShip(ship);
        return true;
    }


    private String[] generateLocationsArray(String anchorLocation, Orientation orientation, int shipLength){
        int[] locations = new int[shipLength];
        int numericLocation = LocationUtility.getNumericLocation(anchorLocation);
        locations[0] = numericLocation;

        for(int i = 1; i < shipLength; i++){
            locations[i] = locations[i-1] + orientation.getInterval();
        }

        return Arrays.stream(locations).mapToObj(LocationUtility::getAlphaLocation).toArray(String[]::new);
    }

    private boolean maxLocationOnBoard(String anchorLocation, Orientation orientation, int shipLength){
        int numericLocation = LocationUtility.getNumericLocation(anchorLocation);
        int endLocation = numericLocation + (shipLength-1) * orientation.getInterval();

        return switch (orientation){
            case Orientation.UP -> endLocation >= 0;
            case Orientation.LEFT, Orientation.RIGHT -> (endLocation/ Controller.COLUMNS) == (numericLocation/Controller.COLUMNS);
            case Orientation.DOWN -> endLocation < (Controller.ROWS * Controller.COLUMNS);
        };
    }




}
