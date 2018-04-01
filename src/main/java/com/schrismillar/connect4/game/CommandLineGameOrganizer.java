package com.schrismillar.connect4.game;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.player.PlayerDeterminer;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.DisplayGridPrinter;
import com.schrismillar.connect4.util.ConsolePrinter;

public class CommandLineGameOrganizer {
    private final ConsolePrinter consolePrinter;
    private final GameFactory gameFactory;
    private final DisplayGridPrinter displayGridPrinter;
    private final PlayerDeterminer playerDeterminer;

    public CommandLineGameOrganizer(ConsolePrinter consolePrinter, GameFactory gameFactory,
                                    DisplayGridPrinter displayGridPrinter, PlayerDeterminer playerDeterminer) {
        this.consolePrinter = consolePrinter;
        this.gameFactory = gameFactory;
        this.displayGridPrinter = displayGridPrinter;
        this.playerDeterminer = playerDeterminer;
    }

    public Game setupNewGame() {
        Player playerOne = playerDeterminer.determinePlayerWithId(PlayerId.PLAYER_ONE);
        Player playerTwo = playerDeterminer.determinePlayerWithId(PlayerId.PLAYER_TWO);
        return beginGameWith(playerOne, playerTwo);
    }


    public void playGame(Game game) {
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
}
