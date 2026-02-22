package org.atexplorer.dto;

import org.atexplorer.entity.Player;

public record PlaceShipAction (Player player,String location, String shipName) implements PlayerAction{
}
