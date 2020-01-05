package com.schrismillar.connect4.game.player.determiners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schrismillar.connect4.game.player.PlayerNameValidator;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

@Component
public class PlayerNameDeterminer {
    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;
    private final PlayerNameValidator playerNameValidator;

    @Autowired
    public PlayerNameDeterminer(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner,
                                PlayerNameValidator playerNameValidator) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
        this.playerNameValidator = playerNameValidator;
    }

    public String determinePlayerNameFor(PlayerId playerId) {
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
