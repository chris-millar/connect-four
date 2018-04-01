package com.schrismillar.connect4.game.player.determiners;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.game.player.PlayerNameValidator;
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
        String name = determinePlayerNameFor(playerId);
        return playerFactory.createHumanPlayerWith(playerId, name);
    }

    private String determinePlayerNameFor(PlayerId playerId) {
        consolePrinter.println("Please provide a name for " + playerId + ":");

        String name = consoleScanner.nextLine();
        if (playerNameValidator.isValid(name)) {
            return name;
        } else {
            consolePrinter.println("INVALID INPUT: Player name must be alphaNumeric characters only.");
            return determinePlayerNameFor(playerId);
        }
    }
}
