package com.schrismillar.connect4;

import com.schrismillar.connect4.util.ConsoleYesNoQuestioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShouldPlayNewGame {

    private final ConsoleYesNoQuestioner consoleYesNoQuestioner;

    @Autowired
    public ShouldPlayNewGame(ConsoleYesNoQuestioner consoleYesNoQuestioner) {
        this.consoleYesNoQuestioner = consoleYesNoQuestioner;
    }

    public boolean call() {
        return consoleYesNoQuestioner.ask("Would you like to play a new game?");
    }
}
