package com.schrismillar.connect4.game;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.Grid;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;

public class Board {
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;

    private final Grid connectFourGrid;

    public Board() {
        connectFourGrid = new Grid(HEIGHT, WIDTH, PlayerId.NONE);
    }

    Board(Grid grid) {
        connectFourGrid = grid;
    }

    public Cell dropIntoColumn(int col, PlayerId owner) {
        int row = getHighestCellWithNoOwnerToDropInto(col);
        return connectFourGrid.assignCellAtPositionTo(row, col, owner);
    }

    private int getHighestCellWithNoOwnerToDropInto(int col) {
        List<Cell> column = connectFourGrid.getColumn(col);
        int highestRowWithNoOwner = 0;
        for (int row = 0; row < column.size(); row++) {
            if (column.get(row).owner() != PlayerId.NONE) {
                return highestRowWithNoOwner;
            }
            highestRowWithNoOwner = row;
        }
        return highestRowWithNoOwner;
    }

    public List<Integer> availableColumnIds() {
        return connectFourGrid.getFirstRow().stream().
                filter(cell -> cell.owner() == PlayerId.NONE).
                map(Cell::getColumn).
                collect(toList());
    }

    public GridData getGridData() {
        return connectFourGrid.getGridData();
    }

    public boolean hasHorizontalNeighborsBelongingTo(int numberOfNeighbors, Cell cell) {
        return false;
    }

    public boolean hasVerticalNeighborsBelowBelongingTo(int numberOfNeighbors, Cell cell) {
        List<Cell> column = connectFourGrid.getColumn(cell.getColumn());
        boolean cellNotToCloseToEndOfColumn = cell.getRow() + numberOfNeighbors < column.size();
        return cellNotToCloseToEndOfColumn && doAllCellsFromNextToNumberCellsAwayShareSameOwner(numberOfNeighbors, cell, column);
    }

    private boolean doAllCellsFromNextToNumberCellsAwayShareSameOwner(int numberOfNeighbors, Cell cell, List<Cell> column) {
        return column.
                subList(cell.getRow() + 1, cell.getRow() + numberOfNeighbors + 1).
                stream().
                allMatch(c -> c.owner() == cell.owner());
    }

    public boolean hasPositiveDiagonalNeighborsBelongingTo(int numberOfNeighbors, Cell cell) {
        return false;
    }

    public boolean hasNegativeDiagonalNeighborsBelongingTo(int numberOfNeighbors, Cell cell) {
        return false;
    }
}
