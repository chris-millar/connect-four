package com.schrismillar.connect4.ui;

import com.schrismillar.connect4.model.CellOwnerId;
import com.schrismillar.connect4.model.GridData;

public class GridDataBasedDisplayGrid implements DisplayGrid {
    private static final String NEW_LINE = "\n";

    private final GridData gridData;
    private final CellOwnerToDisplayCellMapper cellOwnerToDisplayCellMapper;

    public GridDataBasedDisplayGrid(GridData gridData, CellOwnerToDisplayCellMapper cellOwnerToDisplayCellMapper) {
        this.gridData = gridData;
        this.cellOwnerToDisplayCellMapper = cellOwnerToDisplayCellMapper;
    }

    @Override
    public String displayValue() {
        StringBuilder gridStringBuilder = new StringBuilder();
        for (int row = 0; row < gridData.height(); row++) {
            DisplayRowStringBuilder rowStringBuilder = new DisplayRowStringBuilder();
            for (int col = 0; col < gridData.width(); col++) {
                CellOwnerId owner = gridData.getCellAt(row, col).owner();
                DisplayCell displayCell = cellOwnerToDisplayCellMapper.determineDisplayCellFrom(owner);
                rowStringBuilder.appendWrappedCellValue(displayCell.value());
            }
            gridStringBuilder.append(rowStringBuilder.build());
            if (notLastRow(row)) {
                gridStringBuilder.append(NEW_LINE);
            }
        }
        return gridStringBuilder.toString();
    }

    private boolean notLastRow(int row) {
        return row != gridData.height() - 1;
    }

    private class DisplayRowStringBuilder {
        private final StringBuilder rowLine1;
        private final StringBuilder rowLine2;
        private final StringBuilder rowLine3;

        private DisplayRowStringBuilder() {
            rowLine1 = new StringBuilder();
            rowLine2 = new StringBuilder();
            rowLine3 = new StringBuilder();
        }

        private void appendWrappedCellValue(String cellDisplayValue) {
            rowLine1.append("|---|");
            rowLine2.append("|(").append(cellDisplayValue).append(")|");
            rowLine3.append("|---|");
        }


        private String build() {
            return new StringBuilder().append(rowLine1.toString()).append(NEW_LINE).
                    append(rowLine2.toString()).append(NEW_LINE).
                    append(rowLine3.toString()).
                    toString();
        }
    }
}
