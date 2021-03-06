package com.schrismillar.connect4.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    private static final int FIRST_ROW = 0;

    private final Cell[][] cells;

    public Grid(int height, int width, CellOwnerId startingOwner) {
        cells = gridPopulatedWithCellsWithOwner(height, width, startingOwner);
    }

    private Cell[][] gridPopulatedWithCellsWithOwner(int height, int width, CellOwnerId startingOwner) {
        Cell[][] cells = new Cell[height][width];

        for (int row = 0;  row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell(row, col, startingOwner);
            }
        }

        return cells;
    }

    private int height() {
        return cells.length;
    }

    private int width() {
        return cells[FIRST_ROW].length;
    }

    public GridData getGridData() {
        return new GridData(cloneCells());
    }

    private Cell[][] cloneCells() {
        return Arrays.stream(cells.clone()).
                map(Cell[]::clone).
                collect(Collectors.toList()).
                toArray(new Cell[height()][width()]);
    }

    public List<Cell> getFirstRow() {
        return getRow(FIRST_ROW);
    }

    public List<Cell> getRow(int row) {
        List<Cell> rowOfCells = new LinkedList<>();
        for (int col = 0; col < width(); col++) {
            rowOfCells.add(cells[row][col]);
        }
        return rowOfCells;
    }

    public List<Cell> getColumn(int col) {
        List<Cell> column = new LinkedList<>();
        for (int row = 0; row < height(); row++) {
            column.add(cells[row][col]);
        }
        return column;
    }

    public List<Cell> getPositiveDiagonalWith(Cell cell) {
        Cell bottomOfDiagonal = findBottomOfPositiveDiagonal(cell);
        return buildPositiveDiagonalAsListStartingFrom(bottomOfDiagonal);
    }

    private List<Cell> buildPositiveDiagonalAsListStartingFrom(Cell bottomOfDiagonal) {
        List<Cell> diagonal = new LinkedList<>();
        int row = bottomOfDiagonal.getRow();
        int col = bottomOfDiagonal.getColumn();
        while (col < width() && row >= 0) {
            diagonal.add(cells[row][col]);
            row--;
            col++;
        }
        return diagonal;
    }

    private Cell findBottomOfPositiveDiagonal(Cell cell) {
        int row = cell.getRow();
        int col = cell.getColumn();
        Cell bottomCell = cell;
        while (col >= 0 && row < height()) {
            bottomCell = cells[row++][col--];
        }
        return bottomCell;
    }

    public List<Cell> getNegativeDiagonalWith(Cell cell) {
        Cell topOfDiagonal = findTopOfNegativeDiagonal(cell);
        return buildNegativeDiagonalAsListStartingFrom(topOfDiagonal);
    }

    private List<Cell> buildNegativeDiagonalAsListStartingFrom(Cell topOfDiagonal) {
        List<Cell> diagonal = new LinkedList<>();
        int row = topOfDiagonal.getRow();
        int col = topOfDiagonal.getColumn();
        while (col < width() && row < height()) {
            diagonal.add(cells[row][col]);
            row++;
            col++;
        }
        return diagonal;
    }

    private Cell findTopOfNegativeDiagonal(Cell cell) {
        int row = cell.getRow();
        int col = cell.getColumn();
        Cell topCell = cell;
        while (col >= 0 && row >= 0) {
            topCell = cells[row--][col--];
        }
        return topCell;
    }

    public Cell assignCellAtPositionTo(int row, int col, CellOwnerId owner) {
        Cell newCell = new Cell(row, col, owner);
        cells[row][col] = newCell;
        return newCell;
    }
}
