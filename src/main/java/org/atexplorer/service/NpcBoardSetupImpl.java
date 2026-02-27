package org.atexplorer.service;

import org.atexplorer.Controller;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Orientation;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.utils.LocationUtility;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NpcBoardSetupImpl extends BoardSetupServiceImpl{

    @Override
    public boolean setPiece(PlaceShipAction psa) {

        Random random = new Random();
        ArrayList<Orientation> orientations = new ArrayList<>(Arrays.asList(Orientation.values()));

        for(ShipTypes shipType : ShipTypes.values()){

            PlaceShipAction npcShip = generateNpcShipAction(psa.player(), shipType, random, orientations);

            while(!super.setPiece(npcShip)){
                npcShip = generateNpcShipAction(psa.player(), shipType, random, orientations);
            }


        }
        return true;
    }

    private PlaceShipAction generateNpcShipAction(Player player, ShipTypes shipType, Random random, ArrayList<Orientation> orientations){
        return new PlaceShipAction(player,
                shipType.getName(),
                LocationUtility.getAlphaLocation(random.nextInt(Controller.COLUMNS * Controller.ROWS)),
                orientations.get(random.nextInt(orientations.size())).name());
    }
}
