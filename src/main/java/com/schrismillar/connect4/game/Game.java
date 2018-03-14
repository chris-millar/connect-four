package com.schrismillar.connect4.game;

import java.util.List;
import java.util.stream.Collectors;

import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.GridData;

public class Game {
    private final Player playerOne;
    private final Player playerTwo;
    private final ConnectFourBoard connectFourBoard;

    private Player currentPlayer;
    private GameState currentGameState;

    public Game(Player playerOne, Player playerTwo, ConnectFourBoard connectFourBoard) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.connectFourBoard = connectFourBoard;
        currentPlayer = playerOne;
        currentGameState = new ActiveGameState();
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

    public GridData takeTurnForCurrentPlayer() {
        //Step 1: decide play
        int columnChoice = promptForPlayerColumnChoice(currentPlayer, connectFourBoard.availableColumnIds());

        //Step 2: make play
        Cell justPlayedCell = connectFourBoard.dropIntoColumn(columnChoice, currentPlayer.getPlayerId());

        //Step 3: determine new GameState (was win or tie?)
        //TODO
        //return gridData and
        currentGameState = determineNewState();

        //Step 4: allow other player to observe play that just happened
        //otherPlayer().observePlay(justPlayedCell)

        //Step 5: update current player
        currentPlayer = otherPlayer();

        //Step 6: return current grid data
        return connectFourBoard.getGridData();
    }

    private GameState determineNewState() {
        if (connectFourBoard.availableColumnIds().isEmpty()) {
            return new TieGameState();
        }
        return new ActiveGameState();
    }

    private Player otherPlayer() {
        return currentPlayer == playerOne ? playerTwo : playerOne;
    }

    private int promptForPlayerColumnChoice(Player currentPlayer, List<Integer> availableColumns) {
        List<Integer> baseOneAvailableColumns = availableColumns.stream().map(integer -> integer + 1).collect(Collectors.toList());
        return currentPlayer.decideMove(baseOneAvailableColumns) - 1;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}
