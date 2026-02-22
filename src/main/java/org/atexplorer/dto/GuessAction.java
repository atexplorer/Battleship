package org.atexplorer.dto;

import org.atexplorer.entity.Player;

public record GuessAction (Player player, String location) implements PlayerAction{
}
