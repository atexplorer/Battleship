package org.atexplorer.service;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.entity.Player;

public class GuessServiceImpl implements GuessService{

    @Override
    public boolean guess(GuessAction ga) {
        Player defendingPlayer = ga.player();
        String guess = ga.location();

        return defendingPlayer.registerGuess(guess);
    }

    @Override
    public boolean alreadyGuessed(GuessAction ga){
        return ga.player().alreadyGuessed(ga.location());
    }
}
