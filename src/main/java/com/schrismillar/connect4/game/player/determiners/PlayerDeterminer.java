package com.schrismillar.connect4.game.player.determiners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.model.PlayerId;

@Component
public class PlayerDeterminer {
    private final PlayerNameDeterminer playerNameDeterminer;
    private final PlayerTypeDeterminer playerTypeDeterminer;

    @Autowired
    public PlayerDeterminer(PlayerNameDeterminer playerNameDeterminer, PlayerTypeDeterminer playerTypeDeterminer) {
        this.playerNameDeterminer = playerNameDeterminer;
        this.playerTypeDeterminer = playerTypeDeterminer;
    }

    public Player determinePlayerWithId(PlayerId playerId) {
        String name = playerNameDeterminer.determinePlayerNameFor(playerId);
        return playerTypeDeterminer.determinePlayerType(playerId, name);
    }

}