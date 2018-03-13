package com.schrismillar.connect4.game;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class PlayerFactory {
    private static final PlayerFactory INSTANCE = new PlayerFactory();

    public static PlayerFactory instance() {
        return INSTANCE;
    }

    public HumanPlayer createHumanPlayerWith(PlayerId playerId) {
        return new HumanPlayer(playerId, new ConsolePrinter(), new ConsoleScanner());
    }
}
