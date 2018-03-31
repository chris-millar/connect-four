package com.schrismillar.connect4.game.player;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerDeterminer {
    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;
    private final PlayerNameValidator playerNameValidator;
    private final PlayerFactory playerFactory;

    public PlayerDeterminer(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner,
                            PlayerNameValidator playerNameValidator, PlayerFactory playerFactory) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
        this.playerNameValidator = playerNameValidator;
        this.playerFactory = playerFactory;
    }

    public Player determinePlayerWithId(PlayerId playerId) {
        consolePrinter.println("Please provide a name for " + playerId + ":");

        String name = consoleScanner.nextLine();
        if (playerNameValidator.isValid(name)) {
            return playerFactory.createHumanPlayerWith(playerId, name);
        } else {
            consolePrinter.println("INVALID INPUT: Player name must be alphaNumeric characters only.");
            return determinePlayerWithId(playerId);
        }
    }
}
