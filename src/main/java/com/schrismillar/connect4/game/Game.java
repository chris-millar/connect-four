package com.schrismillar.connect4.game;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.state.ActiveGameState;
import com.schrismillar.connect4.game.state.GameState;
import com.schrismillar.connect4.game.state.TieGameState;
import com.schrismillar.connect4.game.state.WinGameState;
import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.GridData;

public class Game {
    private final Player currentPlayer;
    private final Player nextPlayer;
    private final Board board;
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

    public Game takeTurnForCurrentPlayer() {
        int columnChoice = promptForPlayerColumnChoice(currentPlayer, board.availableColumnIds());
        Cell justPlayedCell = board.dropIntoColumn(columnChoice, currentPlayer.getPlayerId());

        //TODO - add AI players and allow them to observe a play
        //otherPlayer().observePlay(justPlayedCell)

        return new Game(nextPlayer, currentPlayer, board, determineNewState(justPlayedCell));
    }

    private GameState determineNewState(Cell justPlayedCell) {
        if (isWin(justPlayedCell)) {
            return new WinGameState(currentPlayer);
        }
        if (noMoreUnownedCells()) {
            return new TieGameState();
        }
        return new ActiveGameState();
    }

    private boolean noMoreUnownedCells() {
        return board.availableColumnIds().isEmpty();
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

    @Override
    public String toString() {
        return "Game{" +
                "currentPlayer=" + currentPlayer +
                ", nextPlayer=" + nextPlayer +
                ", board=" + board +
                ", currentGameState=" + currentGameState +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Game) {
            Game that = (Game) obj;
            return Objects.equals(this.currentPlayer, that.currentPlayer) &&
                    Objects.equals(this.nextPlayer, that.nextPlayer) &&
                    Objects.equals(this.board, that.board) &&
                    Objects.equals(this.currentGameState, that.currentGameState);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPlayer, nextPlayer, board, currentGameState);
    }
}
