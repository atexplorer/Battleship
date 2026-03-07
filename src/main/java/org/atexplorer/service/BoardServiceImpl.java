package org.atexplorer.service;

import org.atexplorer.Controller;
import org.atexplorer.dto.GuessAction;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Orientation;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.utils.LocationUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.atexplorer.utils.LocationUtility.locationCollision;

public class BoardServiceImpl implements BoardService {

    @Override
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

    //I feel like two things are happening here:
    //1. it removes the matching location from the ship array if it exists (we already know it does if this is called)
    //2. it then checks if the unhit positions is equal to 0, if it is then it removes it from the player's ship array
    @Override
    public boolean removePiece(Player player, String location) {
        for(Ship ship : player.getShips()){
            if(Arrays.asList(ship.getPositions()).contains(location)){
                ship.setPositions(reduceArray(ship.getPositions(), location));

                if(ship.getPositions().length == 0){
                    player.removeShip(ship);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public String[] reduceArray(String[] array, String removeVal){
        return Arrays.stream(array).filter(s -> !s.equals(removeVal)).toArray(String[]::new);
    }


    private String[] generateLocationsArray(int anchorLocation, Orientation orientation, int shipLength){
        int[] locations = new int[shipLength];
        locations[0] = anchorLocation;

        for(int i = 1; i < shipLength; i++){
            locations[i] = locations[i-1] + orientation.getInterval();
        }

        return Arrays.stream(locations).mapToObj(LocationUtility::getAlphaLocation).toArray(String[]::new);
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
