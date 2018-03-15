package com.schrismillar.connect4.game;

import java.util.List;
import java.util.stream.Collectors;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.state.ActiveGameState;
import com.schrismillar.connect4.game.state.GameState;
import com.schrismillar.connect4.game.state.TieGameState;
import com.schrismillar.connect4.game.state.WinGameState;
import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.GridData;

public class Game {
    private final Player nextPlayer;
    private final Board board;
    private final Player currentPlayer;
    private final GameState currentGameState;

    public Game(Player currentPlayer, Player nextPlayer, Board board, GameState currentGameState) {
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
        this.board = board;
        this.currentGameState = currentGameState;
    }

    public GridData getGridData() {
        return board.getGridData();
    }

    public boolean isActive() {
        return currentGameState.isActive();
    }

    public boolean isOver() {
        return !currentGameState.isActive();
    }

    public Game takeTurnForCurrentPlayer() {
        //Step 1: decide play
        int columnChoice = promptForPlayerColumnChoice(currentPlayer, board.availableColumnIds());

        //Step 2: make play
        Cell justPlayedCell = board.dropIntoColumn(columnChoice, currentPlayer.getPlayerId());

        //Step 3: determine new GameState (was win or tie?)
        //TODO
        //return gridData and

        //Step 4: allow other player to observe play that just happened
        //otherPlayer().observePlay(justPlayedCell)

        return new Game(nextPlayer, currentPlayer, board, determineNewState(justPlayedCell));
    }

    private GameState determineNewState(Cell justPlayedCell) {
        if (isWin(justPlayedCell)) {
            return new WinGameState(currentPlayer);
        };
        if (board.availableColumnIds().isEmpty()) {
            return new TieGameState();
        }
        return new ActiveGameState();
    }

    private boolean isWin(Cell justPlayedCell) {
        return  board.hasHorizontalNeighborsBelongingToSameOwnerAs(3, justPlayedCell) ||
                board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, justPlayedCell) ||
                board.hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell) ||
                board.hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell);
    }

    private int promptForPlayerColumnChoice(Player currentPlayer, List<Integer> availableColumns) {
        List<Integer> baseOneAvailableColumns = availableColumns.stream().map(integer -> integer + 1).collect(Collectors.toList());
        return currentPlayer.decideMove(baseOneAvailableColumns) - 1;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}
