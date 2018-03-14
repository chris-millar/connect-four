package com.schrismillar.connect4.ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.schrismillar.connect4.model.Cell;
import com.schrismillar.connect4.model.CellOwnerId;
import com.schrismillar.connect4.model.GridData;

public class DisplayGridImplTest {
    private static final DisplayCell A = () -> "A";
    private static final DisplayCell B = () -> "B";
    private static final DisplayCell BLANK = () -> " ";
    private static final CellOwnerToDisplayCellMapper ALWAYS_A = cellOwner -> A;
    private static final CellOwnerId OWNER_ONE = new CellOwnerId() {};
    private static final CellOwnerId OWNER_TWO = new CellOwnerId() {};
    private static final CellOwnerToDisplayCellMapper MULTI_OWNER_MAPPER = owner -> {
        if (owner == OWNER_ONE) {
            return A;
        } else if (owner == OWNER_TWO) {
            return B;
        } else {
            return BLANK;
        }
    };

    @Test
    public void displayValueReturnsStringRepresentationOfGridWithSingleCellIfOnlyOneCellProvided() {
        Cell[][] cells = new Cell[1][1];
        cells[0][0] = cellWithOwner(OWNER_ONE);
        GridData gridData = new GridData(cells);
        DisplayGridImpl displayGridImpl = new DisplayGridImpl(gridData, ALWAYS_A);

        String displayValue = displayGridImpl.displayValue();

        String expectedDisplayValue =
                "|-1-|" + "\n" +
                "\n" +
                "|---|" + "\n" +
                "|(A)|" + "\n" +
                "|---|";
        assertEquals(expectedDisplayValue, displayValue);
    }

    @Test
    public void displayValueReturnsGridWithDifferentCellValues() {
        Cell[][] cells = new Cell[1][2];
        cells[0][0] = cellWithOwner(OWNER_ONE);
        cells[0][1] = cellWithOwner(OWNER_TWO);
        GridData gridData = new GridData(cells);
        DisplayGridImpl displayGridImpl = new DisplayGridImpl(gridData, MULTI_OWNER_MAPPER);

        String displayValue = displayGridImpl.displayValue();

        String expectedDisplayValue =
                "|-1-|" + "|-2-|" + "\n" +
                "\n" +
                "|---|" + "|---|" + "\n" +
                "|(A)|" + "|(B)|" + "\n" +
                "|---|" + "|---|";
        assertEquals(expectedDisplayValue, displayValue);
    }

    @Test
    public void displayValueReturns2x2Grid() {
        Cell[][] cells = new Cell[2][2];
        cells[0][0] = cellWithOwner(OWNER_ONE);
        cells[0][1] = cellWithOwner(OWNER_TWO);
        cells[1][0] = cellWithOwner(mock(CellOwnerId.class));
        cells[1][1] = cellWithOwner(OWNER_TWO);
        GridData gridData = new GridData(cells);
        DisplayGridImpl displayGridImpl = new DisplayGridImpl(gridData, MULTI_OWNER_MAPPER);

        String displayValue = displayGridImpl.displayValue();

        String expectedDisplayValue =
                "|-1-|" + "|-2-|" + "\n" +
                "\n" +
                "|---|" + "|---|" + "\n" +
                "|(A)|" + "|(B)|" + "\n" +
                "|---|" + "|---|" + "\n" +
                "|---|" + "|---|" + "\n" +
                "|( )|" + "|(B)|" + "\n" +
                "|---|" + "|---|";
        assertEquals(expectedDisplayValue, displayValue);
    }

    @Test
    public void displayValueReturns3x3Grid() {
        CellOwnerId otherOwner = mock(CellOwnerId.class);
        Cell[][] cells = new Cell[3][3];
        cells[0][0] = cellWithOwner(OWNER_ONE);
        cells[0][1] = cellWithOwner(otherOwner);
        cells[0][2] = cellWithOwner(OWNER_TWO);
        cells[1][0] = cellWithOwner(otherOwner);
        cells[1][1] = cellWithOwner(OWNER_TWO);
        cells[1][2] = cellWithOwner(OWNER_TWO);
        cells[2][0] = cellWithOwner(otherOwner);
        cells[2][1] = cellWithOwner(OWNER_TWO);
        cells[2][2] = cellWithOwner(OWNER_ONE);
        GridData gridData = new GridData(cells);
        DisplayGridImpl displayGridImpl = new DisplayGridImpl(gridData, MULTI_OWNER_MAPPER);

        String displayValue = displayGridImpl.displayValue();

        String expectedDisplayValue =
                "|-1-|" + "|-2-|" + "|-3-|" +"\n" +
                "\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|(A)|" + "|( )|" + "|(B)|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|( )|" + "|(B)|" + "|(B)|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|---|" + "|---|" + "|---|" +"\n" +
                "|( )|" + "|(B)|" + "|(A)|" +"\n" +
                "|---|" + "|---|" + "|---|";
        assertEquals(expectedDisplayValue, displayValue);
    }

    private Cell cellWithOwner(CellOwnerId owner) {
        Cell cell = mock(Cell.class);
        when(cell.owner()).thenReturn(owner);
        return cell;
    }
}