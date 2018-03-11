package com.schrismillar.connect4.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class GridDataTest {
    private static final Cell CELL_1 = mock(Cell.class, "Cell 1");
    private static final Cell CELL_2 = mock(Cell.class, "Cell 2");
    private static final Cell CELL_3 = mock(Cell.class, "Cell 3");
    private static final Cell CELL_4 = mock(Cell.class, "Cell 4");

    @Test
    public void equalsAndHashCode() {
        GridData gridData = new GridData(twoByTwoArrayWith(CELL_1, CELL_2, CELL_3, CELL_4));

        assertEquals(   new GridData(twoByTwoArrayWith(CELL_1, CELL_2, CELL_3, CELL_4)),           gridData);
        assertNotEquals(new GridData(twoByTwoArrayWith(mock(Cell.class), CELL_2, CELL_3, CELL_4)), gridData);
        assertNotEquals(new GridData(twoByTwoArrayWith(CELL_1, mock(Cell.class), CELL_3, CELL_4)), gridData);
        assertNotEquals(new GridData(twoByTwoArrayWith(CELL_1, CELL_2, mock(Cell.class), CELL_4)), gridData);
        assertNotEquals(new GridData(twoByTwoArrayWith(CELL_1, CELL_2, CELL_3, mock(Cell.class))), gridData);
        assertNotEquals("not a GridData", gridData);
    }

    @Test
    public void heightReturnsLengthOfRowArray() {
        GridData gridData = new GridData(new Cell[4][3]);

        assertEquals(4, gridData.height());
    }

    @Test
    public void heightReturnsLengthOfColumnArrayFromFirstRow() {
        GridData gridData = new GridData(new Cell[4][3]);

        assertEquals(3, gridData.width());
    }

    @Test
    public void getCellAtReturnsCellAtSpecifiedPosition() {
        GridData gridData = new GridData(twoByTwoArrayWith(CELL_1, CELL_2, CELL_3, CELL_4));

        Cell cell = gridData.getCellAt(0, 1);

        assertEquals(CELL_2, cell);
    }

    private Cell[][] twoByTwoArrayWith(Cell ...cell) {
        Cell[][] cells = new Cell[2][2];
        cells[0][0] = cell[0];
        cells[0][1] = cell[1];
        cells[1][0] = cell[2];
        cells[1][1] = cell[3];
        return cells;
    }
}