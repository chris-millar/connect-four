package com.schrismillar.connect4.game;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.schrismillar.connect4.model.*;

public class Board {
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;
    private static final int RIGHT = 1;

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

    public boolean hasHorizontalNeighborsBelongingToSameOwnerAs(int numberOfNeighbors, Cell cell) {
        List<Cell> row = connectFourGrid.getRow(cell.getRow());
        int leftCount = consecutiveSameOwnersInNegativeDirection(row, cell.getColumn(), cell.owner());
        int rightCount = consecutiveSameOwnersInPositiveDirection(row, cell.getColumn(), cell.owner());
        return leftCount + rightCount >= numberOfNeighbors;
    }

    public boolean hasVerticalNeighborsBelowBelongingToSameOwnerAs(int numberOfNeighbors, Cell cell) {
        List<Cell> column = connectFourGrid.getColumn(cell.getColumn());
        int downCount = consecutiveSameOwnersInPositiveDirection(column, cell.getRow(), cell.owner());
        return downCount >= numberOfNeighbors;
    }

    public boolean hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(int numberOfNeighbors, Cell cell) {
        List<Cell> positiveDiagonal = connectFourGrid.getPositiveDiagonalWith(cell);
        int cellsIndexInDiagonal = positiveDiagonal.indexOf(cell);
        int upSlopeCount = consecutiveSameOwnersInPositiveDirection(positiveDiagonal, cellsIndexInDiagonal, cell.owner());
        int downSlopeCount = consecutiveSameOwnersInNegativeDirection(positiveDiagonal, cellsIndexInDiagonal, cell.owner());
        return upSlopeCount + downSlopeCount >= numberOfNeighbors;
    }

    public boolean hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(int numberOfNeighbors, Cell cell) {
        List<Cell> negativeDiagonal = connectFourGrid.getNegativeDiagonalWith(cell);
        int cellsIndexInDiagonal = negativeDiagonal.indexOf(cell);
        int upSlopeCount = consecutiveSameOwnersInNegativeDirection(negativeDiagonal, cellsIndexInDiagonal, cell.owner());
        int downSlopeCount = consecutiveSameOwnersInPositiveDirection(negativeDiagonal, cellsIndexInDiagonal, cell.owner());
        return upSlopeCount + downSlopeCount >= numberOfNeighbors;
    }

    private int consecutiveSameOwnersInNegativeDirection(List<Cell> list, int startIndex, CellOwnerId owner) {
        int currentIndex = startIndex - 1;
        int sameOwnerStreak = 0;
        while (currentIndex >= 0 && list.get(currentIndex).owner() == owner) {
            sameOwnerStreak++;
            currentIndex--;
        }
        return sameOwnerStreak;
    }

    private int consecutiveSameOwnersInPositiveDirection(List<Cell> list, int startIndex, CellOwnerId owner) {
        int currentIndex = startIndex + 1;
        int sameOwnerStreak = 0;
        while (currentIndex < list.size() && list.get(currentIndex).owner() == owner) {
            sameOwnerStreak++;
            currentIndex++;
        }
        return sameOwnerStreak;
    }
}
