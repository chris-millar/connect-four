package com.schrismillar.connect4.game.player.determiners;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerTypeDeterminer {
    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;
    private final PlayerFactory playerFactory;

    public PlayerTypeDeterminer(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner, PlayerFactory playerFactory) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
        this.playerFactory = playerFactory;
    }

    public Player determinePlayerType(PlayerId playerId, String name) {
        consolePrinter.println("Choose a type of player for " + playerId + ". <human>/<h> or <ai>");
        String playerType = consoleScanner.next();
        if ("human".equalsIgnoreCase(playerType) || "h".equalsIgnoreCase(playerType)) {
            return playerFactory.createHumanPlayerWith(playerId, name);
        } else if ("ai".equalsIgnoreCase(playerType)) {
            return playerFactory.createRandomAiPlayerWith(playerId, name);
        } else {
            consolePrinter.println("INVALID INPUT: Player type must be either <human>/<h> or <ai>.");
            return determinePlayerType(playerId, name);
        }
    }
}
