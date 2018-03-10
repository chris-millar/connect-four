package com.schrismillar.connect4.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {
    private static final int ROW = 1;
    private static final int COL = 2;

    @Test
    public void equalsAndHashCode() {
        Cell cell = new Cell(ROW, COL, PlayerId.NONE);
        assertEquals(new Cell(ROW, COL, PlayerId.NONE), cell);
        assertNotEquals(new Cell(2, COL, PlayerId.NONE), cell);
        assertNotEquals(new Cell(ROW, 3, PlayerId.NONE), cell);
        assertNotEquals(new Cell(ROW, COL, PlayerId.PLAYER_ONE), cell);
        assertNotEquals("not a cell", cell);
    }

    @Test
    public void ownerReturnsConstructorProvidedOwner() {
        PlayerId owner = new Cell(ROW, COL, PlayerId.NONE).owner();
        assertEquals(PlayerId.NONE, owner);
    }
}