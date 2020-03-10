package com.schrismillar.connect4;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.schrismillar.connect4.game.CommandLineGameOrganizer;
import com.schrismillar.connect4.game.Game;

public class ApplicationTest {

    @Mock private CommandLineGameOrganizer commandLineGameOrganizer;
    @Mock private ShouldPlayNewGame shouldPlayNewGame;

    private Application application;

    @Before
    public void setUp() {
        initMocks(this);
        application = new Application(commandLineGameOrganizer, shouldPlayNewGame);
    }

    @Test
    public void startLoopsAskingIfYouWantToPlayNewGameAndPlaysItIfYouDo() throws Exception {
        Game game1 = mock(Game.class);
        Game game2 = mock(Game.class);
        when(commandLineGameOrganizer.setupNewGame()).thenReturn(game1).thenReturn(game2);
        when(shouldPlayNewGame.call()).thenReturn(true).thenReturn(true).thenReturn(false);
        InOrder orderVerifier = inOrder(shouldPlayNewGame, commandLineGameOrganizer);

        application.run();

        orderVerifier.verify(commandLineGameOrganizer).playGame(game1);
        orderVerifier.verify(commandLineGameOrganizer).playGame(game2);
    }

    @Test
    public void startDoesNotLoopIfYouAnswerNoTheFirstTime() throws Exception {
        when(shouldPlayNewGame.call()).thenReturn(false);

        application.run();

        verify(commandLineGameOrganizer, never()).setupNewGame();
        verify(commandLineGameOrganizer, never()).playGame(any());
    }
}
