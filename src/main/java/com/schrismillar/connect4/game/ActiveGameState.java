package com.schrismillar.connect4.game;

import java.util.Optional;

public class ActiveGameState implements GameState {
    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public Optional<Player> winner() {
        return Optional.empty();
    }
}
