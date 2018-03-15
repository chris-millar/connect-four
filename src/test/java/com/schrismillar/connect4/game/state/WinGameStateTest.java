package com.schrismillar.connect4.game.state;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Test;

import com.schrismillar.connect4.game.player.Player;

public class WinGameStateTest {
    @Test
    public void isActiveReturnsFalse() {
        WinGameState winGameState = new WinGameState(null);

        assertFalse("WinGameState should always return false for #isActive", winGameState.isActive());
    }

    @Test
    public void winnerReturnsEmptyOptional() {
        Player winningPlayer = mock(Player.class);
        WinGameState winGameState = new WinGameState(winningPlayer);

        assertEquals(Optional.of(winningPlayer), winGameState.winner());
    }

    @Test
    public void equalsReturnsTrueIfHaveSameWinner() {
        GameState otherGameState = mock(GameState.class);
        Player winner = mock(Player.class);
        Player otherWinner = mock(Player.class);
        assertEquals(new WinGameState(winner), new WinGameState(winner));
        assertNotEquals(new WinGameState(otherWinner), new WinGameState(winner));
        assertNotEquals(otherGameState, new WinGameState(winner));
    }

}