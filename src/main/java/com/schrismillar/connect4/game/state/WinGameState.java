package com.schrismillar.connect4.game.state;

import java.util.Optional;

import com.schrismillar.connect4.game.Player;

public class WinGameState implements GameState {
    private final Player winner;

    public WinGameState(Player winner) {
        this.winner = winner;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Optional<Player> winner() {
        return Optional.of(winner);
    }
}
