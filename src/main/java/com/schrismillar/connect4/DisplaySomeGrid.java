package com.schrismillar.connect4;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.schrismillar.connect4.ui.*;

public class DisplaySomeGrid {
    private static final DisplayCell EMPTY_DISPLAY_CELL = new EmptyDisplayCell();
    private static final DisplayCell RED_DISPLAY_CELL = new RedDisplayCell();
    private static final DisplayCell YELLOW_DISPLAY_CELL = new YellowDisplayCell();

    public static void main(String[] args) {
        List<List<DisplayCell>> cells = new ArrayList<>();
        cells.add(asList(EMPTY_DISPLAY_CELL, YELLOW_DISPLAY_CELL, EMPTY_DISPLAY_CELL));
        cells.add(asList(RED_DISPLAY_CELL, RED_DISPLAY_CELL, YELLOW_DISPLAY_CELL));
        cells.add(asList(YELLOW_DISPLAY_CELL, RED_DISPLAY_CELL, YELLOW_DISPLAY_CELL));
        DisplayGrid displayGrid = new DisplayGrid(cells);
        System.out.print(displayGrid.displayValue());
    }
}
