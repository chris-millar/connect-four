package com.schrismillar.connect4;

import java.util.Random;

import com.schrismillar.connect4.game.CommandLineGameOrganizer;
import com.schrismillar.connect4.game.Game;
import com.schrismillar.connect4.game.GameFactory;
import com.schrismillar.connect4.game.player.determiners.PlayerDeterminer;
import com.schrismillar.connect4.game.player.PlayerFactory;
import com.schrismillar.connect4.game.player.PlayerNameValidator;
import com.schrismillar.connect4.game.player.determiners.PlayerNameDeterminer;
import com.schrismillar.connect4.game.player.determiners.PlayerTypeDeterminer;
import com.schrismillar.connect4.ui.DisplayGridPrinter;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;
import com.schrismillar.connect4.util.RandomElementSelector;

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
        RandomElementSelector randomElementSelector = new RandomElementSelector(new Random());
        PlayerFactory playerFactory = new PlayerFactory(consolePrinter, consoleScanner, randomElementSelector);
        DisplayGridPrinter displayGridPrinter = new DisplayGridPrinter(consolePrinter);
        PlayerNameValidator playerNameValidator = new PlayerNameValidator();
        PlayerNameDeterminer playerNameDeterminer =
                new PlayerNameDeterminer(consolePrinter, consoleScanner, playerNameValidator);
        PlayerTypeDeterminer playerTypeDeterminer = new PlayerTypeDeterminer(consolePrinter, consoleScanner, playerFactory);
        PlayerDeterminer playerDeterminer =
                new PlayerDeterminer(playerNameDeterminer, playerTypeDeterminer);
        CommandLineGameOrganizer commandLineGameOrganizer =
                new CommandLineGameOrganizer(consolePrinter, gameFactory, displayGridPrinter, playerDeterminer);

        Application application = new Application(consolePrinter, consoleScanner, commandLineGameOrganizer);
        application.start();
    }

    void start() {
        while (true) {
            if (ShouldPlayNewGame.call(consolePrinter, consoleScanner)) {
                Game game = commandLineGameOrganizer.setupNewGame();
                commandLineGameOrganizer.playGame(game);
            } else {
                return;
            }
        }
    }

}
