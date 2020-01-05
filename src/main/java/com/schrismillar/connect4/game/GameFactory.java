package com.schrismillar.connect4.game;

import org.springframework.stereotype.Component;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.state.ActiveGameState;

@Component
public class GameFactory {
    Game createGameWith(Player playerOne, Player playerTwo) {
        return new Game(playerOne, playerTwo, new Board(), new ActiveGameState());
    }
}
