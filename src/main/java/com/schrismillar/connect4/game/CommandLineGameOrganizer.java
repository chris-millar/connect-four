package com.schrismillar.connect4.game;

import java.util.Optional;

import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.*;
import com.schrismillar.connect4.util.ConsolePrinter;

public class CommandLineGameOrganizer {
    private static final DisplayCell EMPTY_DISPLAY_CELL = new EmptyDisplayCell();
    private static final DisplayCell RED_DISPLAY_CELL = new RedDisplayCell();
    private static final DisplayCell YELLOW_DISPLAY_CELL = new YellowDisplayCell();
    private static final CellOwnerToDisplayCellMapper MAPPER = owner -> {
        if (owner instanceof PlayerId) {
            if (owner == PlayerId.PLAYER_ONE) {
                return RED_DISPLAY_CELL;
            } else if (owner == PlayerId.PLAYER_TWO) {
                return YELLOW_DISPLAY_CELL;
            } else {
                return EMPTY_DISPLAY_CELL;
            }
        } else {
            return EMPTY_DISPLAY_CELL;
        }
    };

    private final ConsolePrinter consolePrinter;

    public CommandLineGameOrganizer(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void playNewGame() {
        Player playerOne = decidePlayer(PlayerId.PLAYER_ONE);
        Player playerTwo = decidePlayer(PlayerId.PLAYER_TWO);
        Game game = beginGameWith(playerOne, playerTwo);

        while(game.getCurrentGameState().isActive()) {
            game = game.takeTurnForCurrentPlayer();
            printGridData(game.getGridData());

        }
        Optional<Player> winner = game.getCurrentGameState().winner();
        String gameOverMessage = winner.map(player -> "The Winner is " + player.getPlayerId() + "!").
                orElse("No winner today, this is a tie game.");
        consolePrinter.println(gameOverMessage);
    }

    private Game beginGameWith(Player playerOne, Player playerTwo) {
        Board board = new Board();
        Game game = new Game(playerOne, playerTwo, board, new ActiveGameState());
        GridData startGridData = game.getGridData();
        consolePrinter.println("The game is starting. Here is the Connect Four board.");
        printGridData(startGridData);
        return game;
    }

    private void printGridData(GridData gridData) {
        consolePrinter.println(new DisplayGridImpl(gridData, MAPPER).displayValue());
        consolePrinter.printBlankLine();
    }

    private Player decidePlayer(PlayerId playerId) {
        return PlayerFactory.instance().createHumanPlayerWith(playerId);
    }
}
