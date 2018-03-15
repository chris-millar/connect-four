package com.schrismillar.connect4.game.state;

import java.util.Optional;

import com.schrismillar.connect4.game.player.Player;

public class ActiveGameState implements GameState {
    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public Optional<Player> winner() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "ActiveGameState{}";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ActiveGameState;
    }
}
