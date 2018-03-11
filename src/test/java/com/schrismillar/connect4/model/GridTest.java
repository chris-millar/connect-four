package com.schrismillar.connect4.model;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class GridTest {
    private static final CellOwnerId STARTING_OWNER = cellOwnerIdWithToStringName("Starting Owner");
    private static final CellOwnerId NEW_OWNER = cellOwnerIdWithToStringName("New Owner");

    @Test
    public void cellOwnersReturnsListOfOwnersOfAllCellsInGrid() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);

        List<List<CellOwnerId>> owners = grid.cellOwners();

        assertEquals(asList(asList(STARTING_OWNER, STARTING_OWNER), asList(STARTING_OWNER, STARTING_OWNER)), owners);
    }

    @Test
    public void getGridDataReturnsGridDataRepresentingTheCurrent2dArrayOfCellsThatMakeUpTheGrid() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);

        GridData gridData = grid.getGridData();

        Cell[][] cells = new Cell[2][2];
        cells[0][0] = new Cell(0, 0, STARTING_OWNER);
        cells[0][1] = new Cell(0, 1, STARTING_OWNER);
        cells[1][0] = new Cell(1, 0, STARTING_OWNER);
        cells[1][1] = new Cell(1, 1, STARTING_OWNER);
        GridData expectedGridData = new GridData(cells);
        assertEquals(expectedGridData, gridData);
    }

    @Test
    public void getGridDataReturnsGridDataWhoseArrayIsNotTheSameInstanceOfTheOneBackingGrid() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);
        GridData gridData = grid.getGridData();

        grid.assignCellAtPositionTo(0, 0, NEW_OWNER);
        grid.assignCellAtPositionTo(1, 1, NEW_OWNER);

        Cell[][] cells = new Cell[2][2];
        cells[0][0] = new Cell(0, 0, STARTING_OWNER);
        cells[0][1] = new Cell(0, 1, STARTING_OWNER);
        cells[1][0] = new Cell(1, 0, STARTING_OWNER);
        cells[1][1] = new Cell(1, 1, STARTING_OWNER);
        GridData expectedGridData = new GridData(cells);
        assertEquals(expectedGridData, gridData);
    }

    @Test
    public void getFirstRowReturnsListOfFirstRowOfCells() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);

        List<Cell> firstRow = grid.getFirstRow();

        Cell cell1 = new Cell(0, 0, STARTING_OWNER);
        Cell cell2 = new Cell(0, 1, STARTING_OWNER) ;
        assertEquals(asList(cell1, cell2), firstRow);
    }

    @Test
    public void getFirstRowReturnsListNotBackedByGridsCellsArray() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);
        List<Cell> firstRow = grid.getFirstRow();

        firstRow.set(0, new Cell(0, 0, NEW_OWNER));

        Cell cell1 = new Cell(0, 0, STARTING_OWNER);
        Cell cell2 = new Cell(0, 1, STARTING_OWNER) ;
        assertEquals(asList(cell1, cell2), grid.getFirstRow());
    }

    @Test
    public void getColumnReturnsSpecifiedColumnOfCellsAsList() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);

        List<Cell> secondColumn = grid.getColumn(1);

        Cell cell1 = new Cell(0, 1, STARTING_OWNER);
        Cell cell2 = new Cell(1, 1, STARTING_OWNER);
        assertEquals(asList(cell1, cell2), secondColumn);
    }

    @Test
    public void getColumnReturnsListNotBackedByGridsCellsArray() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);
        List<Cell> secondColumn = grid.getColumn(1);

        secondColumn.set(1, new Cell(1, 1, NEW_OWNER));

        Cell cell1 = new Cell(0, 1, STARTING_OWNER);
        Cell cell2 = new Cell(1, 1, STARTING_OWNER);
        assertEquals(asList(cell1, cell2), grid.getColumn(1));
    }

    @Test
    public void assignCellAtPositionToCreatesNewCellWithProvidedValuesAndPlacesInGridAtThatPosition() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);

        Cell createdCell = grid.assignCellAtPositionTo(0, 1, NEW_OWNER);

        Cell expectedCell = new Cell(0, 1, NEW_OWNER);
        assertEquals(expectedCell, createdCell);

        Cell otherCellInRow = new Cell(0, 0, STARTING_OWNER);
        assertEquals(asList(otherCellInRow, expectedCell), grid.getFirstRow());
    }

    private static CellOwnerId cellOwnerIdWithToStringName(String toStringValue) {
        return new CellOwnerId() {
            @Override
            public String toString() {
                return toStringValue;
            }
        };
    }
}