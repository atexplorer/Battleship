package org.atexplorer.service;

import org.atexplorer.Controller;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Orientation;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.utils.LocationUtility;

import java.util.Arrays;

public class BoardSetupServiceImpl implements BoardSetupService {

    public boolean setPiece(PlaceShipAction psa){
        String anchorLocationAlpha= psa.location();
        int anchorLocationNum = LocationUtility.getNumericLocation(anchorLocationAlpha);
        Orientation orientation = Orientation.valueOf(psa.direction().toUpperCase());
        ShipTypes shipType = ShipTypes.of(psa.shipName());

        if(locationCollision(anchorLocationAlpha, psa.player()) || !maxLocationOnBoard(anchorLocationNum, orientation, shipType.getSize()) || shipTypeCreated(shipType, psa.player())){
            return false;
        }

        String[] shipLocationArray = generateLocationsArray(anchorLocationNum, orientation, shipType.getSize());

        for(String location : shipLocationArray){
            if(locationCollision(location, psa.player())){
                return false;
            }
        }

        Ship ship = new Ship(shipType);
        ship.setPositions(shipLocationArray);
        psa.player().addShip(ship);
        return true;
    }

    private String[] generateLocationsArray(int anchorLocation, Orientation orientation, int shipLength){
        int[] locations = new int[shipLength];
        locations[0] = anchorLocation;

        for(int i = 1; i < shipLength; i++){
            locations[i] = locations[i-1] + orientation.getInterval();
        }

        return Arrays.stream(locations).mapToObj(LocationUtility::getAlphaLocation).toArray(String[]::new);
    }


    private boolean locationCollision(String inputLocation, Player player){
        for(Ship ship : player.getShips()){
            for(String location : ship.getPositions()){
                if(location.equals(inputLocation)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean maxLocationOnBoard(int anchorLocation, Orientation orrientation, int shipLength){
        int endLocation = anchorLocation + shipLength * orrientation.getInterval();

        return switch (orrientation){
            case Orientation.UP -> endLocation > 0;
            case Orientation.LEFT, Orientation.RIGHT -> (endLocation/ Controller.COLUMNS) == (anchorLocation/Controller.COLUMNS);
            case Orientation.DOWN -> endLocation < (Controller.ROWS * Controller.COLUMNS);
        };
    }

    private boolean shipTypeCreated(ShipTypes shipType, Player player){
        for(Ship ship : player.getShips()){
            if (ship.getShipType().equals(shipType)){
                return true;
            }
        }
        return false;
    }
}
