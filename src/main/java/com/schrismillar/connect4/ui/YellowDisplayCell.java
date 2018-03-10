package com.schrismillar.connect4.ui;

public class YellowDisplayCell implements DisplayCell {
    private static final String ANSI_BOLD_YELLOW = "\u001B[33;1m";

    @Override
    public String value() {
        return yellow(VALUE_CHARACTER);
    }

    private String yellow(String string) {
        return ANSI_BOLD_YELLOW + string + ANSI_RESET;
    }
}
