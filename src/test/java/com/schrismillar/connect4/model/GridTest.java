package com.schrismillar.connect4.model;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class GridTest {

    @Test
    public void cellOwnersReturnsListOfOwnersOfAllCellsInGrid() {
        CellOwnerId startingOwner = new CellOwnerId() {};
        Grid grid = new Grid(2, 2, startingOwner);

        List<List<CellOwnerId>> owners = grid.cellOwners();

        assertEquals(asList(asList(startingOwner, startingOwner), asList(startingOwner, startingOwner)), owners);
    }
}