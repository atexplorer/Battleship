package org.atexplorer.service;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.entity.Player;
import org.atexplorer.utils.LocationUtility;

public class GuessServiceImpl implements GuessService{
    @Override
    public boolean guess(GuessAction ga) {
        Player defendingPlayer = ga.player();
        String guess = ga.location();

        if(LocationUtility.locationCollision(guess, defendingPlayer)){
            defendingPlayer.addHitLocation(guess);
            return true;
        }else{
            defendingPlayer.addMissLocation(guess);
            return false;
        }
    }

    @Override
    public boolean alreadyGuessed(GuessAction ga){
        return ga.player().getMissLocations().contains(ga.location()) || ga.player().getHitLocations().contains(ga.location());
    }
}
