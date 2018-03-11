package com.schrismillar.connect4.model;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;

public class GridData {
    private static final int FIRST_ROW = 0;

    private final Cell[][] cells;

    public GridData(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GridData) {
            GridData that = (GridData) obj;

            return compareAllCellsIn(this, that);
        }
        return false;
    }

    private boolean compareAllCellsIn(GridData me, GridData that) {
        for (int row = 0; row < me.cells.length; row++) {
            for (int col = 0; col < me.cells[row].length; col++) {
                if (!me.cells[row][col].equals(that.cells[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cells);
    }

    @Override
    public String toString() {
        return "GridData{" + asString(cells) + '}';
    }

    private String asString(Cell[][] cells) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Cell[]> rows = asList(cells);
        rows.forEach(col -> {
            List<Cell> columnCells = asList(col);
            stringBuilder.append("[");
            stringBuilder.append(columnCells.stream().map(Cell::toString).reduce((s, s2) -> s + ", " + s2).orElse(""));
            stringBuilder.append("]");
        });
        return stringBuilder.toString();
    }

    public int height() {
        return cells.length;
    }

    public int width() {
        return cells[FIRST_ROW].length;
    }

    public Cell getCellAt(int row, int col) {
        return cells[row][col];
    }
}
