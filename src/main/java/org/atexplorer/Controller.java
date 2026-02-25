package org.atexplorer;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.dto.PlayerAction;
import org.atexplorer.dto.PlayerInitAction;
import org.atexplorer.entity.Player;
import org.atexplorer.gui.*;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.service.PlayerSetupService;



public class Controller {

    private enum GameState{
        SETUP,
        PLAYING,
        FINISHED
    }

    private final PlayerSetupService setupService;

    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    private Player player1;
    private Player player2;

    private final GameView gameView;

    private GameState gameState;

    public Controller(){
        this.setupService = new PlayerSetupService();
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
    //Todo: need to create a separate method that will handle each playerAction
    public String processBoardAction(PlayerAction playerAction){
        switch (playerAction){
            //PlaceShip should include placing the piece, if piece successfully placed: determine if any ships have been fully placed, removing any from the dropdown, tell board to update, check if all ships have been placed successfully
            case PlaceShipAction psa -> setupService.placePiece(psa);
            //Check if location had enemy piece, if enemy piece: check if any full ships have been sunk, tell board to update
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
