package com.schrismillar.connect4;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.schrismillar.connect4.game.CommandLineGameOrganizer;
import com.schrismillar.connect4.game.Game;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class ApplicationTest {

    @Mock private CommandLineGameOrganizer commandLineGameOrganizer;
    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;

    private Application application;

    @Before
    public void setUp() {
        initMocks(this);
        application = new Application(consolePrinter, consoleScanner, commandLineGameOrganizer);
    }

    @Test
    public void startLoopsAskingIfYouWantToPlayNewGameAndPlaysItIfYouDo() throws Exception {
        Game game1 = mock(Game.class);
        Game game2 = mock(Game.class);
        when(commandLineGameOrganizer.setupNewGame()).thenReturn(game1).thenReturn(game2);
        when(consoleScanner.next()).thenReturn("y").thenReturn("y").thenReturn("n");
        InOrder orderVerifier = inOrder(consolePrinter, commandLineGameOrganizer);

        application.run();

        orderVerifier.verify(consolePrinter).println("Would you like to play a new game? <y/n>");
        orderVerifier.verify(commandLineGameOrganizer).playGame(game1);
        orderVerifier.verify(consolePrinter).println("Would you like to play a new game? <y/n>");
        orderVerifier.verify(commandLineGameOrganizer).playGame(game2);
        orderVerifier.verify(consolePrinter).println("Would you like to play a new game? <y/n>");
    }

    @Test
    public void startDoesNotLoopIfYouAnswerNoTheFirstTime() throws Exception {
        when(consoleScanner.next()).thenReturn("n");

        application.run();

        verify(consolePrinter).println("Would you like to play a new game? <y/n>");
        verify(commandLineGameOrganizer, never()).setupNewGame();
        verify(commandLineGameOrganizer, never()).playGame(any());
    }

    @Test
    public void startPrintsInvalidInputMessageIfYouDoNotRespondWithYesOrNoToPlayingNewGameWillPromptForNewGameAgainUntilValidValueProvided() throws Exception {
        when(consoleScanner.next()).thenReturn("invalid value").thenReturn("other invalid value").thenReturn("n");
        InOrder orderVerifier = inOrder(consolePrinter);

        application.run();

        orderVerifier.verify(consolePrinter).println("Would you like to play a new game? <y/n>");
        orderVerifier.verify(consolePrinter).println("INVALID INPUT: You must answer either <y> or <n>");
        orderVerifier.verify(consolePrinter).println("Would you like to play a new game? <y/n>");
        orderVerifier.verify(consolePrinter).println("INVALID INPUT: You must answer either <y> or <n>");
        orderVerifier.verify(consolePrinter).println("Would you like to play a new game? <y/n>");
        verify(commandLineGameOrganizer, never()).setupNewGame();
        verify(commandLineGameOrganizer, never()).playGame(any());
    }
}