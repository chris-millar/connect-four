package com.schrismillar.connect4.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmptyCellTest {

    @Test
    public void emptyCellValueIsASingleSpace() {
        assertEquals(" ", new EmptyCell().value());
    }

}