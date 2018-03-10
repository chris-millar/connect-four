package com.schrismillar.connect4.ui;

public class EmptyDisplayCell implements DisplayCell {
    private static final String SINGLE_SPACE = " ";

    public String value() {
        return SINGLE_SPACE;
    }
}
