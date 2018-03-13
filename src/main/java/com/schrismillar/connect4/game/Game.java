package com.schrismillar.connect4.game;

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
}
