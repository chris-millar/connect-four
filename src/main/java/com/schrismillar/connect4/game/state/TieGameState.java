package com.schrismillar.connect4.game.state;

import java.util.Optional;

import com.schrismillar.connect4.game.player.Player;

public class TieGameState implements GameState {
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Optional<Player> winner() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "TieGameState{}";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TieGameState;
    }
}
