package com.schrismillar.connect4.game.player.determiners;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.HumanPlayer;
import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.player.PlayerFactory;

public class PlayerDeterminerTest {
    private static final String NAME = "valid player name";

    @Mock private PlayerFactory playerFactory;
    @Mock private HumanPlayer humanPlayer;
    @Mock private PlayerNameDeterminer playerNameDeterminer;

    private PlayerDeterminer playerDeterminer;

    @Before
    public void setUp() {
        initMocks(this);

        playerDeterminer = new PlayerDeterminer(playerFactory, playerNameDeterminer);
    }

    @Test
    public void determinePlayerReturnsHumanPlayerProvidedIdAndWithCommandLineSpecifiedName() {
        when(playerNameDeterminer.determinePlayerNameFor(PLAYER_ONE)).thenReturn(NAME);
        when(playerFactory.createHumanPlayerWith(PLAYER_ONE, NAME)).thenReturn(humanPlayer);

        Player player = playerDeterminer.determinePlayerWithId(PLAYER_ONE);

        assertTrue("Returned Player did not have type HumanPlayer.", player instanceof HumanPlayer);
        assertEquals(humanPlayer, (HumanPlayer) player);
    }
}