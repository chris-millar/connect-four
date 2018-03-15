package com.schrismillar.connect4.game.state;

import static org.junit.Assert.*;

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

}