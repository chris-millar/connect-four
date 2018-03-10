package com.schrismillar.connect4.ui;

import java.util.List;
import java.util.function.Supplier;

public class DisplayGrid {
    private static final String NEW_LINE = "\n";
    private final List<List<DisplayCell>> cells;

    public DisplayGrid(List<List<DisplayCell>> cells) {
        this.cells = cells;
    }

    public String displayValue() {
        StringBuffer grid = new StringBuffer();
        cells.forEach(row ->
                grid.append(oneLineComposedOf(separatorRowOfSize(row.size()))).
                     append(oneLineComposedOf(valuesInGridRow(row))).
                     append(oneLineComposedOf(separatorRowOfSize(row.size()))));
        return grid.toString();
    }

    private StringBuffer oneLineComposedOf(Supplier<StringBuffer> lineBufferSupplier) {
        StringBuffer buffer = lineBufferSupplier.get();
        if (buffer.length() != 0) {
            buffer.append(NEW_LINE);
        }
        return buffer;
    }

    private Supplier<StringBuffer> separatorRowOfSize(int size) {
        return () -> {
            StringBuffer separatorRow = new StringBuffer();
            for (int i = 0; i < size; i++) {
               separatorRow.append("|---|");
            }
            return separatorRow;
        };
    }

    private Supplier<StringBuffer> valuesInGridRow(List<DisplayCell> row) {
        return () -> {
            StringBuffer lineBuffer = new StringBuffer();
            row.forEach(cell -> lineBuffer.append("|").append("(").append(cell.value()).append(")").append("|"));
            return lineBuffer;
        };
    }
}
