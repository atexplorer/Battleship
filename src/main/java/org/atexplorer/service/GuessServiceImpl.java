package org.atexplorer.service;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.entity.Player;
import org.atexplorer.utils.LocationUtility;

public class GuessServiceImpl implements GuessService{
    @Override
    public boolean guess(GuessAction ga) {
        Player defendingPlayer = ga.player();
        String guess = ga.location();

        if(alreadyGuessed(defendingPlayer, guess)){
            return false;
        }

        if(LocationUtility.locationCollision(guess, defendingPlayer)){
            defendingPlayer.addHitLocation(guess);
            return true;
        }else{
            defendingPlayer.addMissLocation(guess);
            return false;
        }
    }

    private boolean alreadyGuessed(Player player, String guess){
        return player.getMissLocations().contains(guess) || player.getHitLocations().contains(guess);
    }
}
