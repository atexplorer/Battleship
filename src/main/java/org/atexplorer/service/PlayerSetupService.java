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

public class PlayerSetupService {

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

}
