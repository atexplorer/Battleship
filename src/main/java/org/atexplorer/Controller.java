package org.atexplorer;

import org.atexplorer.entity.HumanPlayer;
import org.atexplorer.entity.Npc;
import org.atexplorer.entity.NpcDifficulty;
import org.atexplorer.entity.Player;
import org.atexplorer.gui.*;
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

    public void play(String param1, String param2, String param3){
        if(gameState.equals(GameState.SETUP)){
            setup(param1, param2);
            if(player1 != null && player2 != null){
                gameState = GameState.PLAYING;
            }
        }

    }

    //TODO: need to handle invalid player type and param responses
    public void setup(String playerType, String param){
        if(player1 == null){
            player1 = setupPlayer(playerType, param);
        }else{
            player2 = setupPlayer(playerType, param);
        }
    }

    public Player setupPlayer(String playerType, String param){
        if("NPC".equalsIgnoreCase(playerType)){
            NpcDifficulty difficulty;
            switch (param.toLowerCase()){
                case "easy" -> difficulty = NpcDifficulty.EASY;
                case "medium" -> difficulty = NpcDifficulty.MEDIUM;
                case "hard" -> difficulty = NpcDifficulty.HARD;
                default -> {
                    return null;
                }
            }
            return new Npc(difficulty);
        } else if ("Player".equalsIgnoreCase(playerType)) {
            return new HumanPlayer(param);
        }
        return null;
    }

    public boolean setPiece(Player player, String location, String shipName){
        String result = setupService.placePiece(player, location, shipName);
        return !result.equals(location);
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
