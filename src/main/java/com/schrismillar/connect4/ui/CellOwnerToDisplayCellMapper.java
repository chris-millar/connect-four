package com.schrismillar.connect4.ui;

import com.schrismillar.connect4.model.CellOwnerId;

public interface CellOwnerToDisplayCellMapper {
    DisplayCell determineDisplayCellFrom(CellOwnerId owner);
}
