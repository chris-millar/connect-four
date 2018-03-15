package com.schrismillar.connect4.game;

import com.schrismillar.connect4.game.player.Player;
import com.schrismillar.connect4.game.state.ActiveGameState;

public class GameFactory {
    Game createGameWith(Player playerOne, Player playerTwo) {
        return new Game(playerOne, playerTwo, new Board(), new ActiveGameState());
    }
}
