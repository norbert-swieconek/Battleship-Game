package org.battleship;

import java.util.Arrays;
import java.util.Objects;

public class Board {
    private CellStatus[][] grid;
    private final int BOARD_SIZE = 10;

    // filling grid with '~' chars
    public Board () {
        this.grid = new CellStatus[BOARD_SIZE][BOARD_SIZE];
        for (CellStatus[] chars : grid) {
            Arrays.fill(chars, CellStatus.FOG);
        }
    }

    // displaying full board on console
    public void displayBoard() {
        // printing number of column from 1 to 10
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        // printing name of row from A to J and battlefield
        char letter = 'A';
        for (CellStatus[] row : grid) {
            System.out.print(letter + " ");
            letter++;
            for (CellStatus cell : row) {
                System.out.print(cell.getCellStatus() + " ");
            }
            System.out.println();
        }
    }

    // setting ship
    public void setGrid(int row, int column, CellStatus status) {
        grid[row][column] = status;
    }

    public boolean placeShip(String startRow, int startCol, String endRow, int endCol) {
        int boatLength = 0;

        if (!Objects.equals(startRow, endRow) && startCol != endCol) {
            System.out.println("Error: Ship must be placed on x or y axis or coordinates are out of board.");
            return false;
        } else {
            // x or y
            // x
            if (Objects.equals(startRow, endRow)) {
                // length of ship
                boatLength = endCol - startCol;

                if (boatLength > 3) {
                    System.out.println("Error: Ship can't be bigger than 4 fields.");
                    return false;
                } else {
                    // Generate ship axis x
                    for (int i = startCol - 1; i < endCol; i++) {
                        this.setGrid(this.translateAlphabetToInt(startRow), i , CellStatus.SHIP);
                    }

                }
            // y
            } else {
                boatLength = this.translateAlphabetToInt(endRow) - this.translateAlphabetToInt(startRow);
                if (boatLength > 3) {
                    System.out.println("Error: Ship can't be bigger than 4 fields.");
                    return false;
                } else {
                    // Generate ship axis y
                    for (int i = this.translateAlphabetToInt(startRow); i <= this.translateAlphabetToInt(endRow); i++) {
                        this.setGrid(i, startCol - 1, CellStatus.SHIP);
                    }
                }
            }
        }
        return true;
    }

    // translate char to index of column
    public int translateAlphabetToInt(String x) {
        return x.charAt(0) - 'A';
    }
}
