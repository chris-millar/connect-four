package com.schrismillar.connect4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

@Component
public class ShouldPlayNewGame {

    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;

    @Autowired
    public ShouldPlayNewGame(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
    }

    public boolean call() {
        consolePrinter.println("Would you like to play a new game? <y/n>");
        String next = consoleScanner.next();
        if ("y".equalsIgnoreCase(next)) {
            return true;
        } else if ("n".equalsIgnoreCase(next)) {
            return false;
        } else {
            consolePrinter.println("INVALID INPUT: You must answer either <y> or <n>");
            return call();
        }

    }
}
