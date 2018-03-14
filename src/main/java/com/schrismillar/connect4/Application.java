package com.schrismillar.connect4;

import com.schrismillar.connect4.game.CommandLineGameOrganizer;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }

    private void start() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        ConsoleScanner consoleScanner = new ConsoleScanner();
        CommandLineGameOrganizer commandLineGameOrganizer = new CommandLineGameOrganizer(consolePrinter, consoleScanner);
        while (true) {
            if (shouldPlayNewGame(consolePrinter, consoleScanner)) {
                commandLineGameOrganizer.playNewGame();
            } else {
                return;
            }
        }
    }

    private boolean shouldPlayNewGame(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        consolePrinter.println("Would you like to play a new game? <y/n>");
        String next = consoleScanner.next();
        if ("y".equalsIgnoreCase(next)) {
            return true;
        } else if ("n".equalsIgnoreCase(next)) {
            return false;
        } else {
            consolePrinter.println("INVALID INPUT: You must answer either y or n");
            return shouldPlayNewGame(consolePrinter, consoleScanner);
        }
    }
}
