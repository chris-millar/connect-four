package com.schrismillar.connect4.game.player;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static com.schrismillar.connect4.model.PlayerId.PLAYER_TWO;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class HumanPlayerTest {
    private static final String NAME = "Some Player Name";

    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void decideMovePromptsConsoleForSelection() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner, NAME);
        when(consoleScanner.nextInt()).thenReturn(2);
        List<Integer> availableColumns = asList(1, 2, 3);

        humanPlayer.decideMove(availableColumns);

        verify(consolePrinter).println(expectedPlayPrompt(NAME, availableColumns));
    }

    @Test
    public void decideMoveReturnsIntSpecifiedViaConsole() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner, NAME);
        when(consoleScanner.nextInt()).thenReturn(2);
        List<Integer> availableColumns = asList(1, 2, 3);

        int move = humanPlayer.decideMove(availableColumns);

        assertEquals(2, move);
    }

    @Test
    public void decideMovePrintsInvalidInputMessageThenRecursesIfConsoleProvidedIntNotOneOfAvailableColumns() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner, NAME);
        when(consoleScanner.nextInt()).thenReturn(77).thenReturn(3);
        List<Integer> availableColumns = asList(1, 2, 3);

        int move = humanPlayer.decideMove(availableColumns);

        verify(consolePrinter, times(2)).println(expectedPlayPrompt(NAME, availableColumns));
        verify(consolePrinter, times(1)).println(expectedInvalidInputMessage(availableColumns));

        assertEquals(3, move);
    }

    @Test
    public void decideMovePrintsInvalidInputMessageThenRecursesIfConsoleProvidedValueIsNotAnInt() {
        HumanPlayer humanPlayer = new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner, NAME);
        when(consoleScanner.nextInt()).
                thenThrow(new RuntimeException("ConsoleScanner was looking for an int but found a String")).
                thenReturn(1);
        List<Integer> availableColumns = asList(1, 2, 3);

        int move = humanPlayer.decideMove(availableColumns);

        verify(consolePrinter, times(2)).println(expectedPlayPrompt(NAME, availableColumns));
        verify(consolePrinter, times(1)).println(expectedInvalidInputMessage(availableColumns));

        assertEquals(1, move);
    }

    @Test
    public void getPlayerIdReturnsPlayerIdPlayerConstructedWith() {
        HumanPlayer player = new HumanPlayer(PLAYER_TWO, null, null, null);

        assertEquals(PLAYER_TWO, player.getPlayerId());
    }

    @Test
    public void getNameReturnsNamePlayerConstructedWith() {
        HumanPlayer player = new HumanPlayer(PLAYER_ONE, null, null, NAME);

        assertEquals(NAME, player.getName());
    }

    private String expectedInvalidInputMessage(List<Integer> availableColumns) {
        return "INVALID INPUT: You must select one of the column ids " + availableColumns;
    }

    private String expectedPlayPrompt(String name, List<Integer> availableColumns) {
        return name + ", it's your turn. Of the available columns " + availableColumns +
                " which would you like to play in?";
    }
}