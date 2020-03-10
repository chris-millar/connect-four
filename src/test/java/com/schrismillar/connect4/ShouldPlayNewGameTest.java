package com.schrismillar.connect4;

import com.schrismillar.connect4.util.ConsoleYesNoQuestioner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShouldPlayNewGameTest {

    @Mock private ConsoleYesNoQuestioner consoleYesNoQuestioner;

    private ShouldPlayNewGame shouldPlayNewGame;

    @Before
    public void setUp() {
        initMocks(this);
        shouldPlayNewGame = new ShouldPlayNewGame(consoleYesNoQuestioner);
    }

    @Test
    public void callDelegatesToQuestioner() {
        when(consoleYesNoQuestioner.ask("Would you like to play a new game?")).thenReturn(true);
        assertTrue(shouldPlayNewGame.call());

        when(consoleYesNoQuestioner.ask("Would you like to play a new game?")).thenReturn(false);
        assertFalse(shouldPlayNewGame.call());
    }
}
