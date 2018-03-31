package com.schrismillar.connect4.game.player;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerFactory {
    public HumanPlayer createHumanPlayerWith(PlayerId playerId) {
        return new HumanPlayer(playerId, new ConsolePrinter(), new ConsoleScanner(), playerId.name());
    }
}
