package com.schrismillar.connect4.ui;

public interface DisplayCell {
    String VALUE_CHARACTER = "#";
    String ANSI_RESET = "\u001B[0m";

    String value();
}
