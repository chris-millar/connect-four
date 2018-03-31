package com.schrismillar.connect4.game.player;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerFactoryTest {

    @Test
    public void createHumanPlayerWithReturnHumanPlayerWithSpecifiedProperties() {
        String playerName = "playerName";
        ConsolePrinter consolePrinter = mock(ConsolePrinter.class);
        ConsoleScanner consoleScanner = mock(ConsoleScanner.class);
        PlayerFactory playerFactory = new PlayerFactory(consolePrinter, consoleScanner);

        HumanPlayer player = playerFactory.createHumanPlayerWith(PLAYER_ONE, playerName);

        assertEquals(new HumanPlayer(PLAYER_ONE, consolePrinter, consoleScanner, playerName), player);
    }
}