package org.battleship;

public enum CellStatus {
    FOG('~'),
    SHIP('O'),
    HIT('X'),
    MISS('M');

    private final char cellStatus;

    CellStatus(char cellStatus) {
        this.cellStatus = cellStatus;
    }

    public char getCellStatus() {
        return this.cellStatus;
    }
}
