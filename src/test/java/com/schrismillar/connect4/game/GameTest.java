package com.schrismillar.connect4.game;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.state.ActiveGameState;
import com.schrismillar.connect4.game.state.GameState;
import com.schrismillar.connect4.game.state.TieGameState;
import com.schrismillar.connect4.game.state.WinGameState;
import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;

public class GameTest {

    @Mock private Player currentPlayer;
    @Mock private Player nextPlayer;
    @Mock private Board board;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getGridDataReturnsBoardsGridData() {
        GridData boardsGridData = mock(GridData.class);
        when(board.getGridData()).thenReturn(boardsGridData);
        Game game = new Game(null, null, board, null);

        GridData gridData = game.getGridData();

        assertEquals(boardsGridData, gridData);
    }

    @Test
    public void getCurrentStateReturnsStateGameWasConstructedWith() {
        GameState currentGameState = mock(GameState.class);
        Game game = new Game(null, null, null, currentGameState);

        assertEquals(currentGameState, game.getCurrentGameState());
    }

    @Test
    public void takeTurnForCurrentPlayerReturnsNewGameWithCurrentAndNextPlayerSwappedSameBoardAndActiveGameStateIfNoGameOverConditionsMet() {
        when(currentPlayer.decideMove(asList(1, 2, 3))).thenReturn(2);
        when(board.availableColumnIds()).thenReturn(asList(0, 1, 2));
        PlayerId currentPlayerId = PlayerId.PLAYER_ONE;
        when(currentPlayer.getPlayerId()).thenReturn(currentPlayerId);
        Cell justPlayedCell = mock(Cell.class);
        when(board.dropIntoColumn(1, currentPlayerId)).thenReturn(justPlayedCell);
        when(board.hasHorizontalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        Game game = new Game(currentPlayer, nextPlayer, board, null);

        Game newGame = game.takeTurnForCurrentPlayer();

        assertEquals(new Game(nextPlayer, currentPlayer, board, new ActiveGameState()), newGame);
    }

    @Test
    public void takeTurnForCurrentPlayerReturnsNewGameWithTieGameStateIfNoMoreAvailableColumnIds() {
        when(currentPlayer.decideMove(singletonList(1))).thenReturn(1);
        when(board.availableColumnIds()).thenReturn(singletonList(0)).thenReturn(emptyList());
        PlayerId currentPlayerId = PlayerId.PLAYER_ONE;
        when(currentPlayer.getPlayerId()).thenReturn(currentPlayerId);
        Cell justPlayedCell = mock(Cell.class);
        when(board.dropIntoColumn(0, currentPlayerId)).thenReturn(justPlayedCell);
        when(board.hasHorizontalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        Game game = new Game(currentPlayer, nextPlayer, board, null);

        Game newGame = game.takeTurnForCurrentPlayer();

        assertEquals(new Game(nextPlayer, currentPlayer, board, new TieGameState()), newGame);
    }

    @Test
    public void takeTurnForCurrentPlayerReturnsNewGameWithWinGameStateIfHorizontalNeighborsReturnsTrue() {
        when(currentPlayer.decideMove(asList(1, 2, 3))).thenReturn(2);
        when(board.availableColumnIds()).thenReturn(asList(0, 1, 2));
        PlayerId currentPlayerId = PlayerId.PLAYER_ONE;
        when(currentPlayer.getPlayerId()).thenReturn(currentPlayerId);
        Cell justPlayedCell = mock(Cell.class);
        when(board.dropIntoColumn(1, currentPlayerId)).thenReturn(justPlayedCell);
        when(board.hasHorizontalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(true);
        when(board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        Game game = new Game(currentPlayer, nextPlayer, board, null);

        Game newGame = game.takeTurnForCurrentPlayer();

        assertEquals(new Game(nextPlayer, currentPlayer, board, new WinGameState(currentPlayer)), newGame);
    }

    @Test
    public void takeTurnForCurrentPlayerReturnsNewGameWithWinGameStateIfVerticalNeighborsReturnsTrue() {
        when(currentPlayer.decideMove(asList(1, 2, 3))).thenReturn(2);
        when(board.availableColumnIds()).thenReturn(asList(0, 1, 2));
        PlayerId currentPlayerId = PlayerId.PLAYER_ONE;
        when(currentPlayer.getPlayerId()).thenReturn(currentPlayerId);
        Cell justPlayedCell = mock(Cell.class);
        when(board.dropIntoColumn(1, currentPlayerId)).thenReturn(justPlayedCell);
        when(board.hasHorizontalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(true);
        when(board.hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        Game game = new Game(currentPlayer, nextPlayer, board, null);

        Game newGame = game.takeTurnForCurrentPlayer();

        assertEquals(new Game(nextPlayer, currentPlayer, board, new WinGameState(currentPlayer)), newGame);
    }

    @Test
    public void takeTurnForCurrentPlayerReturnsNewGameWithWinGameStateIfNegativeDiagonalNeighborsReturnsTrue() {
        when(currentPlayer.decideMove(asList(1, 2, 3))).thenReturn(2);
        when(board.availableColumnIds()).thenReturn(asList(0, 1, 2));
        PlayerId currentPlayerId = PlayerId.PLAYER_ONE;
        when(currentPlayer.getPlayerId()).thenReturn(currentPlayerId);
        Cell justPlayedCell = mock(Cell.class);
        when(board.dropIntoColumn(1, currentPlayerId)).thenReturn(justPlayedCell);
        when(board.hasHorizontalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(true);
        when(board.hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        Game game = new Game(currentPlayer, nextPlayer, board, null);

        Game newGame = game.takeTurnForCurrentPlayer();

        assertEquals(new Game(nextPlayer, currentPlayer, board, new WinGameState(currentPlayer)), newGame);
    }

    @Test
    public void takeTurnForCurrentPlayerReturnsNewGameWithWinGameStateIfPositiveDiagonalNeighborsReturnsTrue() {
        when(currentPlayer.decideMove(asList(1, 2, 3))).thenReturn(2);
        when(board.availableColumnIds()).thenReturn(asList(0, 1, 2));
        PlayerId currentPlayerId = PlayerId.PLAYER_ONE;
        when(currentPlayer.getPlayerId()).thenReturn(currentPlayerId);
        Cell justPlayedCell = mock(Cell.class);
        when(board.dropIntoColumn(1, currentPlayerId)).thenReturn(justPlayedCell);
        when(board.hasHorizontalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasVerticalNeighborsBelowBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasNegativeDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(false);
        when(board.hasPositiveDiagonalNeighborsBelongingToSameOwnerAs(3, justPlayedCell)).thenReturn(true);
        Game game = new Game(currentPlayer, nextPlayer, board, null);

        Game newGame = game.takeTurnForCurrentPlayer();

        assertEquals(new Game(nextPlayer, currentPlayer, board, new WinGameState(currentPlayer)), newGame);
    }

    @Test
    public void equalsAndHashCode() {
        GameState currentGameState = mock(GameState.class);
        Player otherPlayer = mock(Player.class);
        Board otherBoard = mock(Board.class);
        GameState otherGameState = mock(GameState.class);
        Game game = new Game(currentPlayer, nextPlayer, board, currentGameState);
        assertEquals(new Game(currentPlayer, nextPlayer, board, currentGameState), game);
        assertNotEquals(new Game(otherPlayer, nextPlayer, board, currentGameState), game);
        assertNotEquals(new Game(currentPlayer, otherPlayer, board, currentGameState), game);
        assertNotEquals(new Game(currentPlayer, nextPlayer, otherBoard, currentGameState), game);
        assertNotEquals(new Game(currentPlayer, nextPlayer, otherBoard, otherGameState), game);
        assertNotEquals("not a game", game);
    }
}