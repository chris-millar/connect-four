package com.schrismillar.connect4.game.player.determiners;

import static com.schrismillar.connect4.model.PlayerId.PLAYER_ONE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.schrismillar.connect4.game.player.Player;

public class PlayerDeterminerTest {
    private static final String NAME = "valid player name";

    @Mock private Player player;
    @Mock private PlayerNameDeterminer playerNameDeterminer;
    @Mock private PlayerTypeDeterminer playerTypeDeterminer;

    private PlayerDeterminer playerDeterminer;

    @Before
    public void setUp() {
        initMocks(this);

        playerDeterminer = new PlayerDeterminer(playerNameDeterminer, playerTypeDeterminer);
    }

    @Test
    public void determinePlayerReturnsHumanPlayerProvidedIdAndWithCommandLineSpecifiedName() {
        when(playerNameDeterminer.determinePlayerNameFor(PLAYER_ONE)).thenReturn(NAME);
        when(playerTypeDeterminer.determinePlayerType(PLAYER_ONE, NAME)).thenReturn(player);

        Player returnedPlayer = playerDeterminer.determinePlayerWithId(PLAYER_ONE);

        assertEquals(player, returnedPlayer);
    }
}