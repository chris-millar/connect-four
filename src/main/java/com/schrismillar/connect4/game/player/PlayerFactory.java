package com.schrismillar.connect4.game.player;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerFactory {
    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;

    public PlayerFactory(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
    }

    public HumanPlayer createHumanPlayerWith(PlayerId playerId) {
        return new HumanPlayer(playerId, consolePrinter, consoleScanner, playerId.name());
    }

    public HumanPlayer createHumanPlayerWith(PlayerId playerId, String name) {
        return new HumanPlayer(playerId, consolePrinter, consoleScanner, name);
    }
}
