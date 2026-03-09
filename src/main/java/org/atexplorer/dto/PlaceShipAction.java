package org.atexplorer.dto;

import org.atexplorer.entity.Player;
import org.atexplorer.piece.Orientation;
import org.atexplorer.piece.ShipTypes;

public record PlaceShipAction (Player player, String location, ShipTypes shipType, Orientation orientation) implements PlayerAction{
}
