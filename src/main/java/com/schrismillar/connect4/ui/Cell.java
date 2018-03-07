package com.schrismillar.connect4.ui;

public interface Cell {
    String SQUARE = "■";
    String ANSI_RESET = "\u001B[0m";

    String value();
}
