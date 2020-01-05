package com.schrismillar.connect4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.schrismillar.connect4.game.CommandLineGameOrganizer;
import com.schrismillar.connect4.game.Game;
import com.schrismillar.connect4.util.ConsolePrinter;
import com.schrismillar.connect4.util.ConsoleScanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;
    private final CommandLineGameOrganizer commandLineGameOrganizer;

    @Autowired
    Application(ConsolePrinter consolePrinter,
                ConsoleScanner consoleScanner,
                CommandLineGameOrganizer commandLineGameOrganizer) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
        this.commandLineGameOrganizer = commandLineGameOrganizer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
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
