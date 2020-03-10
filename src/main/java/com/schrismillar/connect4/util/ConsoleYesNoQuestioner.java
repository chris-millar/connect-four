package com.schrismillar.connect4.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleYesNoQuestioner {

    private final ConsolePrinter consolePrinter;
    private final ConsoleScanner consoleScanner;

    @Autowired
    public ConsoleYesNoQuestioner(ConsolePrinter consolePrinter, ConsoleScanner consoleScanner) {
        this.consolePrinter = consolePrinter;
        this.consoleScanner = consoleScanner;
    }

    public boolean ask(String question) {
        consolePrinter.println(question + " <y/n>");
        String answer = consoleScanner.next();
        if ("y".equalsIgnoreCase(answer)) {
            return true;
        } else if ("n".equalsIgnoreCase(answer)) {
            return false;
        } else {
            consolePrinter.println("INVALID INPUT: You must answer either <y> or <n>");
            return ask(question);
        }
    }
}
