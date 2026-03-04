package org.atexplorer.service;

import static org.junit.jupiter.api.Assertions.*;

import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.Npc;
import org.atexplorer.entity.NpcDifficulty;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NpcBoardSetupServiceImplTest {

    private final BoardSetupService boardSetupService = new NpcBoardSetupServiceImpl();
    private Player testPlayer;

    @BeforeEach
    public void init(){
        testPlayer = new Npc(NpcDifficulty.EASY);
    }

    @Test
    public void testNpcSetPiece_Pass(){
        PlaceShipAction psa = new PlaceShipAction(testPlayer, null, null, null);
        assertTrue(boardSetupService.setPiece(psa));

        //Need to check and see if all ShipTypes have been successfully placed on the NPC board.
    }
}
