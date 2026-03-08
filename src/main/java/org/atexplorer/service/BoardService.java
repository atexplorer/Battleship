package org.atexplorer.service;

import org.atexplorer.dto.GuessAction;
import org.atexplorer.dto.PlaceShipAction;
import org.atexplorer.entity.Player;
import org.atexplorer.piece.ShipTypes;

public interface BoardService {

    boolean setPiece(PlaceShipAction psa);

}
