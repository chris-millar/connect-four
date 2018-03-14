package com.schrismillar.connect4.game;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static com.schrismillar.connect4.model.PlayerId.PLAYER_TWO;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.Grid;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;

public class BoardTest {

    @Test
    public void getAvailableColumnIdsReturnsAllSevenColumnIdsForNewGrid() {
        Board board = new Board();

        List<Integer> availableColumnIds = board.availableColumnIds();

        assertEquals(asList(0, 1, 2, 3, 4, 5, 6), availableColumnIds);
    }

    @Test
    public void getAvailableColumnIdsReturnsColumnIdsOfTopRowWhoseOwnerIsStillNone() {
        Grid grid = mock(Grid.class);
        Cell cell0 = new Cell(0, 0, PlayerId.NONE);
        Cell cell1 = new Cell(0, 1, PlayerId.NONE);
        Cell cell2 = new Cell(0, 2, PLAYER_ONE);
        Cell cell3 = new Cell(0, 3, PlayerId.NONE);
        Cell cell4 = new Cell(0, 4, PLAYER_ONE);
        Cell cell5 = new Cell(0, 5, PLAYER_ONE);
        Cell cell6 = new Cell(0, 6, PlayerId.NONE);
        when(grid.getFirstRow()).thenReturn(asList(cell0, cell1, cell2, cell3, cell4, cell5, cell6));
        Board board = new Board(grid);

        List<Integer> availableColumnIds = board.availableColumnIds();

        assertEquals(asList(0, 1, 3, 6), availableColumnIds);
    }

    @Test
    public void getGridDataCallsThroughToGrid() {
        Grid grid = mock(Grid.class);
        GridData gridData = mock(GridData.class);
        when(grid.getGridData()).thenReturn(gridData);
        Board board = new Board(grid);

        GridData actualGridData = board.getGridData();

        assertEquals(gridData, actualGridData);
    }

    @Test
    public void dropIntoColumnAssignsCellWithSpecifiedOwnerToHighestRowIndexInSpecifiedColumnThatDoesNotHaveAnOwner() {
        Grid grid = mock(Grid.class);
        Cell unOwnedCell1 = new Cell(0, 0, PlayerId.NONE);
        Cell unOwnedCell2 = new Cell(1, 0, PlayerId.NONE);
        Cell ownedCell1 = new Cell(2, 0, PLAYER_ONE);
        Cell ownedCell2 = new Cell(3, 0, PLAYER_ONE);
        when(grid.getColumn(0)).thenReturn(asList(unOwnedCell1, unOwnedCell2, ownedCell1, ownedCell2));
        when(grid.assignCellAtPositionTo(1, 0, PLAYER_ONE)).
                thenReturn(new Cell(1, 0, PLAYER_ONE));
        Board board = new Board(grid);

        Cell cell = board.dropIntoColumn(0, PLAYER_ONE);

        assertEquals(new Cell(1, 0, PLAYER_ONE), cell);
    }

    @Test
    public void dropIntoColumnAssignsCellWithSpecifiedOwnerToLastColumnIndexWhenColumnEmpty() {
        Grid grid = mock(Grid.class);
        Cell unOwnedCell1 = new Cell(0, 0, PlayerId.NONE);
        Cell unOwnedCell2 = new Cell(1, 0, PlayerId.NONE);
        Cell unOwnedCell3 = new Cell(2, 0, PlayerId.NONE);
        Cell unOwnedCell4 = new Cell(3, 0, PlayerId.NONE);
        when(grid.getColumn(0)).thenReturn(asList(unOwnedCell1, unOwnedCell2, unOwnedCell3, unOwnedCell4));
        when(grid.assignCellAtPositionTo(3, 0, PLAYER_ONE)).
                thenReturn(new Cell(3, 0, PLAYER_ONE));
        Board board = new Board(grid);

        Cell cell = board.dropIntoColumn(0, PLAYER_ONE);

        assertEquals(new Cell(3, 0, PLAYER_ONE), cell);
    }

    @Test
    public void hasVerticalNeighborsBelowReturnsTrueIfNumberOfSpecifiedCellsBelowSpecifiedShareSameOwnerAsSpecifiedCell() {
        Grid grid = mock(Grid.class);
        Cell playerOneCell0 = new Cell(0, 0, PLAYER_ONE);
        Cell playerOneCell1 = new Cell(1, 0, PLAYER_ONE);
        Cell playerOneCell2 = new Cell(2, 0, PLAYER_ONE);
        Cell playerOneCell3 = new Cell(3, 0, PLAYER_ONE);
        when(grid.getColumn(0)).thenReturn(asList(playerOneCell0, playerOneCell1, playerOneCell2, playerOneCell3));
        Board board = new Board(grid);

        boolean has3NeighborsBelow = board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, playerOneCell0);

