package org.atexplorer.service;

import static org.junit.jupiter.api.Assertions.*;

import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BoardSetupServiceImplTest {

    private final BoardSetupService boardSetupService = new BoardSetupServiceImpl();
    private Player testPlayer;

    @BeforeEach
    public void init(){
        testPlayer = new HumanPlayer("Test");
    }

    @Test
    public void testSetPiece_Pass(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "A2", "Cruiser", "Down");
        assertTrue(boardSetupService.setPiece(psa));
        assertArrayEquals(new String[]{"A2", "A3"}, testPlayer.getShips().getFirst().getPositions());
    }

    @Test
    public void testSetPiece_Fail_AnchorLocationCollision(){
        Ship testCruiser = new Ship(ShipTypes.CRUISER);
        testCruiser.setPositions(new String[]{"A2", "A3"});
        testPlayer.addShip(testCruiser);

        PlaceShipAction psa = new PlaceShipAction(testPlayer, "A2", "Submarine", "Right");
        assertFalse(boardSetupService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardLeft(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "A2", "Cruiser", "Left");
        assertFalse(boardSetupService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardRight(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "J2", "Cruiser", "Right");
        assertFalse(boardSetupService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardTop(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "E2", "Submarine", "Up");
        assertFalse(boardSetupService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardBottom(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "G10", "Cruiser", "Down");
        assertFalse(boardSetupService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_ShipCreated(){
        Ship testCruiser = new Ship(ShipTypes.CRUISER);
        testCruiser.setPositions(new String[]{"A2", "A3"});
        testPlayer.addShip(testCruiser);

        PlaceShipAction psa = new PlaceShipAction(testPlayer, "E5", "Cruiser", "Left");
        assertFalse(boardSetupService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_ShipCollisionInGenArray(){
        Ship testCruiser = new Ship(ShipTypes.CRUISER);
        testCruiser.setPositions(new String[]{"A2", "A3"});
        testPlayer.addShip(testCruiser);

        PlaceShipAction psa = new PlaceShipAction(testPlayer, "C2", "Submarine", "Left");
        assertFalse(boardSetupService.setPiece(psa));
    }


}
