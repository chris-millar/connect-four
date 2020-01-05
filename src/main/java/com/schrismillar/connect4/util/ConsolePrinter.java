package com.schrismillar.connect4.util;

import org.springframework.stereotype.Component;

@Component
public class ConsolePrinter {

    public void println(String string) {
        System.out.println(string);
    }

    public void printBlankLine() {
        println("");
    }
}
