package com.schrismillar.connect4;

import com.schrismillar.connect4.game.*;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.*;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class Application {
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
    private static final int BASE_1_COLUMN_IDS = 1;

    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }

    private void start() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        ConsoleScanner consoleScanner = new ConsoleScanner();
        while (true) {
            if (shouldPlayNewGame(consolePrinter, consoleScanner)) {
                playNewGame(consolePrinter, consoleScanner);
            } else {
                return;
            }
        }
    }

    private void playNewGame(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        Player playerOne = PlayerFactory.instance().createHumanPlayerWith(PlayerId.PLAYER_ONE);
        Player playerTwo = PlayerFactory.instance().createHumanPlayerWith(PlayerId.PLAYER_TWO);
        ConnectFourBoard connectFourBoard = new ConnectFourBoard();
        GridDataDisplayer consoleGridDataPrinter = gridData -> {
            consolePrinter.println(new DisplayGridImpl(gridData, MAPPER, BASE_1_COLUMN_IDS).displayValue());
            consolePrinter.printBlankLine();
        };
        Game game = new Game(playerOne, playerTwo, connectFourBoard, consolePrinter, consoleGridDataPrinter);
        game.start();

        Player currentPlayer = playerOne;
        while(game.isStillGoing()) {
            currentPlayer = game.takeTurn(currentPlayer, BASE_1_COLUMN_IDS);

            if (game.isOver()) {
                consolePrinter.println("I have no idea who won, but this game is over");
                return;
            }
        }
    }

    private boolean shouldPlayNewGame(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        consolePrinter.println("Would you like to play a new game? <y/n>");
        String next = consoleScanner.next();
        if ("y".equalsIgnoreCase(next)) {
            return true;
        } else if ("n".equalsIgnoreCase(next)) {
            return false;
        } else {
            consolePrinter.println("INVALID INPUT: You must answer either y or n");
            return shouldPlayNewGame(consolePrinter, consoleScanner);
        }
    }
}
