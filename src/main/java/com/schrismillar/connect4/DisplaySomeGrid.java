package com.schrismillar.connect4;

import com.schrismillar.connect4.game.ConnectFourBoard;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.*;

public class DisplaySomeGrid {
    private static final DisplayCell EMPTY_DISPLAY_CELL = new EmptyDisplayCell();
    private static final DisplayCell RED_DISPLAY_CELL = new RedDisplayCell();
    private static final DisplayCell YELLOW_DISPLAY_CELL = new YellowDisplayCell();
    private static final int BASE_0_COLUMN_IDS = 0;

    public static void main(String[] args) {
        PlayerId startingOwner = PlayerId.NONE;
        ConnectFourBoard connectFourBoard = new ConnectFourBoard();

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

        printBoard(connectFourBoard, mapper);

        connectFourBoard.dropIntoColumn(2, PlayerId.PLAYER_ONE);
        printBoard(connectFourBoard, mapper);

        connectFourBoard.dropIntoColumn(4, PlayerId.PLAYER_TWO);
        printBoard(connectFourBoard, mapper);
    }

    private static void printBoard(ConnectFourBoard connectFourBoard, CellOwnerToDisplayCellMapper mapper) {
        System.out.println(new DisplayGridImpl(connectFourBoard.getGridData(), mapper, BASE_0_COLUMN_IDS).displayValue());
        System.out.println("\n");
    }

}
