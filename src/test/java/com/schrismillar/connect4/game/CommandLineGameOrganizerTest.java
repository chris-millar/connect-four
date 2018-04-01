package com.schrismillar.connect4.game;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static com.schrismillar.connect4.model.PlayerId.PLAYER_TWO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.HumanPlayer;
import com.schrismillar.connect4.game.player.PlayerDeterminer;
import com.schrismillar.connect4.game.state.ActiveGameState;
import com.schrismillar.connect4.game.state.GameState;
import com.schrismillar.connect4.model.GridData;
import com.schrismillar.connect4.ui.DisplayGridPrinter;
import com.schrismillar.connect4.util.ConsolePrinter;

public class CommandLineGameOrganizerTest {
    @Mock private ConsolePrinter consolePrinter;
    @Mock private GameFactory gameFactory;
    @Mock private HumanPlayer playerOne;
    @Mock private HumanPlayer playerTwo;
    @Mock private DisplayGridPrinter displayGridPrinter;
    @Mock private PlayerDeterminer playerDeterminer;

    private CommandLineGameOrganizer commandLineGameOrganizer;

    @Before
    public void setUp() {
        initMocks(this);
        commandLineGameOrganizer = new CommandLineGameOrganizer(consolePrinter, gameFactory, displayGridPrinter,
                                                                playerDeterminer);
    }

    @Test
    public void setupNewGameReturnsGameWithPlayerOneAndPlayerTwoFromPlayerDeterminer() {
        when(playerDeterminer.determinePlayerWithId(PLAYER_ONE)).thenReturn(playerOne);
        when(playerDeterminer.determinePlayerWithId(PLAYER_TWO)).thenReturn(playerTwo);
        Game initialGame = mock(Game.class);
        GridData initialGridData = mock(GridData.class);
        when(initialGame.getGridData()).thenReturn(initialGridData);
        when(gameFactory.createGameWith(playerOne, playerTwo)).thenReturn(initialGame);
        InOrder printersOrderVerifier = inOrder(consolePrinter, displayGridPrinter);

        Game game = commandLineGameOrganizer.setupNewGame();

        assertEquals(initialGame, game);

        printersOrderVerifier.verify(consolePrinter).println("The game is starting. Here is the Connect Four board.");
        printersOrderVerifier.verify(displayGridPrinter).printGrid(initialGridData);
        printersOrderVerifier.verify(consolePrinter).printBlankLine();
    }

    @Test
    public void playGameLoopsWhileGameIsActive() {
        Game game = mock(Game.class);
        when(game.getCurrentGameState()).thenReturn(new ActiveGameState());
        Game firstTurnGame = mock(Game.class);
        GridData firstTurnGridData = mock(GridData.class);
        when(firstTurnGame.getGridData()).thenReturn(firstTurnGridData);
        when(game.takeTurnForCurrentPlayer()).thenReturn(firstTurnGame);
        when(firstTurnGame.getCurrentGameState()).thenReturn(new ActiveGameState());

        Game secondTurnGame = mock(Game.class);
        GridData secondTurnGridData = mock(GridData.class);
        when(secondTurnGame.getGridData()).thenReturn(secondTurnGridData);
        when(firstTurnGame.takeTurnForCurrentPlayer()).thenReturn(secondTurnGame);
        String gameOverMessage = "the game is over";
        GameState gameOverState = mockGameOverState(gameOverMessage);
        when(secondTurnGame.getCurrentGameState()).thenReturn(gameOverState);
        InOrder printersOrderVerifier = inOrder(consolePrinter, displayGridPrinter);

        commandLineGameOrganizer.playGame(game);

        printersOrderVerifier.verify(displayGridPrinter).printGrid(firstTurnGridData);
        printersOrderVerifier.verify(consolePrinter).printBlankLine();
        printersOrderVerifier.verify(displayGridPrinter).printGrid(secondTurnGridData);
        printersOrderVerifier.verify(consolePrinter).printBlankLine();
        printersOrderVerifier.verify(consolePrinter).println(gameOverMessage);
    }

    private GameState mockGameOverState(String gameOverMessage) {
        GameState gameOverState = mock(GameState.class);
        when(gameOverState.isActive()).thenReturn(false);
        when(gameOverState.message()).thenReturn(gameOverMessage);
        return gameOverState;
    }
}