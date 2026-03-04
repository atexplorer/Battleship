package org.atexplorer.service;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.dto.PlaceShipAction;

public interface BoardService {

    boolean setPiece(PlaceShipAction psa);

    boolean removePiece(GuessAction ga);
}
