package org.atexplorer;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.dto.PlayerAction;
import org.atexplorer.dto.PlayerInitAction;
import org.atexplorer.entity.Player;
import org.atexplorer.gui.*;
import org.atexplorer.piece.Ship;
import org.atexplorer.piece.ShipTypes;
import org.atexplorer.service.*;


public class Controller {

    private enum GameState{
        SETUP,
        PLAYING,
        FINISHED
    }

    private final PlayerSetupService setupService;
    private BoardService boardService;
    private GuessService guessService;

    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    private Player player1;
    private Player player2;

    private final GameView gameView;

    private GameState gameState;

    public Controller(){
        this.guessService = new GuessServiceImpl();
        this.setupService = new PlayerSetupService();
        this.boardService = new BoardServiceImpl();
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

    //Returning a String feels a little too "coding to an implementation" may want to reconsider
    //although, I could have it return a string, just not add any variables. That may be a better option.
    public String processBoardAction(PlayerAction playerAction){
        return switch (playerAction){
            case PlaceShipAction psa -> {
                //this is going to have to be a shared boolean with guess action
                if(boardService.setPiece(psa)){
                    yield psa.shipName() + " has been placed.";
                }else{
                    yield "Failed to place " + psa.shipName();
                }
            }
            case GuessAction ga -> processGuess(ga);
        };
    }

    private String processGuess(GuessAction ga){
        if(guessService.alreadyGuessed(ga)){
            return ga.location() + " has already been guessed.";
        }

        String response;

        if(guessService.guess(ga)){
            ShipTypes shipType;
            if((shipType = boardService.removePiece(ga)) != null){
                response = "You sunk my " + shipType.getName() + "!";
            }else{
                response = ga.location() + " is a hit!";
            }
        }else{
            response = ga.location() + " is a miss...";
        }

        return response;
    }

    private boolean shipListComplete(Player player){
        return player.getShips().size() == ShipTypes.values().length;
    }


}
