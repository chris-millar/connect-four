package com.schrismillar.connect4.game.state;

import java.util.Optional;

import com.schrismillar.connect4.game.player.Player;

public interface GameState {
    boolean isActive();
    Optional<Player> winner();
    String message();
}
