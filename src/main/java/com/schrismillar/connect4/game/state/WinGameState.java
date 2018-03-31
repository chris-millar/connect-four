package com.schrismillar.connect4.game.state;

import java.util.Objects;
import java.util.Optional;

import com.schrismillar.connect4.game.player.Player;

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

    @Override
    public String message() {
        return "The Winner is " + winner.getName() + "!";
    }

    @Override
    public String toString() {
        return "WinGameState{" +
                "winner=" + winner +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WinGameState) {
            WinGameState that = (WinGameState) obj;
            return Objects.equals(this.winner, that.winner);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(winner);
    }
}
