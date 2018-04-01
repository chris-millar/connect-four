package com.schrismillar.connect4.game.player.determiners;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.HumanPlayer;
import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.game.player.RandomAiPlayer;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerTypeDeterminerTest {
    private static final String NAME = "valid player name";

    @Mock private ConsolePrinter consolePrinter;
    @Mock private ConsoleScanner consoleScanner;
    @Mock private PlayerFactory playerFactory;
    @Mock private HumanPlayer humanPlayer;

    private PlayerTypeDeterminer playerTypeDeterminer;

    @Before
    public void setUp() {
        initMocks(this);

        playerTypeDeterminer = new PlayerTypeDeterminer(consolePrinter, consoleScanner, playerFactory);
    }

    @Test
    public void determinePlayerTypePromptsForTypeAndReturnsHumanPlayerWhenUserAnswersWithHuman() {
        when(consoleScanner.next()).thenReturn("human");
        when(playerFactory.createHumanPlayerWith(PLAYER_ONE, NAME)).thenReturn(humanPlayer);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);

        Player player = playerTypeDeterminer.determinePlayerType(PLAYER_ONE, NAME);

        assertEquals(humanPlayer, player);
        consoleOrderVerifier.verify(consolePrinter).println("Choose a type of player for " + PLAYER_ONE + ". <human>/<h> or <ai>");
        consoleOrderVerifier.verify(consoleScanner).next();
    }

    @Test
    public void determinePlayerTypePromptsForTypeAndReturnsHumanPlayerWhenUserAnswersWithJustH() {
        when(consoleScanner.next()).thenReturn("h");
        when(playerFactory.createHumanPlayerWith(PLAYER_ONE, NAME)).thenReturn(humanPlayer);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);

        Player player = playerTypeDeterminer.determinePlayerType(PLAYER_ONE, NAME);

        assertEquals(humanPlayer, player);
        consoleOrderVerifier.verify(consolePrinter).println("Choose a type of player for " + PLAYER_ONE + ". <human>/<h> or <ai>");
        consoleOrderVerifier.verify(consoleScanner).next();
    }

    @Test
    public void determinePlayerTypePromptsForTypeAndReturnsRandomAiPlayerWhenUserAnswersWithAi() {
        RandomAiPlayer randomAiPlayer = mock(RandomAiPlayer.class);
        when(consoleScanner.next()).thenReturn("ai");
        when(playerFactory.createRandomAiPlayerWith(PLAYER_ONE, NAME)).thenReturn(randomAiPlayer);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);

        Player player = playerTypeDeterminer.determinePlayerType(PLAYER_ONE, NAME);

        assertEquals(randomAiPlayer, player);
        consoleOrderVerifier.verify(consolePrinter).println("Choose a type of player for " + PLAYER_ONE + ". <human>/<h> or <ai>");
        consoleOrderVerifier.verify(consoleScanner).next();
    }

    @Test
    public void determinePlayerTypePrintsInvalidInputMessageAndRecursesIfInvalidPlayerType() {
        String invalidType = "not a valid player type";
        when(consoleScanner.next()).thenReturn(invalidType).thenReturn("h");
        when(playerFactory.createHumanPlayerWith(PLAYER_ONE, NAME)).thenReturn(humanPlayer);
        InOrder consoleOrderVerifier = inOrder(consolePrinter, consoleScanner);


        Player player = playerTypeDeterminer.determinePlayerType(PLAYER_ONE, NAME);

        assertEquals(humanPlayer, player);
        consoleOrderVerifier.verify(consolePrinter).println("Choose a type of player for " + PLAYER_ONE + ". <human>/<h> or <ai>");
        consoleOrderVerifier.verify(consoleScanner).next();
        consoleOrderVerifier.verify(consolePrinter).println("INVALID INPUT: Player type must be either <human>/<h> or <ai>.");
        consoleOrderVerifier.verify(consolePrinter).println("Choose a type of player for " + PLAYER_ONE + ". <human>/<h> or <ai>");
        consoleOrderVerifier.verify(consoleScanner).next();
    }

}