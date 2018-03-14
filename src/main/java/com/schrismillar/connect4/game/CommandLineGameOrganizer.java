package com.schrismillar.connect4.game;

import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.*;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class CommandLineGameOrganizer {
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
    private final ConsoleScanner consoleScanner;

    public CommandLineGameOrganizer(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
    }

    public void playNewGame() {
        Player playerOne = decidePlayer(PlayerId.PLAYER_ONE);
        Player playerTwo = decidePlayer(PlayerId.PLAYER_TWO);
        Game game = beginGameWith(playerOne, playerTwo);

        while(game.getCurrentGameState().isActive()) {
            GridData gridData = game.takeTurnForCurrentPlayer();
            printGridData(gridData);

            if (!game.getCurrentGameState().isActive()) {
                consolePrinter.println("I have no idea who won, but this game is over");
                return;
            }
        }
    }

    private Game beginGameWith(Player playerOne, Player playerTwo) {
        ConnectFourBoard connectFourBoard = new ConnectFourBoard();
        Game game = new Game(playerOne, playerTwo, connectFourBoard);
        GridData startGridData = game.getGridData();
        consolePrinter.println("The game is starting. Here is the Connect Four board.");
        printGridData(startGridData);
        return game;
    }

    private void printGridData(GridData gridData) {
        consolePrinter.println(new DisplayGridImpl(gridData, MAPPER).displayValue());
        consolePrinter.printBlankLine();
    }

    private Player decidePlayer(PlayerId playerId) {
        return PlayerFactory.instance().createHumanPlayerWith(playerId);
    }
}
