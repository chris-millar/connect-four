package com.schrismillar.connect4.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class GridDataTest {
    @Test
    public void equalsAndHashCode() {
        Cell cell1 = mock(Cell.class, "Cell 1");
        Cell cell2 = mock(Cell.class, "Cell 2");
        Cell cell3 = mock(Cell.class, "Cell 3");
        Cell cell4 = mock(Cell.class, "Cell 4");
        GridData gridData = new GridData(cellsWith(cell1, cell2, cell3, cell4));
        assertEquals(new GridData(cellsWith(cell1, cell2, cell3, cell4)), gridData);
        assertNotEquals(new GridData(cellsWith(mock(Cell.class), cell2, cell3, cell4)), gridData);
        assertNotEquals(new GridData(cellsWith(cell1, mock(Cell.class), cell3, cell4)), gridData);
        assertNotEquals(new GridData(cellsWith(cell1, cell2, mock(Cell.class), cell4)), gridData);
        assertNotEquals(new GridData(cellsWith(cell1, cell2, cell3, mock(Cell.class))), gridData);
        assertNotEquals("not a GridData", gridData);

    }

    private Cell[][] cellsWith(Cell ...cell) {
        Cell[][] cells = new Cell[2][2];
        cells[0][0] = cell[0];
        cells[0][1] = cell[1];
        cells[1][0] = cell[2];
        cells[1][1] = cell[3];
        return cells;
    }
}