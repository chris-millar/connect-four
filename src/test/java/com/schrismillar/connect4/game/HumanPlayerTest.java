package com.schrismillar.connect4.game;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.HumanPlayer;
import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class HumanPlayerTest {

    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void decideMovePromptsConsoleForSelection() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner);
        when(consoleScanner.nextInt()).thenReturn(2);
        List<Integer> availableColumns = asList(1, 2, 3);

        humanPlayer.decideMove(availableColumns);

        verify(consolePrinter).println(expectedPlayPrompt(PLAYER_ONE, availableColumns));
    }

    @Test
    public void decideMoveReturnsIntSpecifiedViaConsole() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner);
        when(consoleScanner.nextInt()).thenReturn(2);
        List<Integer> availableColumns = asList(1, 2, 3);

        int move = humanPlayer.decideMove(availableColumns);

        assertEquals(2, move);
    }

    @Test
    public void decideMovePrintsInvalidInputMessageThenRecursesIfConsoleProvidedIntNotOneOfAvailableColumns() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner);
        when(consoleScanner.nextInt()).thenReturn(77).thenReturn(3);
        List<Integer> availableColumns = asList(1, 2, 3);

        int move = humanPlayer.decideMove(availableColumns);

        verify(consolePrinter, times(2)).println(expectedPlayPrompt(PLAYER_ONE, availableColumns));
        verify(consolePrinter, times(1)).println(expectedInvalidInputMessage(availableColumns));

        assertEquals(3, move);
    }

    @Test
    public void decideMovePrintsInvalidInputMessageThenRecursesIfConsoleProvidedValueIsNotAnInt() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner);
        when(consoleScanner.nextInt()).
                thenThrow(new RuntimeException("ConsoleScanner was looking for an int but found a String")).
                thenReturn(1);
        List<Integer> availableColumns = asList(1, 2, 3);

        int move = humanPlayer.decideMove(availableColumns);

        verify(consolePrinter, times(2)).println(expectedPlayPrompt(PLAYER_ONE, availableColumns));
        verify(consolePrinter, times(1)).println(expectedInvalidInputMessage(availableColumns));

        assertEquals(1, move);
    }

    private String expectedInvalidInputMessage(List<Integer> availableColumns) {
        return "INVALID INPUT: You must select one of the column ids " + availableColumns;
    }

    private String expectedPlayPrompt(PlayerId playerId, List<Integer> availableColumns) {
        return playerId + " it's your turn. Of the available columns " + availableColumns +
                " which would you like to play in?";
    }
}