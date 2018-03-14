package com.schrismillar.connect4.game;

import java.util.Optional;

public class TieGameState implements GameState {
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Optional<Player> winner() {
        return Optional.empty();
    }
}
