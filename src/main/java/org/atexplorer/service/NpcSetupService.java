package org.atexplorer.service;

import org.atexplorer.Controller;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.utils.LocationUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NpcSetupService {

    //maybe we should actually force the player to choose a direction when choosing the piece rather than letting each piece
    //This alleviates having to make sure the piece in the right direction
    private enum Direction{
        UP (-10),
        RIGHT (1),
        DOWN (10),
        LEFT (-1);

        private final int interval;

        Direction(int interval){
            this.interval = interval;
        }

        public int getInterval() {
            return interval;
        }
    }

    public void setupNpc(Player npc){
        Random random = new Random();
        for(ShipTypes shipType : ShipTypes.values()){
            int anchorLocation = random.nextInt(Controller.COLUMNS * Controller.ROWS);

            while(locationCollision(LocationUtility.getAlphaLocation(anchorLocation), npc)){
                anchorLocation = random.nextInt(Controller.COLUMNS * Controller.ROWS);
            }

            ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
            Direction direction = directions.get(random.nextInt(directions.size()));

            while(!maxLocationOnBoard(anchorLocation, direction, shipType.getSize())){
                directions.remove(direction);
                direction = directions.get(random.nextInt(directions.size()));
            }

            String[] possibleLocations = generateLocationsArray(anchorLocation, direction, shipType.getSize());


        }
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

    private String[] generateLocationsArray(int anchorLocation, Direction direction, int shipLength){
        int[] locations = new int[shipLength];
        locations[0] = anchorLocation;

        for(int i = 1; i < shipLength; i++){
            locations[i] = locations[i-1] + direction.getInterval();
        }

        return Arrays.stream(locations).mapToObj(LocationUtility::getAlphaLocation).toArray(String[]::new);
    }

    private boolean maxLocationOnBoard(int anchorLocation, Direction direction, int shipLength){
        int endLocation = anchorLocation + shipLength * direction.getInterval();

        return switch (direction){
            case Direction.UP -> endLocation > 0;
            case Direction.LEFT, Direction.RIGHT -> (endLocation/Controller.COLUMNS) == (anchorLocation/Controller.COLUMNS);
            case Direction.DOWN -> endLocation < (Controller.ROWS * Controller.COLUMNS);
        };
    }

}
