package org.battleship;

import java.util.Arrays;

public class Board {
    private char[][] grid;
    private final int BOARD_SIZE = 10;
    private final char FOG_OF_WAR = '*';
    private final char CELL_OF_SHIP = 'O';

    // filling grid with '~' chars
    public Board () {
        this.grid = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] chars : grid) {
            Arrays.fill(chars, FOG_OF_WAR);
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
        for (char[] row : grid) {
            System.out.print(letter + " ");
            letter++;
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // setting ship
    public void setGrid(int row, int column) {
        grid[row][column] = CELL_OF_SHIP;
    }
}
