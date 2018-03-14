package com.schrismillar.connect4.game;

import java.util.Optional;

public interface GameState {
    boolean isActive();
    Optional<Player> winner();
}
