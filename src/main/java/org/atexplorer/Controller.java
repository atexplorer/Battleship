package org.atexplorer;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.dto.PlayerAction;
import org.atexplorer.dto.PlayerInitAction;
import org.atexplorer.entity.Player;
import org.atexplorer.gui.*;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.service.SetupService;



public class Controller {

    private enum GameState{
        SETUP,
        PLAYING,
        FINISHED
    }

    private final SetupService setupService;

    private static final int ROWS = 11;
    private static final int COLUMNS = 11;
    private Player player1;
    private Player player2;

    private final GameView gameView;

    private GameState gameState;

    public Controller(){
        this.setupService = new SetupService();
        this.gameState = GameState.SETUP;
        this.gameView = new GameView(this, ROWS, COLUMNS, player1, player2);
    }

    //This method will handle setting up player objects that will be used by the Game view
    public void initializePlayer(PlayerInitAction playerInitAction){
        if(player1 == null){
            player1 = setupService.setupPlayer(playerInitAction);
        }else{
            player2 = setupService.setupPlayer(playerInitAction);
        }
    }

    //This will process the action when a board button is pressed, which will either be to set a piece or to guess where an enemy piece is
    public String processBoardAction(PlayerAction playerAction){
        switch (playerAction){
            case PlaceShipAction psa -> setupService.placePiece(psa);
            case GuessAction ga -> System.out.println("Guess action passed to controller");
        }

        return "processed PlayerAction";
    }

    //Todo: this method isn't finished and needs to be set up to tell the view when to move to the next phase of the game
    private boolean shipListComplete(Player player){
        if(player.getShips().size() < ShipTypes.values().length){
            return false;
        }

        for (Ship ship : player.getShips()){
            if(ship.getPositions().length != ShipTypes.of(ship.getShipName()).getSize()){
                return false;
            }
        }

        return true;
    }


}
