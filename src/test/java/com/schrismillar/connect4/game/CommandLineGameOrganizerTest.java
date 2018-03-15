package com.schrismillar.connect4.game;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.HumanPlayer;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.game.state.GameState;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.ui.DisplayGridPrinter;
import com.schrismillar.connect4.util.ConsolePrinter;

public class CommandLineGameOrganizerTest {
    @Mock private ConsolePrinter consolePrinter;
    @Mock private GameFactory gameFactory;
    @Mock private PlayerFactory playerFactory;
    @Mock private HumanPlayer playerOne;
    @Mock private HumanPlayer playerTwo;
    @Mock private DisplayGridPrinter displayGridPrinter;

    private CommandLineGameOrganizer commandLineGameOrganizer;

    @Before
    public void setUp() {
        initMocks(this);
        commandLineGameOrganizer = new CommandLineGameOrganizer(consolePrinter, gameFactory, playerFactory,
                                                                displayGridPrinter);
    }

    @Test
    public void playNewGameDeterminesTwoPlayersAndStartsNewGameWithThosePlayersLoopsWhileGameIsActive() {
        when(playerFactory.createHumanPlayerWith(PlayerId.PLAYER_ONE)).thenReturn(playerOne);
        when(playerFactory.createHumanPlayerWith(PlayerId.PLAYER_TWO)).thenReturn(playerTwo);
        Game initialGame = mock(Game.class);
        GridData initialGridData = mock(GridData.class);
        when(initialGame.getGridData()).thenReturn(initialGridData);
        when(gameFactory.createGameWith(playerOne, playerTwo)).thenReturn(initialGame);
        GameState activeGameState = mock(GameState.class);
        when(activeGameState.isActive()).thenReturn(true);
        when(initialGame.getCurrentGameState()).thenReturn(activeGameState);
        Game firstTurnGame = mock(Game.class);
        GridData firstTurnGridData = mock(GridData.class);
        when(firstTurnGame.getGridData()).thenReturn(firstTurnGridData);
        when(initialGame.takeTurnForCurrentPlayer()).thenReturn(firstTurnGame);
        GameState gameOverState = mock(GameState.class);
        when(gameOverState.isActive()).thenReturn(false);
        String gameOverMessage = "the game is over";
        when(gameOverState.message()).thenReturn(gameOverMessage);
        when(firstTurnGame.getCurrentGameState()).thenReturn(gameOverState);
        InOrder printersOrderVerifier = inOrder(consolePrinter, displayGridPrinter);

        commandLineGameOrganizer.playNewGame();

        printersOrderVerifier.verify(consolePrinter).println("The game is starting. Here is the Connect Four board.");
        printersOrderVerifier.verify(displayGridPrinter).printGrid(initialGridData);
        printersOrderVerifier.verify(consolePrinter).printBlankLine();
        printersOrderVerifier.verify(displayGridPrinter).printGrid(firstTurnGridData);
        printersOrderVerifier.verify(consolePrinter).printBlankLine();
        printersOrderVerifier.verify(consolePrinter).println(gameOverMessage);
    }

}