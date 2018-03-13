package com.schrismillar.connect4.game;

import java.util.List;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class HumanPlayer implements Player {
    private final PlayerId playerId;
    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;

    HumanPlayer(PlayerId playerId, ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        this.playerId = playerId;
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
    }

    @Override
    public PlayerId getPlayerId() {
        return playerId;
    }

    @Override
    public int decideMove(List<Integer> availableColumns) {
        consolePrinter.println(playerId + " it's your turn. Of the available columns " + availableColumns +
                " which would you like to play in?");
        try {
            int col = consoleScanner.nextInt();
            if (!availableColumns.contains(col)) {
                return handleInvalidInput(availableColumns);
            }
            return col;
        } catch (Exception e) {
            return handleInvalidInput(availableColumns);
        }
    }

    private int handleInvalidInput(List<Integer> availableColumns) {
        consolePrinter.println("INVALID INPUT: You must select one of the column ids " + availableColumns);
        return decideMove(availableColumns);
    }
}
