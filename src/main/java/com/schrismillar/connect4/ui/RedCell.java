package com.schrismillar.connect4.ui;

public class RedCell implements Cell {
    private static final String ANSI_BOLD_RED = "\u001B[31;1m";

    @Override
    public String value() {
        return red(SQUARE);
    }

    private String red(String string) {
        return ANSI_BOLD_RED + string + ANSI_RESET;
    }
}
