package com.schrismillar.connect4.model;

import static java.util.Arrays.asList;

import java.util.LinkedList;
import java.util.List;

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


    public List<List<CellOwnerId>> cellOwners() {
        List<List<CellOwnerId>> rowsOfOwners = new LinkedList<>();
        for (int row = 0; row < height(); row++) {
            List<CellOwnerId> rowOfOwners = new LinkedList<>();
            for (int col = 0; col < width(); col++) {
                rowOfOwners.add(cells[row][col].owner());
            }
            rowsOfOwners.add(rowOfOwners);
        }
        return rowsOfOwners;
    }

    public List<Cell> getFirstRow() {
        return asList(cells[FIRST_ROW]);
    }

    public List<Cell> getColumn(int col) {
        List<Cell> column = new LinkedList<>();
        for (int row = 0; row < height(); row++) {
            column.add(cells[row][col]);
        }
        return column;
    }
}
