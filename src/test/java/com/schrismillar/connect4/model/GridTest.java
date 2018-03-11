package com.schrismillar.connect4.model;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class GridTest {
    private static final CellOwnerId STARTING_OWNER = new CellOwnerId() {};

    @Test
    public void cellOwnersReturnsListOfOwnersOfAllCellsInGrid() {
        Grid grid = new Grid(2, 2, STARTING_OWNER);

        List<List<CellOwnerId>> owners = grid.cellOwners();

        assertEquals(asList(asList(STARTING_OWNER, STARTING_OWNER), asList(STARTING_OWNER, STARTING_OWNER)), owners);
    }
}