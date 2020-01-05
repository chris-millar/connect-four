package com.schrismillar.connect4;

import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class ShouldPlayNewGame {
    public static boolean call(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        consolePrinter.println("Would you like to play a new game? <y/n>");
        String next = consoleScanner.next();
        if ("y".equalsIgnoreCase(next)) {
            return true;
        } else if ("n".equalsIgnoreCase(next)) {
            return false;
        } else {
            consolePrinter.println("INVALID INPUT: You must answer either <y> or <n>");
            return call(consolePrinter, consoleScanner);
        }

    }
}
