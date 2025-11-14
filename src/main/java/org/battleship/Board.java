package org.battleship;

import java.util.Arrays;

public class Board {
    private char[][] grid;

    // filling grid with '~' chars
    public Board () {
        this.grid = new char[10][10];
        for (char[] chars : grid) {
            Arrays.fill(chars, '~');
        }
    }

    // displaying full board on console
    public void displayBoard() {
        // printing number of column from 1 to 10
        System.out.print("  ");
        for (int i = 1; i < 11; i++) {
            System.out.print(i + " ");
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
}
