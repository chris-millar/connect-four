package com.schrismillar.connect4.game.player;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerDeterminerTest {
    private static final String NAME = "valid player name";

    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;
    @Mock private PlayerNameValidator playerNameValidator;
    @Mock private PlayerFactory playerFactory;
    @Mock private HumanPlayer humanPlayer;

    private PlayerDeterminer playerDeterminer;

    @Before
    public void setUp() {
        initMocks(this);

        playerDeterminer = new PlayerDeterminer(consolePrinter, consoleScanner, playerNameValidator, playerFactory);
    }

    @Test
    public void determinePlayerReturnsHumanPlayerProvidedIdAndWithCommandLineSpecifiedName() {
        when(consoleScanner.nextLine()).thenReturn(NAME);
        when(playerNameValidator.isValid(NAME)).thenReturn(true);
        when(playerFactory.createHumanPlayerWith(PLAYER_ONE, NAME)).thenReturn(humanPlayer);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);


        Player player = playerDeterminer.determinePlayerWithId(PLAYER_ONE);

        assertTrue("Returned Player did not have type HumanPlayer.", player instanceof HumanPlayer);
        assertEquals(humanPlayer, (HumanPlayer) player);

        consoleOrderVerifier.verify(consolePrinter).println("Please provide a name for " + PLAYER_ONE + ":");
        consoleOrderVerifier.verify(consoleScanner).nextLine();
    }

    @Test
    public void determinePlayerPrintsInvalidInputMessageAndRecursesIfInvalidPlayerName() {
        String invalidName = "not a valid player name";
        when(consoleScanner.nextLine()).thenReturn(invalidName).thenReturn(NAME);
        when(playerNameValidator.isValid(invalidName)).thenReturn(false);
        when(playerNameValidator.isValid(NAME)).thenReturn(true);
        when(playerFactory.createHumanPlayerWith(PLAYER_ONE, NAME)).thenReturn(humanPlayer);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);


        Player player = playerDeterminer.determinePlayerWithId(PLAYER_ONE);

        assertTrue("Returned Player did not have type HumanPlayer.", player instanceof HumanPlayer);
        assertEquals(humanPlayer, (HumanPlayer) player);

        consoleOrderVerifier.verify(consolePrinter, times(1)).println("Please provide a name for " + PLAYER_ONE + ":");
        consoleOrderVerifier.verify(consoleScanner, times(1)).nextLine();
        consoleOrderVerifier.verify(consolePrinter, times(1)).println("INVALID INPUT: Player name must be alphaNumeric characters only.");
        consoleOrderVerifier.verify(consolePrinter, times(1)).println("Please provide a name for " + PLAYER_ONE + ":");
        consoleOrderVerifier.verify(consoleScanner, times(1)).nextLine();
    }
}