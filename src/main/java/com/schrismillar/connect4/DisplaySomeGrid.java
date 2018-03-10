package com.schrismillar.connect4;

import java.util.LinkedList;
import java.util.List;

import com.schrismillar.connect4.model.Grid;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.*;

public class DisplaySomeGrid {
    private static final DisplayCell EMPTY_DISPLAY_CELL = new EmptyDisplayCell();
    private static final DisplayCell RED_DISPLAY_CELL = new RedDisplayCell();
    private static final DisplayCell YELLOW_DISPLAY_CELL = new YellowDisplayCell();

    public static void main(String[] args) {
        Grid connectFourGrid = new Grid(6, 7);
        List<List<PlayerId>> playerIds = connectFourGrid.cellOwners();
        List<List<DisplayCell>> displayCells = transform(playerIds);
        DisplayGrid displayGrid = new DisplayGrid(displayCells);
        System.out.print(displayGrid.displayValue());
    }

    private static List<List<DisplayCell>> transform(List<List<PlayerId>> playerIds) {
        List<List<DisplayCell>> displayCells = new LinkedList<>();
        playerIds.forEach(row -> {
            List<DisplayCell> rowCells = new LinkedList<>();
            row.forEach(playerId -> {
                rowCells.add(RED_DISPLAY_CELL);
            });
            displayCells.add(rowCells);
        });
        return displayCells;
    }
}
