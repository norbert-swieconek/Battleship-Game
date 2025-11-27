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
    public void displayMainBoard(boolean hideShips) {
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

            if (!hideShips) {
                for (CellStatus cell : row) {
                    System.out.print(cell.getCellStatus() + " ");
                }
            } else {
                for (CellStatus cell : row) {
                    if (cell == CellStatus.SHIP) {
                        System.out.print("~" + " ");
                    } else {
                        System.out.print(cell.getCellStatus() + " ");
                    }
                }
            }
            System.out.println();
        }
    }



    // setting ship
    public void setGrid(int row, int column, CellStatus status) {
        grid[row][column] = status;
    }

    public boolean placeShip(int startRow, int startCol, int endRow, int endCol, int shipTypeLength) {
        int boatLength;

        if (!isPlacedTooClose(startRow, startCol) && !isPlacedTooClose(endRow, endCol)) {
            if (!Objects.equals(startRow, endRow) && startCol != endCol) {
                System.out.println("Error! Wrong ship location! Try again:");
                System.out.println();
                return false;
            } else {
                // x or y
                // x
                if (Objects.equals(startRow, endRow)) {
                    // length of ship
                    boatLength = endCol - startCol + 1;

                    for (int i = startCol; i <= endCol; i++) {
                        if (isPlacedTooClose(startRow, i)) {
                            System.out.println("Error: Ship must be placed on free cells");
                            return false;
                        }
                    }
                    if (boatLength != shipTypeLength) {
                        System.out.println("Error: Ship can't be bigger or smaller than " + shipTypeLength + " cells.");
                        return false;
                    } else {
                        // Generate ship axis x
                        for (int i = startCol; i <= endCol; i++) {
                            this.setGrid(startRow, i, CellStatus.SHIP);
                        }
                    }
                    // y
                } else {
                    // length of ship
                    boatLength = endRow - startRow + 1;

                    for (int i = startRow; i <= endRow; i++) {
                        if (isPlacedTooClose(i, startCol)) {
                            System.out.println("Error: Ship must be placed on free cells");
                            return false;
                        }
                    }

                    if (boatLength != shipTypeLength) {
                        System.out.println("Error: Ship can't be bigger than " + shipTypeLength + " cells.");
                        return false;
                    } else {
                        // Generate ship axis y
                        for (int i = startRow; i <= endRow; i++) {
                            this.setGrid(i, startCol, CellStatus.SHIP);
                        }
                    }
                }
            }
            return true;
        } else {
            System.out.println();
            System.out.println("Error! You placed it too close to another one. Try again:");
            System.out.println();
            return false;
        }
    }

    // translate char to index of column
    public int translateAlphabetToInt(String x) {
        return x.charAt(0) - 'A';
    }

    public boolean shoot(int row, int col) {
        if (grid[row][col] == CellStatus.SHIP) {
            System.out.println("You hit a ship!");
            grid[row][col] = CellStatus.HIT;
            return true;
        } else if (grid[row][col] == CellStatus.FOG) {
            System.out.println("You missed!");
            grid[row][col] = CellStatus.MISS;
            return false;
        } else {
            System.out.println("You've already fired at this cell!");
            return false;
        }
    }

    // checking if ship is too close to another ship
    private boolean isPlacedTooClose(int row, int col) {
        int iStart = Math.max(0, row - 1);
        int iEnd = Math.min(9, row + 1);
        int jStart = Math.max(0, col - 1);
        int jEnd = Math.min(9, col + 1);

        for (int i = iStart; i <= iEnd; i++) {
            for (int j = jStart; j <= jEnd; j++) {
                if (grid[i][j] == CellStatus.SHIP) {
                    return true;
                }
            }
        }
        return false;
    }
}
