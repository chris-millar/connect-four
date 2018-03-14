package com.schrismillar.connect4.game;

import java.util.Optional;

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
