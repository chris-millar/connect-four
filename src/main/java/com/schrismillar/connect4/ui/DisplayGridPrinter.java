package com.schrismillar.connect4.ui;

import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;

public class DisplayGridPrinter {
    private static final DisplayCell EMPTY_DISPLAY_CELL = new EmptyDisplayCell();
    private static final DisplayCell RED_DISPLAY_CELL = new RedDisplayCell();
    private static final DisplayCell YELLOW_DISPLAY_CELL = new YellowDisplayCell();
    private static final CellOwnerToDisplayCellMapper MAPPER = owner -> {
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

    private final ConsolePrinter consolePrinter;

    public DisplayGridPrinter(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void printGrid(GridData gridData) {
        consolePrinter.println(new DisplayGridImpl(gridData, MAPPER).displayValue());
    }
}
