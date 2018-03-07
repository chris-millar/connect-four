package com.schrismillar.connect4.ui;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GridTest {
    private static final Cell T = () -> "T";
    private static final Cell D = () -> "D";
    private static final Cell A = () -> "A";

    @Test
    public void displayValueReturnsEmptyStringIfNoCellsProvided() {
        assertEquals("", new Grid(emptyList()).displayValue());
    }

    @Test
    public void displayValueReturnsGridWithSingleCellIfOnlyOneCellProvided() {
        List<List<Cell>> cells = singletonList(singletonList(T));

        String grid = new Grid(cells).displayValue();

        String expectedGrid =
                "|---|" + "\n" +
                "|(T)|" + "\n" +
                "|---|" + "\n";
        assertEquals(expectedGrid, grid);
    }

    @Test
    public void displayValueReturnsGridWithDifferentCellValues() {
        List<List<Cell>> cells = singletonList(asList(T, D));

        String grid = new Grid(cells).displayValue();

        String expectedGrid =
                "|---|" + "|---|" + "\n" +
                "|(T)|" + "|(D)|" + "\n" +
                "|---|" + "|---|" + "\n";
        assertEquals(expectedGrid, grid);
    }

    @Test
    public void displayValueReturns2x2Grid() {
        List<List<Cell>> cells = asList(asList(T, D),
                                        asList(D, T));

        String grid = new Grid(cells).displayValue();

        String expectedGrid =
                "|---|" + "|---|" + "\n" +
                "|(T)|" + "|(D)|" + "\n" +
                "|---|" + "|---|" + "\n" +
                "|---|" + "|---|" + "\n" +
                "|(D)|" + "|(T)|" + "\n" +
                "|---|" + "|---|" + "\n";
        assertEquals(expectedGrid, grid);
    }

    @Test
    public void displayValueReturns3x3Grid() {
        List<List<Cell>> cells = asList(asList(T, D, A),
                                        asList(A, A, A),
                                        asList(T, T, D));

        String grid = new Grid(cells).displayValue();

        String expectedGrid =
                "|---|" + "|---|" + "|---|" +"\n" +
                "|(T)|" + "|(D)|" + "|(A)|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|(A)|" + "|(A)|" + "|(A)|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|(T)|" + "|(T)|" + "|(D)|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n";
        assertEquals(expectedGrid, grid);
    }

    @Test
    public void displayValueReturnsGridWithAnyNumberOfColumnsPerRow() {
        List<List<Cell>> cells = asList(asList(T, D, A),
                                        singletonList(A),
                                        asList(T, T));

        String grid = new Grid(cells).displayValue();

        String expectedGrid =
                "|---|" + "|---|" + "|---|" +"\n" +
                "|(T)|" + "|(D)|" + "|(A)|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|---|" + "\n" +
                "|(A)|" + "\n" +
                "|---|" + "\n" +
                "|---|" + "|---|" +"\n" +
                "|(T)|" + "|(T)|" +"\n" +
                "|---|" + "|---|" +"\n";
        assertEquals(expectedGrid, grid);
    }

    @Test
    public void displayValueReturnsGridWithNothingForEmptyRows() {
        List<List<Cell>> cells = asList(singletonList(T),
                                        emptyList(),
                                        singletonList(D));

        String grid = new Grid(cells).displayValue();

        String expectedGrid =
                "|---|" + "\n" +
                "|(T)|" + "\n" +
                "|---|" + "\n" +
                "|---|" + "\n" +
                "|(D)|" + "\n" +
                "|---|" + "\n";
        assertEquals(expectedGrid, grid);
    }

}