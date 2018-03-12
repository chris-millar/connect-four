package com.schrismillar.connect4.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class CellTest {
    private static final int ROW = 1;
    private static final int COL = 2;
    private static final CellOwnerId OWNER =mock(CellOwnerId.class);
    private static final CellOwnerId OTHER_OWNER = mock(CellOwnerId.class);

    @Test
    public void equalsAndHashCode() {
        Cell cell = new Cell(ROW, COL, OWNER);
        assertEquals(new Cell(ROW, COL, OWNER), cell);
        assertNotEquals(new Cell(2, COL, OWNER), cell);
        assertNotEquals(new Cell(ROW, 3, OWNER), cell);
        assertNotEquals(new Cell(ROW, COL, OTHER_OWNER), cell);
        assertNotEquals("not a cell", cell);
    }

    @Test
    public void ownerReturnsConstructorProvidedOwner() {
        Cell cell = new Cell(ROW, COL, OWNER);

        CellOwnerId owner = cell.owner();

        assertEquals(OWNER, owner);
    }

    @Test
    public void getColumnReturnsColumnIndex() {
        Cell cell = new Cell(ROW, COL, OWNER);

        int column = cell.getColumn();

        assertEquals(COL, column);
    }
}