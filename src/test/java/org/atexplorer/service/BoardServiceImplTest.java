package org.atexplorer.service;

import static org.junit.jupiter.api.Assertions.*;

import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Orientation;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BoardServiceImplTest {

    private final BoardService boardService = new BoardServiceImpl();
    private Player testPlayer;

    @BeforeEach
    public void init(){
        testPlayer = new HumanPlayer("Test");
    }

    @Test
    public void testSetPiece_Pass(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "A2", ShipTypes.CRUISER, Orientation.DOWN);
        assertTrue(boardService.setPiece(psa));
        assertArrayEquals(new String[]{"A2", "B2"}, testPlayer.getShips().getFirst().getPositions());
    }
    @Test
    public void testSetPiece_Pass_ZeroPosition(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "B1", ShipTypes.CRUISER, Orientation.UP);
        assertTrue(boardService.setPiece(psa));
        assertArrayEquals(new String[]{"B1", "A1"}, testPlayer.getShips().getFirst().getPositions());
    }

    @Test
    public void testSetPiece_Fail_AnchorLocationCollision(){
        Ship testCruiser = new Ship(ShipTypes.CRUISER);
        testCruiser.setPositions(new String[]{"A2", "A3"});
        testPlayer.addShip(testCruiser);

        PlaceShipAction psa = new PlaceShipAction(testPlayer, "A2", ShipTypes.SUBMARINE, Orientation.RIGHT);
        assertFalse(boardService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardLeft(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "A1", ShipTypes.CRUISER, Orientation.LEFT);
        assertFalse(boardService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardRight(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "J10", ShipTypes.CRUISER, Orientation.RIGHT);
        assertFalse(boardService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardTop(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "A10", ShipTypes.SUBMARINE, Orientation.UP);
        assertFalse(boardService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_OffBoardBottom(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, "J6", ShipTypes.CRUISER, Orientation.DOWN);
        assertFalse(boardService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_ShipCreated(){
        Ship testCruiser = new Ship(ShipTypes.CRUISER);
        testCruiser.setPositions(new String[]{"A2", "A3"});
        testPlayer.addShip(testCruiser);

        PlaceShipAction psa = new PlaceShipAction(testPlayer, "E5", ShipTypes.CRUISER, Orientation.LEFT);
        assertFalse(boardService.setPiece(psa));
    }

    @Test
    public void testSetPiece_Fail_ShipCollisionInGenArray(){
        Ship testCruiser = new Ship(ShipTypes.CRUISER);
        testCruiser.setPositions(new String[]{"A2", "A3"});
        testPlayer.addShip(testCruiser);

        PlaceShipAction psa = new PlaceShipAction(testPlayer, "C2", ShipTypes.SUBMARINE, Orientation.UP);
        assertFalse(boardService.setPiece(psa));
    }


}
