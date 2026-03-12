package org.atexplorer;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.dto.PlayerAction;
import org.atexplorer.dto.PlayerInitAction;
import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Npc;
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
    private final GuessService guessService;

    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    private Player player1;
    private Player player2;

    private final GameView gameView;

    private GameState gameState;

    public Controller(){
        this.setupService = new PlayerSetupService();
        this.guessService = new GuessServiceImpl();
        this.gameState = GameState.SETUP;
        this.gameView = new GameView(this, ROWS, COLUMNS, player1, player2);
    }

    public boolean initializePlayer(PlayerInitAction playerInitAction){
        Player player = setupService.setupPlayer(playerInitAction);

        if(player == null){
            return false;
        }

        switch (player){
            case Npc npc -> {
                boardService = new NpcBoardServiceImpl();
                boardService.setPiece(new PlaceShipAction(npc, null, null, null));
            }
            case HumanPlayer _ -> boardService = new BoardServiceImpl();
        }

        if(player1 == null){
            player1 = player;
        }else{
            player2 = player;
        }

        return true;
    }

    //Instead of returning a string, we will have the processBoardAction return a boolean to let the user know if the action was successful
    //Alternatively we could return a DTO letting the user know what the action failed
    public boolean processBoardAction(PlayerAction playerAction){
        return switch (playerAction){
            case PlaceShipAction psa -> boardService.setPiece(psa);
            case GuessAction ga -> processGuess(ga);
        };
    }

    private boolean processGuess(GuessAction ga){
        if(guessService.alreadyGuessed(ga)){
            return false;
        }

        if(guessService.guess(ga)){
            gameState = ga.player().allShipsSunk() ? GameState.FINISHED : GameState.PLAYING;
        }

        return true;
    }

}
