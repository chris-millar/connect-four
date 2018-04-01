package com.schrismillar.connect4.game.player;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static com.schrismillar.connect4.model.PlayerId.PLAYER_TWO;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;
import com.schrismillar.connect4.util.RandomElementSelector;

public class PlayerFactoryTest {
    private static final String NAME = "playerName";

    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;
    @Mock private RandomElementSelector randomElementSelector;

    private PlayerFactory playerFactory;

    @Before
    public void setUp() {
        initMocks(this);

        playerFactory = new PlayerFactory(consolePrinter, consoleScanner, randomElementSelector);
    }

    @Test
    public void createHumanPlayerWithReturnsHumanPlayerWithSpecifiedProperties() {
        HumanPlayer player = playerFactory.createHumanPlayerWith(PLAYER_ONE, NAME);

        assertEquals(new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner, NAME), player);
    }

    @Test
    public void createRandomAiPlayerWithReturnsRandomAiPlayerWithSpecifiedProperties() {
        RandomAiPlayer player = playerFactory.createRandomAiPlayerWith(PLAYER_TWO, NAME);

        assertEquals(new RandomAiPlayer(PLAYER_TWO, NAME, consolePrinter, randomElementSelector), player);
    }
}