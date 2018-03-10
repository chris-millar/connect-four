package com.schrismillar.connect4.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Grid {
    private final Cell[][] cells;

    public Grid(int height, int width) {
        cells = gridPopulatedWithCellsWithNoOwner(height, width);
    }

    private Cell[][] gridPopulatedWithCellsWithNoOwner(int height, int width) {
        Cell[][] cells = new Cell[height][width];

        for (int row = 0;  row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell(row, col, PlayerId.NONE);
            }
        }

        return cells;
    }

    private int height() {
        return cells.length;
    }

    private int width() {
        return cells[0].length;
    }


    public List<List<PlayerId>> cellOwners() {
        List<List<PlayerId>> rowsOfOwners = new LinkedList<>();
        for (int row = 0; row < height(); row++) {
            List<PlayerId> rowOfOwners = new LinkedList<>();
            for (int col = 0; col < width(); col++) {
                rowOfOwners.add(cells[row][col].owner());
            }
            rowsOfOwners.add(rowOfOwners);
        }
        return rowsOfOwners;
    }
}
