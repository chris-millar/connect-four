package com.schrismillar.connect4;

import com.schrismillar.connect4.game.CommandLineGameOrganizer;
import com.schrismillar.connect4.game.Game;
import com.schrismillar.connect4.game.GameFactory;
import com.schrismillar.connect4.game.player.PlayerDeterminer;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.game.player.PlayerNameValidator;
import com.schrismillar.connect4.ui.DisplayGridPrinter;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

public class Application {

    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;
    private final CommandLineGameOrganizer commandLineGameOrganizer;

    Application(ConsolePrinter consolePrinter,
                        ConsoleScanner consoleScanner,
                        CommandLineGameOrganizer commandLineGameOrganizer) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
        this.commandLineGameOrganizer = commandLineGameOrganizer;
    }

    public static void main(String[] args) {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        ConsoleScanner consoleScanner = new ConsoleScanner();
        GameFactory gameFactory = new GameFactory();
        PlayerFactory playerFactory = new PlayerFactory(consolePrinter, consoleScanner);
        DisplayGridPrinter displayGridPrinter = new DisplayGridPrinter(consolePrinter);
        PlayerNameValidator playerNameValidator = new PlayerNameValidator();
        PlayerDeterminer playerDeterminer =
                new PlayerDeterminer(consolePrinter, consoleScanner, playerNameValidator, playerFactory);
        CommandLineGameOrganizer commandLineGameOrganizer =
                new CommandLineGameOrganizer(consolePrinter, gameFactory, playerFactory, displayGridPrinter, playerDeterminer);

        Application application = new Application(consolePrinter, consoleScanner, commandLineGameOrganizer);
        application.start();
    }

    void start() {
        while (true) {
            if (shouldPlayNewGame(consolePrinter, consoleScanner)) {
                Game game = commandLineGameOrganizer.setupNewGame();
                commandLineGameOrganizer.playGame(game);
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
            consolePrinter.println("INVALID INPUT: You must answer either <y> or <n>");
            return shouldPlayNewGame(consolePrinter, consoleScanner);
        }
    }
}
