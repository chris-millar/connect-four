package com.schrismillar.connect4.game.player;

import java.util.List;

import com.schrismillar.connect4.model.PlayerId;

public interface Player {
    PlayerId getPlayerId();
    int decideMove(List<Integer> availableColumns);
}
