package com.schrismillar.connect4.game.player;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static com.schrismillar.connect4.model.PlayerId.PLAYER_TWO;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.RandomElementSelector;

public class RandomAiPlayerTest {
    private static final String NAME = "Some Player Name";

    @Mock private ConsolePrinter consolePrinter;
    @Mock private RandomElementSelector randomElementSelector;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void decideMoveRandomlyPicksMoveAndPrintsTheDecidedMove() {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(PLAYER_ONE, NAME, consolePrinter, randomElementSelector);
        List<Integer> availableColumns = asList(1, 2, 3, 4);
        int expectedMove = 3;
        when(randomElementSelector.selectFrom(availableColumns)).thenReturn(expectedMove);

        int move = randomAiPlayer.decideMove(availableColumns);

        assertEquals(expectedMove, move);
        verify(consolePrinter).println(NAME + ", a Random AI, has decided to play in column " + expectedMove);
    }

    @Test
    public void getPlayerIdReturnsPlayerIdPlayerConstructedWith() {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(PLAYER_ONE, NAME, consolePrinter, randomElementSelector);

        assertEquals(PLAYER_ONE, randomAiPlayer.getPlayerId());
    }

    @Test
    public void getNameReturnsNamePlayerConstructedWith() {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(PLAYER_ONE, NAME, consolePrinter, randomElementSelector);

        assertEquals(NAME, randomAiPlayer.getName());
    }

    @Test
    public void equalsAndHashCode() {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(PLAYER_ONE, NAME, consolePrinter, randomElementSelector);
        assertEquals(new RandomAiPlayer(PLAYER_ONE, NAME, consolePrinter, randomElementSelector), randomAiPlayer);
        assertNotEquals(new RandomAiPlayer(PLAYER_TWO, NAME, consolePrinter, randomElementSelector), randomAiPlayer);
        assertNotEquals(new RandomAiPlayer(PLAYER_ONE, "other name", consolePrinter, randomElementSelector), randomAiPlayer);
        Player otherTypeOfPlayer = mock(Player.class);
        assertNotEquals(otherTypeOfPlayer, randomAiPlayer);
        assertNotEquals("not a player", randomAiPlayer);
    }
}