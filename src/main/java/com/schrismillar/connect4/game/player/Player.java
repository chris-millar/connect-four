package com.schrismillar.connect4.game.player;

import java.util.List;

import com.schrismillar.connect4.model.PlayerId;

public interface Player {
    PlayerId getPlayerId();
    String getName();
    int decideMove(List<Integer> availableColumns);
}
