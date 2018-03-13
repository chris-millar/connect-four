package com.schrismillar.connect4;

import java.util.List;
import java.util.stream.Collectors;

import com.schrismillar.connect4.game.*;
import com.schrismillar.connect4.model.Cell;
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
            consolePrinter.println(new DisplayGridImpl(gridData, MAPPER, 1).displayValue());
            consolePrinter.printBlankLine();
        };
        Game game = new Game(playerOne, playerTwo, connectFourBoard, consolePrinter, consoleGridDataPrinter);
        game.start();

        Player currentPlayer = playerOne;
        while(game.isStillGoing()) {
            GridData turnGridData = turn(connectFourBoard, currentPlayer);
            consolePrinter.println(new DisplayGridImpl(turnGridData, MAPPER, 1).displayValue());
            consolePrinter.printBlankLine();
            currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;

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

    private GridData turn(ConnectFourBoard connectFourBoard, Player currentPlayer) {
        int columnChoice = promptForPlayerColumnChoice(currentPlayer, connectFourBoard.availableColumnIds());
        Cell cell = connectFourBoard.dropIntoColumn(columnChoice, currentPlayer.getPlayerId());
        return connectFourBoard.getGridData();
    }

    private int promptForPlayerColumnChoice(Player currentPlayer, List<Integer> availableColumns) {
        List<Integer> baseOneAvailableColumns = availableColumns.stream().map(integer -> integer + 1).collect(Collectors.toList());
        return currentPlayer.decideMove(baseOneAvailableColumns) - 1;
    }
}
