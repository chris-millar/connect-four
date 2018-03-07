package com.schrismillar.connect4;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.schrismillar.connect4.ui.*;

public class DisplaySomeGrid {
    private static final Cell EMPTY_CELL = new EmptyCell();
    private static final Cell RED_CELL = new RedCell();
    private static final Cell YELLOW_CELL = new YellowCell();

    public static void main(String[] args) {
        List<List<Cell>> cells = new ArrayList<>();
        cells.add(asList(EMPTY_CELL,  YELLOW_CELL, EMPTY_CELL));
        cells.add(asList(RED_CELL,    RED_CELL,    YELLOW_CELL));
        cells.add(asList(YELLOW_CELL, RED_CELL,    YELLOW_CELL));
        Grid grid = new Grid(cells);
        System.out.print(grid.displayValue());
    }
}
