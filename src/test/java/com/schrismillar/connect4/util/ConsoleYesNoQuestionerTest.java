package com.schrismillar.connect4.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConsoleYesNoQuestionerTest {

    private final String QUESTION = "Do you want to play a game?";

    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;

    @Captor ArgumentCaptor<String> messageCaptor;

    private ConsoleYesNoQuestioner consoleYesNoQuestioner;

    @Before
    public void setUp() {
        initMocks(this);
        consoleYesNoQuestioner = new ConsoleYesNoQuestioner(consolePrinter, consoleScanner);
    }

    @Test
    public void askDisplaysInvalidInputMessageAndAsksQuestionAgain() {
        when(consoleScanner.next()).thenReturn("invalid value").thenReturn("y");

        consoleYesNoQuestioner.ask(QUESTION);

        verify(consolePrinter, times(3)).println(messageCaptor.capture());
        List<String> messages = messageCaptor.getAllValues();
        assertEquals(QUESTION + " <y/n>", messages.get(0));
        assertEquals("INVALID INPUT: You must answer either <y> or <n>", messages.get(1));
        assertEquals(QUESTION + " <y/n>", messages.get(2));
    }

    @Test
    public void askReturnsTrueWhenGivenYes() {
        when(consoleScanner.next()).thenReturn("y");
        boolean answer = consoleYesNoQuestioner.ask(QUESTION);
        assertTrue(answer);
    }

    @Test
    public void askReturnsTrueWhenGivenYesIgnoringCase() {
        when(consoleScanner.next()).thenReturn("Y");
        boolean answer = consoleYesNoQuestioner.ask(QUESTION);
        assertTrue(answer);
    }

    @Test
    public void askReturnsFalseWhenGivenNo() {
        when(consoleScanner.next()).thenReturn("n");
        boolean answer = consoleYesNoQuestioner.ask(QUESTION);
        assertFalse(answer);
    }

    @Test
    public void askReturnsFalseWhenGivenNoIgnoringCase() {
        when(consoleScanner.next()).thenReturn("N");
        boolean answer = consoleYesNoQuestioner.ask(QUESTION);
        assertFalse(answer);
    }
}
