package com.schrismillar.connect4.game.player;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;
import com.schrismillar.connect4.util.RandomElementSelector;

public class PlayerFactory {
    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;
    private final RandomElementSelector randomElementSelector;

    public PlayerFactory(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner,
                         RandomElementSelector randomElementSelector) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
        this.randomElementSelector = randomElementSelector;
    }

    public HumanPlayer createHumanPlayerWith(PlayerId playerId, String name) {
        return new HumanPlayer(playerId, consolePrinter, consoleScanner, name);
    }

    public RandomAiPlayer createRandomAiPlayerWith(PlayerId playerId, String name) {
        return new RandomAiPlayer(playerId, name, consolePrinter, randomElementSelector);
    }
}
