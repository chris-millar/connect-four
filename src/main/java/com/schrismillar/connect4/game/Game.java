package com.schrismillar.connect4.game;

import java.util.List;
import java.util.stream.Collectors;

import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.util.ConsolePrinter;

public class Game {
    private final Player playerOne;
    private final Player playerTwo;
    private final ConnectFourBoard connectFourBoard;
    private final ConsolePrinter consolePrinter;
    private final GridDataDisplayer gridDataDisplayer;

    public Game(Player playerOne, Player playerTwo, ConnectFourBoard connectFourBoard, ConsolePrinter consolePrinter, GridDataDisplayer gridDataDisplayer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.connectFourBoard = connectFourBoard;
        this.consolePrinter = consolePrinter;
        this.gridDataDisplayer = gridDataDisplayer;
    }

    public void start() {
        consolePrinter.println("The game is starting. Here is the Connect Four board.");
        gridDataDisplayer.display(connectFourBoard.getGridData());
    }

    public boolean isStillGoing() {
        return !isOver();
    }

    public boolean isOver() {
        return connectFourBoard.availableColumnIds().isEmpty();
    }

    public Player takeTurn(Player currentPlayer, int columnIdBase) {
        int columnChoice = promptForPlayerColumnChoice(currentPlayer, connectFourBoard.availableColumnIds(), columnIdBase);
        Cell cell = connectFourBoard.dropIntoColumn(columnChoice, currentPlayer.getPlayerId());
        gridDataDisplayer.display(connectFourBoard.getGridData());
        return currentPlayer == playerOne ? playerTwo : playerOne;
    }

    private int promptForPlayerColumnChoice(Player currentPlayer, List<Integer> availableColumns, int columnIdBase) {
        List<Integer> baseOneAvailableColumns = availableColumns.stream().map(integer -> integer + columnIdBase).collect(Collectors.toList());
        return currentPlayer.decideMove(baseOneAvailableColumns) - columnIdBase;
    }
}