        assertTrue("Expected it to be true because the cell specified has 3 consecutive neighbors below",
                has3NeighborsBelow);
    }

    @Test
    public void hasVerticalNeighborsBelowReturnsTrueEvenIfSpecifiedCellIsInMiddleOfColumn() {
        Grid grid = mock(Grid.class);
        Cell playerOneCell0 = new Cell(0, 0, PLAYER_TWO);
        Cell playerOneCell1 = new Cell(1, 0, PLAYER_TWO);
        Cell playerOneCell2 = new Cell(2, 0, PLAYER_ONE);
        Cell playerOneCell3 = new Cell(3, 0, PLAYER_ONE);
        Cell playerOneCell4 = new Cell(4, 0, PLAYER_ONE);
        Cell playerOneCell5 = new Cell(5, 0, PLAYER_ONE);
        Cell playerOneCell6 = new Cell(6, 0, PLAYER_TWO);
        List<Cell> column = asList(playerOneCell0, playerOneCell1, playerOneCell2, playerOneCell3, playerOneCell4,
                playerOneCell5, playerOneCell6);
        when(grid.getColumn(0)).thenReturn(column);
        Board board = new Board(grid);

        boolean has3NeighborsBelow = board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, playerOneCell2);

        assertTrue("Expected it to be true because the cell specified has 3 consecutive neighbors below",
                has3NeighborsBelow);
    }

    @Test
    public void hasVerticalNeighborsBelowReturnsFalseIfAnyOfSpecifiedNumberOfContiguousCellsBelowHaveDifferentOwner() {
        Grid grid = mock(Grid.class);
        Cell playerOneCell0 = new Cell(0, 0, PLAYER_TWO);
        Cell playerOneCell1 = new Cell(1, 0, PLAYER_TWO);
        Cell playerOneCell2 = new Cell(2, 0, PLAYER_ONE);
        Cell playerOneCell3 = new Cell(3, 0, PLAYER_ONE);
        Cell playerOneCell4 = new Cell(4, 0, PLAYER_TWO);
        Cell playerOneCell5 = new Cell(5, 0, PLAYER_ONE);
        Cell playerOneCell6 = new Cell(6, 0, PLAYER_TWO);
        List<Cell> column = asList(playerOneCell0, playerOneCell1, playerOneCell2, playerOneCell3, playerOneCell4,
                playerOneCell5, playerOneCell6);
        when(grid.getColumn(0)).thenReturn(column);
        Board board = new Board(grid);

        boolean has3NeighborsBelow = board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, playerOneCell2);

        assertFalse("Expected false because the cell specified has only 1 contiguous neighbor with same owner",
                has3NeighborsBelow);
    }

    @Test
    public void hasVerticalNeighborsBelowReturnsFalseIfSpecifiedCellIsDoesNotHaveAtLeastTheSpecifiedNumberOfNeighborsBelow() {
        Grid grid = mock(Grid.class);
        Cell playerOneCell0 = new Cell(0, 0, PLAYER_TWO);
        Cell playerOneCell1 = new Cell(1, 0, PLAYER_TWO);
        Cell playerOneCell2 = new Cell(2, 0, PLAYER_ONE);
        Cell playerOneCell3 = new Cell(3, 0, PLAYER_ONE);
        Cell playerOneCell4 = new Cell(4, 0, PLAYER_TWO);
        Cell playerOneCell5 = new Cell(5, 0, PLAYER_ONE);
        Cell playerOneCell6 = new Cell(6, 0, PLAYER_TWO);
        List<Cell> column = asList(playerOneCell0, playerOneCell1, playerOneCell2, playerOneCell3, playerOneCell4,
                playerOneCell5, playerOneCell6);
        when(grid.getColumn(0)).thenReturn(column);
        Board board = new Board(grid);

        boolean has3NeighborsBelow = board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, playerOneCell4);

        assertFalse("Expected false because specified cell has only 2 neighbors below but we asked if it had 3 with same owner",
                has3NeighborsBelow);
    }
}