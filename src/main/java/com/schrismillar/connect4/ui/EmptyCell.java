package com.schrismillar.connect4.ui;

public class EmptyCell implements Cell {
    private static final String SINGLE_SPACE = " ";

    public String value() {
        return SINGLE_SPACE;
    }
}
