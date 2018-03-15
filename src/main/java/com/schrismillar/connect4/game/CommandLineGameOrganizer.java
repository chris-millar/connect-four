package com.schrismillar.connect4.game;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.DisplayGridPrinter;
import com.schrismillar.connect4.util.ConsolePrinter;

public class CommandLineGameOrganizer {
    private final ConsolePrinter consolePrinter;
    private final GameFactory gameFactory;
    private final PlayerFactory playerFactory;
    private final DisplayGridPrinter displayGridPrinter;

    public CommandLineGameOrganizer(ConsolePrinter consolePrinter, GameFactory gameFactory, PlayerFactory playerFactory,
                                    DisplayGridPrinter displayGridPrinter) {
        this.consolePrinter = consolePrinter;
        this.gameFactory = gameFactory;
        this.playerFactory = playerFactory;
        this.displayGridPrinter = displayGridPrinter;
    }

    public void playNewGame() {
        Player playerOne = decidePlayer(PlayerId.PLAYER_ONE);
        Player playerTwo = decidePlayer(PlayerId.PLAYER_TWO);
        Game game = beginGameWith(playerOne, playerTwo);

        while(game.getCurrentGameState().isActive()) {
            game = game.takeTurnForCurrentPlayer();
            printGridData(game.getGridData());
        }

        consolePrinter.println(game.getCurrentGameState().message());
    }

    private Game beginGameWith(Player playerOne, Player playerTwo) {
        Game game = gameFactory.createGameWith(playerOne, playerTwo);
        GridData startGridData = game.getGridData();
        consolePrinter.println("The game is starting. Here is the Connect Four board.");
        printGridData(startGridData);
        return game;
    }

    private void printGridData(GridData gridData) {
        displayGridPrinter.printGrid(gridData);
        consolePrinter.printBlankLine();
    }

    private Player decidePlayer(PlayerId playerId) {
        return playerFactory.createHumanPlayerWith(playerId);
    }
}
