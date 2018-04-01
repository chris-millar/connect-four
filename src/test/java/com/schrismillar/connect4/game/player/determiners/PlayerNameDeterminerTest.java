package com.schrismillar.connect4.game.player.determiners;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.PlayerNameValidator;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerNameDeterminerTest {
    private static final String NAME = "valid player name";

    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;
    @Mock private PlayerNameValidator playerNameValidator;

    private PlayerNameDeterminer playerNameDeterminer;

    @Before
    public void setUp() {
        initMocks(this);

        playerNameDeterminer = new PlayerNameDeterminer(consolePrinter, consoleScanner, playerNameValidator);
    }

    @Test
    public void determinePlayerPromptsForNameAndReturnsItWhenIsValid() {
        when(consoleScanner.nextLine()).thenReturn(NAME);
        when(playerNameValidator.isValid(NAME)).thenReturn(true);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);


        String name = playerNameDeterminer.determinePlayerNameFor(PLAYER_ONE);

        assertEquals(NAME, name);

        consoleOrderVerifier.verify(consolePrinter).println("Please provide a name for " + PLAYER_ONE + ":");
        consoleOrderVerifier.verify(consoleScanner).nextLine();
    }

    @Test
    public void determinePlayerPrintsInvalidInputMessageAndRecursesIfInvalidPlayerName() {
        String invalidName = "not a valid player name";
        when(consoleScanner.nextLine()).thenReturn(invalidName).thenReturn(NAME);
        when(playerNameValidator.isValid(invalidName)).thenReturn(false);
        when(playerNameValidator.isValid(NAME)).thenReturn(true);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);


        String name = playerNameDeterminer.determinePlayerNameFor(PLAYER_ONE);

        consoleOrderVerifier.verify(consolePrinter, times(1)).println("Please provide a name for " + PLAYER_ONE + ":");
        consoleOrderVerifier.verify(consoleScanner, times(1)).nextLine();
        consoleOrderVerifier.verify(consolePrinter, times(1)).println("INVALID INPUT: Player name must be alphaNumeric characters only.");
        consoleOrderVerifier.verify(consolePrinter, times(1)).println("Please provide a name for " + PLAYER_ONE + ":");
        consoleOrderVerifier.verify(consoleScanner, times(1)).nextLine();
    }
}