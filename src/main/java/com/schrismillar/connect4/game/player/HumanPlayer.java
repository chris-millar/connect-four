package com.schrismillar.connect4.game.player;

import java.util.List;
import java.util.Objects;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class HumanPlayer implements Player {
    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;
    private final PlayerId playerId;
    private final String name;

    HumanPlayer(PlayerId playerId, ConsolePrinter consolePrinter, ConsoleScanner consoleScanner, String name) {
        this.playerId = playerId;
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
        this.name = name;
    }

    @Override
    public PlayerId getPlayerId() {
        return playerId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int decideMove(List<Integer> availableColumns) {
        consolePrinter.println(name + ", it's your turn. Of the available columns " + availableColumns +
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

    @Override
    public String toString() {
        return "HumanPlayer{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HumanPlayer) {
            HumanPlayer that = (HumanPlayer) obj;
            return playerId == that.playerId &&
                    Objects.equals(name, that.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, name);
    }
}
