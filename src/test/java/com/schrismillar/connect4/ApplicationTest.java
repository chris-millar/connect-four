package com.schrismillar.connect4;

import static org.mockito.Mockito.*;

import org.junit.Test;

import com.schrismillar.connect4.game.CommandLineGameOrganizer;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class ApplicationTest {
    @Test
    public void startLoopsAskingIfYouWantToPlayNewGameAndPlaysItIfYouDo() {
        ConsolePrinter consolePrinter = mock(ConsolePrinter.class);
        ConsoleScanner consoleScanner= mock(ConsoleScanner.class);
        CommandLineGameOrganizer commandLineGameOrganizer = mock(CommandLineGameOrganizer.class);
        Application application = new Application(consolePrinter, consoleScanner, commandLineGameOrganizer);
        when(consoleScanner.next()).thenReturn("y").thenReturn("y").thenReturn("n");

        application.start();

        verify(consolePrinter, times(3)).println("Would you like to play a new game? <y/n>");
        verify(commandLineGameOrganizer, times(2)).playNewGame();
    }

    @Test
    public void startDoesNotLoopIfYouAnswerNoTheFirstTime() {
        ConsolePrinter consolePrinter = mock(ConsolePrinter.class);
        ConsoleScanner consoleScanner= mock(ConsoleScanner.class);
        CommandLineGameOrganizer commandLineGameOrganizer = mock(CommandLineGameOrganizer.class);
        Application application = new Application(consolePrinter, consoleScanner, commandLineGameOrganizer);
        when(consoleScanner.next()).thenReturn("n");

        application.start();

        verify(consolePrinter, times(1)).println("Would you like to play a new game? <y/n>");
        verify(commandLineGameOrganizer, never()).playNewGame();
    }

    @Test
    public void startPrintsInvalidInputMessageIfYouDoNotRespondWithYesOrNoToPlayingNewGameWillPromptForNewGameAgainUntilValidValueProvided() {
        ConsolePrinter consolePrinter = mock(ConsolePrinter.class);
        ConsoleScanner consoleScanner= mock(ConsoleScanner.class);
        CommandLineGameOrganizer commandLineGameOrganizer = mock(CommandLineGameOrganizer.class);
        Application application = new Application(consolePrinter, consoleScanner, commandLineGameOrganizer);
        when(consoleScanner.next()).thenReturn("invalid value").thenReturn("other invalid value").thenReturn("n");

        application.start();

        verify(consolePrinter, times(3)).println("Would you like to play a new game? <y/n>");
        verify(consolePrinter, times(2)).println("INVALID INPUT: You must answer either <y> or <n>");
        verify(commandLineGameOrganizer, never()).playNewGame();
    }
}