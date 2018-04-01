package com.schrismillar.connect4.game.player.determiners;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.model.PlayerId;

public class PlayerDeterminer {
    private final PlayerFactory playerFactory;
    private final PlayerNameDeterminer playerNameDeterminer;

    public PlayerDeterminer(PlayerFactory playerFactory, PlayerNameDeterminer playerNameDeterminer) {
        this.playerFactory = playerFactory;
        this.playerNameDeterminer = playerNameDeterminer;
    }

    public Player determinePlayerWithId(PlayerId playerId) {
        String name = playerNameDeterminer.determinePlayerNameFor(playerId);
        return playerFactory.createHumanPlayerWith(playerId, name);
    }

}
