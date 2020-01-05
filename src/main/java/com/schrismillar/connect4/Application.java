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

    private final CommandLineGameOrganizer commandLineGameOrganizer;
    private final ShouldPlayNewGame shouldPlayNewGame;

    @Autowired
    Application(CommandLineGameOrganizer commandLineGameOrganizer,
                ShouldPlayNewGame shouldPlayNewGame) {
        this.commandLineGameOrganizer = commandLineGameOrganizer;
        this.shouldPlayNewGame = shouldPlayNewGame;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        while (true) {
            if (shouldPlayNewGame.call()) {
                Game game = commandLineGameOrganizer.setupNewGame();
                commandLineGameOrganizer.playGame(game);
            } else {
                return;
            }
        }
    }
}
