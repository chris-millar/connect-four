package com.schrismillar.connect4;

import com.schrismillar.connect4.game.ConnectFourBoard;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.*;

public class DisplaySomeGrid {
    private static final DisplayCell EMPTY_DISPLAY_CELL = new EmptyDisplayCell();
    private static final DisplayCell RED_DISPLAY_CELL = new RedDisplayCell();
    private static final DisplayCell YELLOW_DISPLAY_CELL = new YellowDisplayCell();

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

        System.out.println(new DisplayGridImpl(connectFourBoard.getGridData(), mapper).displayValue());
        System.out.println("\n");

        connectFourBoard.dropIntoColumn(2, PlayerId.PLAYER_ONE);
        System.out.println(new DisplayGridImpl(connectFourBoard.getGridData(), mapper).displayValue());
        System.out.println("\n");

        connectFourBoard.dropIntoColumn(4, PlayerId.PLAYER_TWO);
        System.out.println(new DisplayGridImpl(connectFourBoard.getGridData(), mapper).displayValue());
        System.out.println("\n");
    }

}
