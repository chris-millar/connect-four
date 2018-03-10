package com.schrismillar.connect4.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class RedDisplayCellTest {
    @Test
    public void valueReturnsSquareExtendedAnsiCharWithBoldRedTextEscapeSequence() {
        assertEquals("\u001B[31;1m#\u001B[0m", new RedDisplayCell().value());
    }
}