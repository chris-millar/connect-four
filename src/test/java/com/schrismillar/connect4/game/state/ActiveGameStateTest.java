package com.schrismillar.connect4.game.state;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Test;

public class ActiveGameStateTest {
    @Test
    public void isActiveReturnsTrue() {
        ActiveGameState activeGameState = new ActiveGameState();

        assertTrue("ActiveGameState should always return true for #isActive", activeGameState.isActive());
    }

    @Test
    public void winnerReturnsEmptyOptional() {
        ActiveGameState activeGameState = new ActiveGameState();

        assertEquals(Optional.empty(), activeGameState.winner());
    }

    @Test
    public void messageReturnsEmptyString() {
        ActiveGameState activeGameState = new ActiveGameState();

        assertEquals("", activeGameState.message());
    }

    @Test
    public void equalsReturnsTrueIfOtherIsAlsoActiveGameState() {
        GameState otherGameState = mock(GameState.class);
        assertEquals(new ActiveGameState(), new ActiveGameState());
        assertNotEquals(otherGameState, new ActiveGameState());
    }
}