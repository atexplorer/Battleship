package org.atexplorer.service;

import static org.junit.jupiter.api.Assertions.*;

import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.Npc;
import org.atexplorer.entity.NpcDifficulty;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.ShipTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NpcBoardServiceImplTest {

    private final BoardService boardService = new NpcBoardServiceImpl();
    private Player testPlayer;

    @BeforeEach
    public void init(){
        testPlayer = new Npc(NpcDifficulty.EASY);
    }

    @Test
    public void testNpcSetPiece_Pass(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, null, null, null);

        assertAll(() -> assertTrue(boardService.setPiece(psa)),
                () -> assertEquals(4, testPlayer.getShips().size()),
                () -> assertTrue(testPlayer.shipTypeCreated(ShipTypes.CRUISER)),
                () -> assertTrue(testPlayer.shipTypeCreated(ShipTypes.AIRCRAFT_CARRIER)),
                () -> assertTrue(testPlayer.shipTypeCreated(ShipTypes.SUBMARINE)),
                () -> assertTrue(testPlayer.shipTypeCreated(ShipTypes.BATTLESHIP)));
    }
}
