package com.schrismillar.connect4.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class YellowDisplayCellTest {
    @Test
    public void valueReturnsSquareExtendedAnsiCharWithBoldYellowTextEscapeSequence() {
        assertEquals("\u001B[33;1m#\u001B[0m", new YellowDisplayCell().value());
    }

}