package com.schrismillar.connect4.game.player;

import java.util.List;
import java.util.Objects;

import com.schrismillar.connect4.model.PlayerId;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.RandomElementSelector;

public class RandomAiPlayer implements Player {

    private final PlayerId playerId;
    private final String name;
    private final ConsolePrinter consolePrinter;
    private final RandomElementSelector randomElementSelector;

    public RandomAiPlayer(PlayerId playerId, String name, ConsolePrinter consolePrinter, RandomElementSelector randomElementSelector) {
        this.playerId = playerId;
        this.name = name;
        this.consolePrinter = consolePrinter;
        this.randomElementSelector = randomElementSelector;
    }

    @Override
    public PlayerId getPlayerId() {
        return playerId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int decideMove(List<Integer> availableColumns) {
        Integer column = randomElementSelector.selectFrom(availableColumns);
        consolePrinter.println(name + ", a Random AI, has decided to play in column " + column);
        return column;
    }

    @Override
    public String toString() {
        return "RandomAiPlayer{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RandomAiPlayer) {
            RandomAiPlayer that = (RandomAiPlayer) obj;
            return playerId == that.playerId &&
                    Objects.equals(name, that.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, name);
    }
}
