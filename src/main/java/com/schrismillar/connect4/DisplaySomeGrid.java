package com.schrismillar.connect4;

import com.schrismillar.connect4.model.Grid;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.*;

public class DisplaySomeGrid {
    private static final DisplayCell EMPTY_DISPLAY_CELL = new EmptyDisplayCell();
    private static final DisplayCell RED_DISPLAY_CELL = new RedDisplayCell();
    private static final DisplayCell YELLOW_DISPLAY_CELL = new YellowDisplayCell();

    public static void main(String[] args) {
        PlayerId startingOwner = PlayerId.NONE;
        Grid connectFourGrid = new Grid(6, 7, startingOwner);

        CellOwnerToDisplayCellMapper mapper = owner -> {
            if (owner instanceof PlayerId) {
                if (owner == PlayerId.PLAYER_ONE) {
                    return RED_DISPLAY_CELL;
                } else if (owner == PlayerId.PLAYER_TWO) {
                    return YELLOW_DISPLAY_CELL;
                } else {
                    return EMPTY_DISPLAY_CELL;
                }
            } else {
                return EMPTY_DISPLAY_CELL;
            }
        };

        System.out.println(getDisplayGrid(connectFourGrid, mapper).displayValue());
        System.out.println("\n");

        connectFourGrid.assignCellAtPositionTo(3, 4, PlayerId.PLAYER_ONE);
        System.out.println(getDisplayGrid(connectFourGrid, mapper).displayValue());
        System.out.println("\n");

        connectFourGrid.assignCellAtPositionTo(1, 6, PlayerId.PLAYER_TWO);
        System.out.println(getDisplayGrid(connectFourGrid, mapper).displayValue());
        System.out.println("\n");
    }

    private static DisplayGrid getDisplayGrid(Grid connectFourGrid, CellOwnerToDisplayCellMapper mapper) {
        GridData gridData = connectFourGrid.getGridData();
        DisplayGrid displayGrid = new GridDataBasedDisplayGrid(gridData, mapper);
        return displayGrid;
    }
}
