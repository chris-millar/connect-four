package com.schrismillar.connect4.game.state;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Test;

public class TieGameStateTest {
    @Test
    public void isActiveReturnsFalse() {
        TieGameState tieGameState = new TieGameState();

        assertFalse("TieGameState should always return false for #isActive", tieGameState.isActive());
    }

    @Test
    public void winnerReturnsEmptyOptional() {
        TieGameState tieGameState = new TieGameState();

        assertEquals(Optional.empty(), tieGameState.winner());
    }

    @Test
    public void equalsReturnsTrueIfOtherIsAlsoTieGameState() {
        GameState otherGameState = mock(GameState.class);
        assertEquals(new TieGameState(), new TieGameState());
        assertNotEquals(otherGameState, new TieGameState());
    }

}