package org.atexplorer;

import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Npc;
import org.atexplorer.entity.Player;
import org.atexplorer.gui.*;
import org.atexplorer.service.SetupService;



public class Controller {

    private final SetupService setupService;

    private static final int ROWS = 11;
    private static final int COLUMNS = 11;
    private Player player1;
    private Player player2;

    private final GameView gameView;

    public Controller(){
        this.setupService = new SetupService();
        this.player1 = new Npc();
        this.player2 = new HumanPlayer();
        this.gameView = new GameView(this, ROWS, COLUMNS, player1, player2);
    }

    //What we should probably do, is have this controller call the gameView for the current shipName value
    //this allows us to process any input and removes need for the GameView having some kind of state knowledge of whether to send the shipName
    public String processInput(Player player, String location, String shipName){
        return setupService.placePiece(player, location, shipName);
    }



    public void parseInput(String s){
        System.out.println(s);
    }


}
