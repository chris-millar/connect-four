package com.schrismillar.connect4.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmptyDisplayCellTest {

    @Test
    public void emptyCellValueIsASingleSpace() {
        assertEquals(" ", new EmptyDisplayCell().value());
    }

}