package com.schrismillar.connect4.game;

import java.util.List;
import java.util.stream.Collectors;

import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;

public class Game {
    private final Player nextPlayer;
    private final ConnectFourBoard connectFourBoard;
    private final Player currentPlayer;
    private final GameState currentGameState;

    public Game(Player currentPlayer, Player nextPlayer, ConnectFourBoard connectFourBoard, GameState currentGameState) {
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
        this.connectFourBoard = connectFourBoard;
        this.currentGameState = currentGameState;
    }

    public GridData getGridData() {
        return connectFourBoard.getGridData();
    }

    public boolean isActive() {
        return currentGameState.isActive();
    }

    public boolean isOver() {
        return !currentGameState.isActive();
    }

    public Game takeTurnForCurrentPlayer() {
        //Step 1: decide play
        int columnChoice = promptForPlayerColumnChoice(currentPlayer, connectFourBoard.availableColumnIds());

        //Step 2: make play
        Cell justPlayedCell = connectFourBoard.dropIntoColumn(columnChoice, currentPlayer.getPlayerId());

        //Step 3: determine new GameState (was win or tie?)
        //TODO
        //return gridData and

        //Step 4: allow other player to observe play that just happened
        //otherPlayer().observePlay(justPlayedCell)

        return new Game(nextPlayer, currentPlayer, connectFourBoard, determineNewState(justPlayedCell));
    }

    private GameState determineNewState(Cell justPlayedCell) {
        if (isWin(justPlayedCell)) {
            return new WinGameState(currentPlayer);
        };
        if (connectFourBoard.availableColumnIds().isEmpty()) {
            return new TieGameState();
        }
        return new ActiveGameState();
    }

    private boolean isWin(Cell justPlayedCell) {
        return false;
    }

    private int promptForPlayerColumnChoice(Player currentPlayer, List<Integer> availableColumns) {
        List<Integer> baseOneAvailableColumns = availableColumns.stream().map(integer -> integer + 1).collect(Collectors.toList());
        return currentPlayer.decideMove(baseOneAvailableColumns) - 1;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}
