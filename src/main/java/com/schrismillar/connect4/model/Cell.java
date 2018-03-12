package com.schrismillar.connect4.model;

import java.util.Objects;

public class Cell {

    private final int row;
    private final int col;
    private final CellOwnerId owner;

    public Cell(int row, int col, CellOwnerId owner) {
        this.row = row;
        this.col = col;
        this.owner = owner;
    }

    public CellOwnerId owner() {
        return owner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell that = (Cell) obj;
            return that.row == this.row &&
                    that.col == this.col &&
                    that.owner == this.owner;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, owner);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                ", owner=" + owner +
                '}';
    }

    public int getColumn() {
        return col;
    }
}
