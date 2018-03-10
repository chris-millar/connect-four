package com.schrismillar.connect4.model;

import static com.schrismillar.connect4.model.PlayerId.NONE;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GridTest {

    @Test
    public void cellOwnersReturnsListOfOwnersOfAllCellsInGrid() {
        Grid grid = new Grid(2, 2);

        List<List<PlayerId>> owners = grid.cellOwners();

        assertEquals(asList(asList(NONE, NONE), asList(NONE, NONE)), owners);
    }
}